package org.mlib.System.Vibration;

import android.app.Service;
import android.os.VibrationEffect;
import android.os.Vibrator;

import org.mlib.System.DeviceService.MDevice;
import org.mlib.System.Exception.MException;

public class MVibration {
    private Vibrator vibrator;

    public MVibration(MDevice device) {
        this.vibrator = (Vibrator) device.getActivity().getSystemService(
                Service.VIBRATOR_SERVICE
        );

        if (!this.vibrator.hasVibrator())
            new MException("Отсутствует датчик вибрации").printStackTrace();
    }

    public void vibro(int milliseconds) {

    }

    public Vibrator getVibrator() {
        return this.vibrator;
    }
}