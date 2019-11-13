package ru.otus.hw03.bench;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class BenchmarkStats {
    private final int MS_IN_S = 60_000;
    private int sleepMs;
    private int sleepInterval;
    private long timeToOomMs = 0;
    private long listAddCount = 0;

    private Map<String, GcStats> gcStats = new HashMap<>();

    public BenchmarkStats(int sleepMs, int sleepInterval) {
        this.sleepMs = sleepMs;
        this.sleepInterval = sleepInterval;
    }

    public long getTimeToOomMs() {
        return timeToOomMs;
    }

    public void setTimeToOomMs(long timeToOomMs) {
        this.timeToOomMs = timeToOomMs;
    }

    public long getListAddCount() {
        return listAddCount;
    }

    void setListAddCount(long listAddCount) {
        this.listAddCount = listAddCount;
    }

    public void addGc(String gcName) {
        gcStats.put(gcName, new GcStats());
    }

    public void plusGcStats(String gcName, long gcTimeMs) {
        gcStats.get(gcName).plusGc(gcTimeMs);
    }

    @Override
    public String toString() {
        String res = "";
        for (var key : gcStats.keySet()) {
            // (60000*t)/T
            int gcAverageTimeMsInMinute = (int)Math.floor(((double) MS_IN_S / timeToOomMs) * gcStats.get(key).getGcTimeMs());

            res += MessageFormat.format("gcName:{0}\t| sleepMs: {1}\t| sleepInterval: {2}\t| " +
                            "timeToOomMs: {3}\t| listAddCount: {4}\t| " +
                            "gcCount: {5}\t| gcTimeMs: {6}\t| " +
                            "gcAverageTimeMsInMinute: {7}" +
                            "\n",
                    key, sleepMs, sleepInterval,
                    timeToOomMs, listAddCount,
                    gcStats.get(key).getGcCount(), gcStats.get(key).getGcTimeMs(),
                    gcAverageTimeMsInMinute);
        }
        return res;
    }


}
