package org.mlib.Physics.Scene;

import android.util.Pair;
import org.mlib.Graphics.Tools.MVec2;
import org.mlib.System.Time.MTimer;
import org.mlib.Physics.Dynamic.Dynamic.MPhysicalBody;
import java.util.Vector;

public class MPhysicalScene {
    private Vector<MPhysicalBody> physicalBodies;
    private MPhysicalListener listener;
    private int fps;
    private long frameTime;

    public MPhysicalScene() {
        this(new Vector<>());
    }

    public MPhysicalScene(Vector<MPhysicalBody> physicalBodies) {
        this.physicalBodies = physicalBodies;
        this.listener = new MPhysicalListener();
        setFps(60);
    }

    public void setPhysicalBody(MPhysicalBody physicalBody) {
        this.physicalBodies.add(physicalBody);
    }

    public void setFps(int fps) {
        this.fps = fps;
        this.frameTime = 1000 / fps;
    }

    public Vector<MPhysicalBody> getPhysicalBodies() {
        return this.physicalBodies;
    }

    public int getFps() {
        return this.fps;
    }

    public float getFrameTime() {
        return this.frameTime;
    }

    public MPhysicalListener getListener() {
        return this.listener;
    }

    public boolean step() {
        for (int i = 0; i < this.physicalBodies.size(); ++i) {
            for (int j = i + 1; j < this.physicalBodies.size(); ++j) {
                if (this.physicalBodies.get(i).getGeometrical().isCollision(
                        this.physicalBodies.get(j).getGeometrical()
                )) {
                    Pair<MVec2, MVec2> impulses =
                            this.physicalBodies.get(i).getLinearImpulses(
                                    this.physicalBodies.get(j)
                            );

                    this.physicalBodies.get(i).getBody().linearVel = impulses.first;
                    this.physicalBodies.get(j).getBody().linearVel = impulses.second;
                }
            }

            this.physicalBodies.get(i).update();
        }

        return this.listener.onStep();
    }

    public void start() {
        new Thread(
                () -> {
                    while (step())
                        MTimer.waiting(this.frameTime);
                }
        ).start();
    }
}