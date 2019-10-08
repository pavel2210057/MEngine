package org.mlib.Physics.Render;

import androidx.annotation.NonNull;
import org.mlib.Graphics.Shape.Tod.MRegularPolygon;
import org.mlib.Graphics.Tools.MMat4;
import org.mlib.Physics.Model.Geometry.MCircle;

public class MRenderCircle implements MRenderable {
    private MCircle geometrical;
    private MRegularPolygon drawable;

    public MRenderCircle(@NonNull MCircle geometrical) {
        setGeometrical(geometrical);

        MRegularPolygon polygon = new MRegularPolygon(geometrical.getRadius());

        setDrawable(polygon);
    }

    public MRenderCircle(@NonNull MCircle geometrical, @NonNull MRegularPolygon drawable) {
        setGeometrical(geometrical);
        setDrawable(drawable);
    }

    public void setGeometrical(@NonNull MCircle geometrical) {
        this.geometrical = geometrical;
    }

    public void setDrawable(@NonNull MRegularPolygon drawable) {
        this.drawable = drawable;
    }

    @NonNull
    public MCircle getGeometrical() {
        return this.geometrical;
    }

    @NonNull
    public MRegularPolygon getDrawable() {
        return this.drawable;
    }

    @Override
    public void syncGeometry() {
        if (!this.geometrical.getCenter().compare(this.drawable.getCenter()))
            this.drawable.setCenter(this.geometrical.getCenter());

        if (this.geometrical.getRadius() != this.drawable.getRadius())
            this.drawable.setRadius(this.geometrical.getRadius());
    }

    @Override
    public void update(MMat4 view) {
        this.drawable.update(view);
    }

    @Override
    public void draw() {
        this.drawable.draw();
    }
}