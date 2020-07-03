package com.xipu.game.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CharacterGraphicsComponent extends GraphicsComponent {
    public static final int SPRITE_SHEET_COL_NUMS = 4;
    public static final int SPRITE_SHEET_ROW_NUMS = 4;
    public static final float DEFAULT_FRAME_DURATION = 0.25f;
    Animation<TextureRegion> downWalkAnimation;
    Animation<TextureRegion> leftWalkAnimation;
    Animation<TextureRegion> rightWalkAnimation;
    Animation<TextureRegion> upWalkAnimation;
    float stateTime = 0; // time used for determining which frame to draw in the sprite sheet

    public CharacterGraphicsComponent(Actor parentActor, String path) {
        super(parentActor, path);
    }

    /** Split the sprite sheet and fetch animation frames from it
     * @param texture sprite sheet in which all the walk animation is contained
     */
    @Override
    void processTexture(Texture texture) {
        TextureRegion[][] tmp = TextureRegion.split(texture,
                texture.getWidth() / SPRITE_SHEET_COL_NUMS,
                texture.getHeight() / SPRITE_SHEET_ROW_NUMS);
        downWalkAnimation = new Animation<>(DEFAULT_FRAME_DURATION, tmp[0]);
        rightWalkAnimation = new Animation<>(DEFAULT_FRAME_DURATION, tmp[1]);
        upWalkAnimation = new Animation<>(DEFAULT_FRAME_DURATION, tmp[2]);
        leftWalkAnimation = new Animation<>(DEFAULT_FRAME_DURATION, tmp[3]);
        currentFrame = new Sprite(tmp[0][0]);
    }

    public void update(float dt, PlayerInputComponent.DIRECTION direction) {
        stateTime += dt;
        if (stateTime >= 1f) stateTime = 0f;
        setKeyFrameByStateTime(direction);
    }

    private void setKeyFrameByStateTime(PlayerInputComponent.DIRECTION direction) {
        Animation<TextureRegion> walkAnimation = getWalkAnimationByDirection(direction);
        currentFrame.setRegion(walkAnimation.getKeyFrame(stateTime, true));
    }

    private Animation<TextureRegion> getWalkAnimationByDirection(PlayerInputComponent.DIRECTION direction){
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

    public void resetToFirstFrame(PlayerInputComponent.DIRECTION direction) {
        stateTime = 0;
        setKeyFrameByStateTime(direction);
    }
}
