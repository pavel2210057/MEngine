package org.MEngine.Network;

import java.util.Vector;

public class MNetworkListener {
    Vector<MNetworkEvent> events;

    public MNetworkListener() {
        this(new Vector<MNetworkEvent>());
    }

    public MNetworkListener(Vector<MNetworkEvent> events) {
        this.events = events;
    }

    public void onSuccess(String response) {
        for (MNetworkEvent event : this.events)
            event.onSuccess(response);
    }

    public void onFailed(String cause) {
        for (MNetworkEvent event : this.events)
            event.onFailed(cause);
    }

    public Vector<MNetworkEvent> getEvents() {
        return this.events;
    }
}