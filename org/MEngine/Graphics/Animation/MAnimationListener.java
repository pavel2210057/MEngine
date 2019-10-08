package org.MEngine.Graphics.Animation;

import java.util.Vector;

public class MAnimationListener {
    private Vector<MAnimationEvent> events;

    public MAnimationListener() {
        this(new Vector<MAnimationEvent>());
    }

    public MAnimationListener(Vector<MAnimationEvent> events) {
        setEvents(events);
    }

    public void setEvents(Vector<MAnimationEvent> events) {
        this.events = events;
    }

    public Vector<MAnimationEvent> getEvents() {
        return this.events;
    }

    public boolean onStep(Vector<Float> data) {
        for (MAnimationEvent event : this.events)
            if (!event.onStep(data))
                return false;
        return true;
    }

    public void onFinish() {
        for (MAnimationEvent event : this.events)
            event.onFinish();
    }
}