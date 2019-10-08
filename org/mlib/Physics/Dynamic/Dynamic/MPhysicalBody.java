package org.mlib.Physics.Dynamic.Dynamic;

import android.util.Pair;

import androidx.annotation.NonNull;

import org.mlib.Graphics.Tools.MVec2;
import org.mlib.Physics.Model.Body.MBody;
import org.mlib.Physics.Model.Geometry.MGeometrical;

abstract public class MPhysicalBody {
    private MBody body;
    private MGeometrical geometrical;

    public MPhysicalBody(MBody body, MGeometrical geometrical) {
        bindBody(body);
        bindGeometrical(geometrical);
    }

    public void bindBody(MBody body) {
        this.body = body;
    }

    public void bindGeometrical(MGeometrical geometrical) {
        this.geometrical = geometrical;
    }

    @NonNull
    public MBody getBody() {
        return this.body;
    }

    @NonNull
    public MGeometrical getGeometrical() {
        return this.geometrical;
    }

    abstract public Pair<MVec2, MVec2> getLinearImpulses(MPhysicalBody right);

    abstract public void update();
}