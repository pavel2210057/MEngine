package org.MEngine.Physics.Dynamic.Dynamic;

import android.util.Pair;
import org.MEngine.Graphics.Tools.MVec2;
import org.MEngine.Physics.Dynamic.Reaction.MCircleNCircle;
import org.MEngine.Physics.Model.Body.MBody;
import org.MEngine.Physics.Model.Geometry.MCircle;

public class MPhysicalCircle extends MPhysicalBody {
    public MPhysicalCircle(MBody body, MCircle circle) {
        super(body, circle);
    }

    @Override
    public Pair<MVec2, MVec2> getLinearImpulses(MPhysicalBody right) {
        if (right instanceof MPhysicalCircle)
            return MCircleNCircle.getLinearImpulses(
                    this,
                    (MPhysicalCircle) right
                    );

        return null;
    }

    @Override
    public void update() {
        getGeometrical().getAABB().move(
                getBody().linearVel
        );
        
        ((MCircle) getGeometrical()).getCenter().translate(
                getBody().linearVel
        );
    }
}