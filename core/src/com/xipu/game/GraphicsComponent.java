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
    Animation<TextureRegion> downWalkAnimation;
    Animation<TextureRegion> leftWalkAnimation;
    Animation<TextureRegion> rightWalkAnimation;
    Animation<TextureRegion> upWalkAnimation;
    float stateTime = 0; // time used for determining which frame to draw in the sprite sheet
    MyActor parentActor;

    public GraphicsComponent(MyActor parentActor, String path) {
        this.parentActor = parentActor;
        walkSheet = new Texture(Gdx.files.internal(path));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / SPRITE_SHEET_COL_NUMS,
                walkSheet.getHeight() / SPRITE_SHEET_ROW_NUMS);

        currentFrame = new Sprite(tmp[0][0]);
        currentFrame.setOrigin(0, 0);
        currentFrame.setScale(DEFAULT_SCALE);
        downWalkAnimation = new Animation<>(DEFAULT_FRAME_DURATION, tmp[0]);
        rightWalkAnimation = new Animation<>(DEFAULT_FRAME_DURATION, tmp[1]);
        upWalkAnimation = new Animation<>(DEFAULT_FRAME_DURATION, tmp[2]);
        leftWalkAnimation = new Animation<>(DEFAULT_FRAME_DURATION, tmp[3]);

        parentActor.setBounds(getX(), getY(), getWidth(), getHeight());
    }

    void update(float dt, InputComponent.DIRECTION direction) {
        stateTime += dt;
        if (stateTime >= 1f) stateTime = 0f;
        setKeyFrameByStateTime(direction);
    }

    private void setKeyFrameByStateTime(InputComponent.DIRECTION direction) {
        Animation<TextureRegion> walkAnimation = getWalkAnimationByDirection(direction);
        currentFrame.setRegion(walkAnimation.getKeyFrame(stateTime, true));
    }

    private Animation<TextureRegion> getWalkAnimationByDirection(InputComponent.DIRECTION direction){
        Animation<TextureRegion> walkAnimation;

        switch (direction){
            case LEFT:
                walkAnimation = leftWalkAnimation;
                break;
            case RIGHT:
                walkAnimation = rightWalkAnimation;
                break;
            case UP:
                walkAnimation = upWalkAnimation;
                break;
            default:
                walkAnimation = downWalkAnimation;
        }
        return walkAnimation;
    }

    void resetToFirstFrame(InputComponent.DIRECTION direction) {
        stateTime = 0;
        setKeyFrameByStateTime(direction);
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