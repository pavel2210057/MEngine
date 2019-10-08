package org.MEngine.Audio.Output;

import android.media.MediaPlayer;

import androidx.annotation.IdRes;

import org.MEngine.System.DeviceService.MDevice;

public class MAudio {
    private MediaPlayer player;

    public MAudio() {
        this(new MediaPlayer());
    }

    public MAudio(MDevice device, @IdRes int resource) {
        this(
                MediaPlayer.create(
                        device.getActivity(),
                        resource
                )
        );
    }

    public MAudio(MediaPlayer player) {
        setPlayer(player);
    }

    public void setPlayer(MediaPlayer player) {
        this.player = player;
    }

    public MediaPlayer getPlayer() {
        return this.player;
    }
}