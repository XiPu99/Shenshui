package com.xipu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class GraphicsComponent {
    public static final int SPRITE_SHEET_COL_NUMS = 4;
    public static final int SPRITE_SHEET_ROW_NUMS = 4;
    public static final float DEFAULT_FRAME_DURATION = 0.25f;
    public static final int DEFAULT_SCALE = 3;
    Texture walkSheet;
    Sprite currentFrame;
    Animation<TextureRegion> walkAnimation;
    float stateTime = 0; // time used for determining which frame to draw in the sprite sheet
    MyActor myActor;

    public GraphicsComponent(MyActor myActor, String path) {
        this.myActor = myActor;
        walkSheet = new Texture(Gdx.files.internal(path));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / SPRITE_SHEET_COL_NUMS,
                walkSheet.getHeight() / SPRITE_SHEET_ROW_NUMS);

        currentFrame = new Sprite(tmp[0][0]);
        currentFrame.setOrigin(0, 0);
        currentFrame.setScale(DEFAULT_SCALE);
        walkAnimation = new Animation<>(DEFAULT_FRAME_DURATION, tmp[0]);

        myActor.setBounds(getX(), getY(), getWidth(), getHeight());
    }

    void update(float dt) {
        stateTime += dt;
        if (stateTime >= 1f) stateTime = 0f;
        setKeyFrameByStateTime();
    }

    private void setKeyFrameByStateTime() {
        currentFrame.setRegion(walkAnimation.getKeyFrame(stateTime, true));
    }

    void resetToFirstFrame() {
        stateTime = 0;
        setKeyFrameByStateTime();
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