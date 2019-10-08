package org.MEngine.System.Thread;

import java.util.Vector;

public abstract class MRunnable implements Runnable {
    protected Vector<Object> data;

    protected MRunnable(Vector<Object> data) {
        this.data = data;
    }
}