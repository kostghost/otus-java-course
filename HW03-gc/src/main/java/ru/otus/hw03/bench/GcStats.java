package ru.otus.hw03.bench;

class GcStats {
    private long gcCount = 0;
    private long gcTimeMs = 0;

    void plusGc(long gcTimeMs){
        gcCount++;
        this.gcTimeMs+=gcTimeMs;
    }

    long getGcCount() {
        return gcCount;
    }

    long getGcTimeMs() {
        return gcTimeMs;
    }
}
