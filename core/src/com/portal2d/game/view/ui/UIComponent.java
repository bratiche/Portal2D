package com.portal2d.game.view.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Base class for user interface components, such as {@link TextButton}.
 */
public abstract class UIComponent {

    protected float x;
    protected float y;
    protected float width;
    protected float height;

    public UIComponent() {
        this(0, 0, 0, 0);
    }

    public UIComponent(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected boolean contains(float x, float y) {
        return x >= this.x && x <= this.x + width && y >= this.y - height && y <= this.y;
    }

    public abstract void render(SpriteBatch batch, float mouseX, float mouseY);

}
