package org.mlib.Graphics.Animation;

import java.util.Vector;

public interface MAnimationEvent {
    boolean onStep(Vector<Float> data);

    void onFinish();
}