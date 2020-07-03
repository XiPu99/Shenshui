package com.xipu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class GraphicsComponent {
    public static final int DEFAULT_SCALE = 3;
    protected Sprite currentFrame;
    protected Actor parentActor;

    /**
     * @param parentActor Actor object that this {@link GraphicsComponent} is attached to
     */
    public GraphicsComponent(Actor parentActor, String path) {
        this.parentActor = parentActor;
        Texture texture = new Texture(Gdx.files.internal(path));
        processTexture(texture);
        this.currentFrame.setOrigin(0, 0);
        this.currentFrame.setScale(DEFAULT_SCALE);
        this.parentActor.setBounds(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * This method will be called in the constructor.
     * Child class can override this methods to have custom logic.
     */
    void processTexture(Texture texture){
        this.currentFrame = new Sprite(texture);
    }

    void setPosition(float x, float y) {
        currentFrame.setPosition(x, y);
    }

    void draw(Batch batch) {
        currentFrame.draw(batch);
    }

    float getX() {
        return currentFrame.getX();
    }

    float getY() {
        return currentFrame.getY();
    }

    float getWidth() {
        return currentFrame.getWidth() * DEFAULT_SCALE;
    }

    float getHeight() {
        return currentFrame.getHeight() * DEFAULT_SCALE;
    }

}