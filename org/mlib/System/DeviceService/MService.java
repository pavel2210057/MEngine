package org.mlib.System.DeviceService;

import android.view.View;

public class MService {
    public static void hideNavigation(View view) {
        int mode =
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        view.setSystemUiVisibility(mode);
    }
}