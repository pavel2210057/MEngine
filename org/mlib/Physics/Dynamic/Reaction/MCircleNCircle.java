package org.mlib.Physics.Dynamic.Reaction;

import android.util.Pair;
import org.mlib.Graphics.Tools.MVec2;
import org.mlib.Physics.Dynamic.Dynamic.MPhysicalCircle;
import org.mlib.Physics.Model.Geometry.MCircle;

public class MCircleNCircle {
    static public Pair<MVec2, MVec2> getLinearImpulses(MPhysicalCircle left, MPhysicalCircle right) {
        MVec2 normal = new MVec2(
                ((MCircle) left.getGeometrical()).getCenter(),
                ((MCircle) right.getGeometrical()).getCenter()
        );

        return MReactionPair.getLinearImpulses(
                left.getBody(),
                right.getBody(),
                normal
        );
    }
}