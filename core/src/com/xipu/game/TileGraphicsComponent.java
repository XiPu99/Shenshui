package com.xipu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TileGraphicsComponent {
    public static final int DEFAULT_SCALE = 3;
    Texture walkSheet;
    Sprite currentFrame;
    Actor parentActor;

    public TileGraphicsComponent(Actor parentActor, String path) {
        this.parentActor = parentActor;
        walkSheet = new Texture(Gdx.files.internal(path));
        currentFrame = new Sprite(walkSheet);
        currentFrame.setOrigin(0, 0);
        currentFrame.setScale(DEFAULT_SCALE);

        parentActor.setBounds(getX(), getY(), getWidth(), getHeight());

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
