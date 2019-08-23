package org.mlib.System.Database.SQLite;

import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

public class MSQLiteListener {
    private Vector<MSQLiteEvent> events;

    public MSQLiteListener() {
        this(new Vector<MSQLiteEvent>());
    }

    public MSQLiteListener(Vector<MSQLiteEvent> events) {
        setEvents(events);
    }

    public void setEvents(Vector<MSQLiteEvent> events) {
        this.events = events;
    }

    public Vector<MSQLiteEvent> getEvents() {
        return this.events;
    }

    public void onCreate(SQLiteDatabase database) {
        for (MSQLiteEvent event : this.events)
            event.onCreate(database);
    }

    public void onUpgrade(SQLiteDatabase database, int prevVersion, int version) {
        for (MSQLiteEvent event : this.events)
            event.onUpgrade(database, prevVersion, version);
    }
}