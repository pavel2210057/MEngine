package org.MEngine.Physics.Model.Body;

import org.MEngine.Graphics.Tools.MVec2;

public class MBody {
    public float mass;
    public MVec2 linearVel;
    public float angularVel;
    public float friction;

    public boolean isStatic() {
        return mass <= 0;
    }
}