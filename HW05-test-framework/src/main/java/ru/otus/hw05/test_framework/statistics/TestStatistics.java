package ru.otus.hw05.test_framework.statistics;

import java.util.ArrayList;
import java.util.List;

public class TestStatistics {
    private List<TestStatistic> statistics = new ArrayList<>();

    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public void addPassed(String methodName, long durationMs) {
        TestStatistic stat = new TestStatistic(methodName, durationMs, true, null);
        statistics.add(stat);
    }

    public void addFailed(String methodName, long durationMs, Exception exception) {
        TestStatistic stat = new TestStatistic(methodName, durationMs, false, exception);
        statistics.add(stat);
    }

    public String getFormattedStatistic() {
        long passedCount = statistics.stream().filter(TestStatistic::isPassed).count();
        long failedCount = statistics.size() - passedCount;

        StringBuilder res = new StringBuilder("Tests failed: " + failedCount +
                ", passed: " + passedCount +
                " of " + statistics.size() + "\n");

        for (var stat : statistics) {
            res.append(stat.isPassed() ? ANSI_GREEN + "PASSED \t" : ANSI_RED + "FAILED \t")
                    .append(ANSI_RESET)
                    .append(stat.getDurationMs()).append("ms \t")
                    .append(stat.getMethodName()).append("\n");
        }
        return res.toString();
    }

    @Override
    public String toString() {
        return getFormattedStatistic();
    }
}
