package org.mlib.Graphics.Shape.Tod;

import org.mlib.Graphics.Texture.MTexture;
import org.mlib.Graphics.Tools.MRect;
import org.mlib.Graphics.Tools.MVec2;

public class MSprite extends MRectangle {
    private MTexture texture;
    private MRect textureRect;

    public MSprite() {
        this.texture = new MTexture();
        this.textureRect = new MRect();
    }

    public void setTexture(MTexture texture) {
        this.texture = texture;
        getShader().setUniform("iTexture", new float[] { texture.getTexture() });
        setTextureRect(new MRect(0, 0, 1, 1));
    }

    public void setTextureRect(MRect textureRect) {
        this.textureRect = textureRect;

        getVertices().get(0).texCoord = new MVec2(textureRect.left, textureRect.top);
        getVertices().get(1).texCoord = new MVec2(textureRect.left, textureRect.bottom);
        getVertices().get(2).texCoord = new MVec2(textureRect.right, textureRect.top);
        getVertices().get(3).texCoord = new MVec2(textureRect.left, textureRect.bottom);
        getVertices().get(4).texCoord = new MVec2(textureRect.right, textureRect.top);
        getVertices().get(5).texCoord = new MVec2(textureRect.right, textureRect.bottom);
    }

    public MTexture getTexture() {
        return this.texture;
    }

    public MRect getTextureRect() {
        return this.textureRect;
    }

    @Override
    public void draw() {
        MTexture.bind(this.texture.getTexture());
        super.draw();
        MTexture.bind(0);
    }

    @Override
    public boolean compare(MShape right) {
        return
                super.compare(right) &&
                this.texture.getTexture() == ((MSprite)right).getTexture().getTexture();
    }
}