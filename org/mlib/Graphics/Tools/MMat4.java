package org.mlib.Graphics.Tools;

import android.opengl.Matrix;

public class MMat4 {
    private float[] components;

    public MMat4() {
        this.components = new float[16];
    }

    public void identity(int identity) {
        Matrix.setIdentityM(this.components, identity);
    }

    public void translate(MVec2 offset) {
        Matrix.translateM(this.components, 0, offset.x, offset.y, 0);
    }

    public void scale(MVec2 factor) {
        Matrix.scaleM(this.components, 0, factor.x, factor.y, 1);
    }

    public void mul(MMat4 right) {
        Matrix.multiplyMM(this.components, 0, this.components, 0, right.getComponents(), 0);
    }

    public void setOrtho(int width, int height) {
        Matrix.orthoM(this.components, 0, 0, width, height, 0, 0, 1);
    }

    public float[] getComponents() {
        return this.components;
    }
}