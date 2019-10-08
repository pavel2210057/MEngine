package org.MEngine.System.Time;

public class MTimer {
    private long begin;
    private long mills;

    public MTimer() {
        restart();
    }

    public double getSeconds() {
        return (double) getMills() / 1000;
    }

    public long getMills() {
        return (this.mills = System.currentTimeMillis() - this.begin);
    }

    public void restart() {
        this.begin = System.currentTimeMillis();
    }

    public static void waiting(long mills) {
        final long begin = System.currentTimeMillis();
        long current = begin;

        while (current - begin <= mills)
            current = System.currentTimeMillis();
    }
}