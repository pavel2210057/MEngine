package org.MEngine.System.Vibration;

import android.app.Service;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.MEngine.System.DeviceService.MDevice;
import org.MEngine.System.Exception.MException;

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