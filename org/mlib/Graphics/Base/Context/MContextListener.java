package org.mlib.Graphics.Base.Context;

import android.opengl.EGLContext;
import java.util.Vector;
import static android.opengl.EGL14.eglGetCurrentContext;

public class MContextListener {
    private Vector<MContextEvent> events;

    public MContextListener() {
        this.events = new Vector<>();
    }

    public void setEvent(MContextEvent event) {
        this.events.add(event);
    }

    public void removeEvent(MContextEvent event) {
        this.events.removeElement(event);
    }

    public void removeEvent(int index) {
        this.events.removeElementAt(index);
    }

    public void onCreate() {
        for(MContextEvent event : this.events)
            event.onCreate();
    }

    public void onUpdate() {
        for(MContextEvent event : this.events)
            event.onUpdate();
    }

    public void onDraw() {
        for(MContextEvent event : this.events)
            event.onDraw();
    }

}