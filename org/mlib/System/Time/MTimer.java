package org.mlib.System.Time;

public class MTimer {
    private long begin;
    private long mills;

    public MTimer() {
        this.begin = System.currentTimeMillis();
    }

    public double getSeconds() {
        return (double) getMills() / 1000;
    }

    public long getMills() {
        return (this.mills = System.currentTimeMillis() - this.begin);
    }
}