package org.mlib.System.Database.SQLite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.mlib.System.DeviceService.MDevice;

public class MSQLite extends SQLiteOpenHelper {
    private MSQLiteListener listener;

    public MSQLite(MDevice device, String name, int version) {
        this(device, name, version, new MSQLiteListener());
    }

    public MSQLite(MDevice device, String name, int version, MSQLiteListener listener) {
        super(device.getActivity(), name, null, version);

        this.listener = listener;
    }

    public void setListener(MSQLiteListener listener) {
        this.listener = listener;
    }

    public MSQLiteListener getListener() {
        return this.listener;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.listener.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int prevVersion, int version) {
        this.listener.onUpgrade(sqLiteDatabase, prevVersion, version);
    }
}