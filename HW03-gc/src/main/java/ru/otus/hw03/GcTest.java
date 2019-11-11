package ru.otus.hw03;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

import javax.management.Notification;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;

import com.sun.management.GarbageCollectionNotificationInfo;
import ru.otus.hw03.bench.Benchmark;
import ru.otus.hw03.bench.BenchmarkStats;

/*
-XX:+UseSerialGC
-XX:+UseParallelGC
-XX:+UseG1GC

-Xms512m
-Xmx512m
 */

public class GcTest {
    public static void main(String... args) throws Exception {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int sleepMs = 15;
        int sleepInterval = 2_000;
        int logInterval = 20_000;

        BenchmarkStats benchmarkStats = new BenchmarkStats(sleepMs, sleepInterval);
        switchOnMonitoring(benchmarkStats);
        long beginTime = System.currentTimeMillis();


        Benchmark benchmark = new Benchmark(sleepMs, sleepInterval, logInterval, benchmarkStats);
        benchmark.run();

        System.out.println("time:" + (System.currentTimeMillis() - beginTime) / 1000);
    }

    private static void switchOnMonitoring(BenchmarkStats benchmarkStats) {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            benchmarkStats.addGc(gcbean.getName());

            System.out.println("GC name:" + gcbean.getName());
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            // Пожалуй, не очень хороший хак
            NotificationListener listener = (notification, handback) -> gcListener(notification, benchmarkStats);
            emitter.addNotificationListener(listener, null, null);
        }
    }

    private static void gcListener(Notification notification, Object handback) {
        BenchmarkStats benchmarkStats = (BenchmarkStats) handback;

        if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
            GarbageCollectionNotificationInfo info =
                    GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
            String gcName = info.getGcName();
            String gcAction = info.getGcAction();
            String gcCause = info.getGcCause();

            long startTime = info.getGcInfo().getStartTime();
            long duration = info.getGcInfo().getDuration();

            benchmarkStats.plusGcStats(gcName, duration);
            benchmarkStats.setTimeToOomMs(startTime);

            System.out.println(benchmarkStats.toString());

            System.out.println("start:" + startTime + " Name:" + gcName + ", action:" + gcAction + ", gcCause" +
                    ":" + gcCause + "(" + duration + " ms)");
        }
    }
}
