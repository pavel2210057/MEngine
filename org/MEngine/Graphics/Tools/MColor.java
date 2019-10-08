package org.MEngine.Graphics.Tools;

public class MColor {
    public static MColor Red = new MColor(1, 0, 0, 1);
    public static MColor Green = new MColor(0, 1, 0, 1);
    public static MColor Blue = new MColor(0, 0, 1, 1);
    public static MColor Yellow = new MColor(1, 1, 0, 1);
    public static MColor Violet = new MColor(1, 0, 1, 1);
    public static MColor LightBlue = new MColor(0, 1, 1, 1);

    public Float r, g, b, a;

    public MColor() {
        this(0, 0, 0, 1);
    }

    public MColor(float rgba) {
        this(rgba, rgba, rgba, rgba);
    }

    public MColor(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
}