package org.MEngine.Physics.Dynamic.Reaction;

import android.util.Pair;
import org.MEngine.Graphics.Tools.MVec2;
import org.MEngine.Physics.Dynamic.Dynamic.MPhysicalCircle;
import org.MEngine.Physics.Model.Geometry.MCircle;

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