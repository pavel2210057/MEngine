package org.MEngine.System.Database.SQLite;

import android.database.sqlite.SQLiteDatabase;

public interface MSQLiteEvent {
    void onCreate(SQLiteDatabase database);

    void onUpgrade(SQLiteDatabase database, int prevVersion, int version);
}