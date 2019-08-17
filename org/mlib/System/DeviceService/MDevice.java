package org.mlib.System.DeviceService;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

public class MDevice {
    private Activity activity;
    private WindowManager windowManager;
    private Point resolution;
    private boolean isEs2Supported;

    public MDevice(Activity activity) {
        this.activity = activity;

        this.windowManager = activity.getWindowManager();

        this.resolution = new Point();
        this.windowManager.getDefaultDisplay().getSize(this.resolution);

        ActivityManager manager = (ActivityManager) activity
                .getSystemService(Context.ACTIVITY_SERVICE);
        this.isEs2Supported = manager.getDeviceConfigurationInfo()
                .reqGlEsVersion >= 0x20000;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public WindowManager getWindowManager() {
        return this.windowManager;
    }

    public Point getResolution() {
        return this.resolution;
    }

    public boolean getEs2Supported() {
        return this.isEs2Supported;
    }
}