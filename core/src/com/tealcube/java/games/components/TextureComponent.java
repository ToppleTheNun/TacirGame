package com.tealcube.java.games.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Pool.Poolable;

public class TextureComponent extends Component implements Poolable {

    private Texture texture = null;

    @Override
    public void reset() {
        if (hasTexture()) {
            texture.dispose();
        }
        texture = null;
    }

    public boolean hasTexture() {
        return texture != null;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

}
