package com.tealcube.java.games.tacir.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Pool.Poolable;

public class TextureComponent extends Component implements Poolable {

    private TextureAtlas.AtlasRegion texture = null;

    @Override
    public void reset() {
        texture = null;
    }

    public boolean hasTexture() {
        return texture != null;
    }

    public TextureAtlas.AtlasRegion getTexture() {
        return texture;
    }

    public void setTexture(TextureAtlas.AtlasRegion texture) {
        this.texture = texture;
    }

}
