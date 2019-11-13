package ru.otus.hw03.bench;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tully.
 */
public class Benchmark {
    private int sleepMs;
    private int sleepInterval;
    private int logInterval;
    private BenchmarkStats benchmarkStats;

    public Benchmark(int sleepMs, int sleepInterval, int logInterval, BenchmarkStats benchmarkStats) {
        this.sleepMs = sleepMs;
        this.sleepInterval = sleepInterval;
        this.logInterval = logInterval;
        this.benchmarkStats = benchmarkStats;
    }

    public void run() throws InterruptedException {
        List<Long> lst = new ArrayList<>();

        for (long i = 0; ; i += 2) {
            lst.add(i);
            lst.add(i + 1);
            lst.remove((int) i / 2);

            if (i % sleepInterval == 0) {
                Thread.sleep(sleepMs);
            }
            if (i % logInterval == 0) {
                benchmarkStats.setListAddCount(i);
            }
        }
    }
}