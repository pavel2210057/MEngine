package org.MEngine.Physics.Scene;

import java.util.Vector;

public class MPhysicalListener {
    private Vector<MPhysicalEvent> events;

    public MPhysicalListener() {
        this(new Vector<>());
    }

    public MPhysicalListener(Vector<MPhysicalEvent> events) {
        this.events = events;
    }

    public Vector<MPhysicalEvent> getEvents() {
        return this.events;
    }

    public boolean onStep() {
        for (MPhysicalEvent event : this.events) {
            if (!event.onStep())
                return false;
        }

        return true;
    }
}