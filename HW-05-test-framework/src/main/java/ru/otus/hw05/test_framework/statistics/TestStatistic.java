package ru.otus.hw05.test_framework.statistics;

class TestStatistic {
    private String methodName;
    private long durationMs;
    private boolean passed;
    private Exception exception;

    TestStatistic(String methodName, long durationMs, boolean passed, Exception exception) {
        this.methodName = methodName;
        this.durationMs = durationMs;
        this.passed = passed;
        this.exception = exception;
    }

    String getMethodName() {
        return methodName;
    }

    long getDurationMs() {
        return durationMs;
    }

    boolean isPassed() {
        return passed;
    }

    Exception getException() {
        return exception;
    }
}
