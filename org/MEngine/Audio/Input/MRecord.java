package org.MEngine.Audio.Input;

import android.media.MediaRecorder;

public class MRecord {
    private MediaRecorder recorder;

    public MRecord() {
        setRecorder(new MediaRecorder());
    }

    public void setRecorder(MediaRecorder recorder) {
        this.recorder = recorder;
    }

    public MediaRecorder getRecorder() {
        return this.recorder;
    }
}