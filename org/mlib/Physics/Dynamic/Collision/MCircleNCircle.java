package org.mlib.Physics.Dynamic.Collision;

import org.mlib.Graphics.Tools.MVec2;
import org.mlib.Physics.Model.Geometry.MCircle;

public class MCircleNCircle {
    static public boolean isCollision(MCircle left, MCircle right) {
        return
                new MVec2(left.getCenter(), right.getCenter()).getLengthSquare() <=
                Math.pow(left.getRadius() + right.getRadius(), 2);
    }
}