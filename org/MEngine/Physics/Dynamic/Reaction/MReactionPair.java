package org.MEngine.Physics.Dynamic.Reaction;

import android.util.Pair;
import org.MEngine.Graphics.Tools.MVec2;
import org.MEngine.Physics.Dynamic.Dynamic.MPhysicalBody;
import org.MEngine.Physics.Model.Body.MBody;

public class MReactionPair {
    static public Pair<MVec2, MVec2> getLinearImpulses(MBody left,
                                                     MBody right,
                                                     MVec2 normal) {
        //V = V * dot(V, n) * n
        MVec2 invNormal = normal.getInverse();
        float dot = left.linearVel.getDotProduct(normal),
              invDot = right.linearVel.getDotProduct(invNormal);

        return new Pair<>(
                new MVec2(
                        left.linearVel.x * dot * normal.x,
                        left.linearVel.y * dot * normal.y
                ),

                new MVec2(
                        right.linearVel.x * invDot * invNormal.x,
                        right.linearVel.y * invDot * invNormal.y
                )
        );
    }

    static public void getRotation(MPhysicalBody left, MPhysicalBody right) { }

    static public void getFriction(MPhysicalBody left, MPhysicalBody right) { }
}