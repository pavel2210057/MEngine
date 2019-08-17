package org.mlib.Graphics.Base.Vertex;

import org.mlib.Graphics.Tools.MColor;
import org.mlib.Graphics.Tools.MVec2;

public class MVertex2 {
    public MVec2 position;
    public MVec2 texCoord;
    public MColor color;

    public MVertex2() {
        this(new MVec2(), new MVec2(), new MColor());
    }

    public MVertex2(MVec2 position, MVec2 texCoord, MColor color) {
        this.position = position;
        this.texCoord = texCoord;
        this.color = color;
    }
}