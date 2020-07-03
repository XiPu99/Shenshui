package com.xipu.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class NPCActor extends CharacterActor {

    int speed = 50;
    boolean disabled = false;

    public NPCActor(String path) {
        super(path);
        inputComponent = new NPCInputComponent(this);
    }

    public void test() {
        Rectangle re = new Rectangle();
    }

    /**
     * Called each frame by {@link Actor#act(float)}.
     *
     * @param delta Time in seconds since the last frame.
     */
    @Override
    public void act(float delta) {
        super.act(delta);
//        if(disabled){
////            this.moveBy(0, speed * delta);
//            this.stopAnimation(PlayerInputComponent.DIRECTION.DOWN);
//        }
//        else{
//            this.moveBy(0, - speed * delta);
//            this.runAnimation(delta, PlayerInputComponent.DIRECTION.DOWN);
//
//        }
    }

    @Override
    public void onCollision(PlayerInputComponent.DIRECTION direction) {
//        System.out.println("On collide");
        System.out.println(direction);
        disabled = true;
//        if (disabled && (direction == PlayerInputComponent.DIRECTION.UP || direction == null)) {
//            disabled = true;
//        } else {
//
//        }
    }

    @Override
    public void leaveCollision() {
//        System.out.println("Leave collision");
        disabled = false;
    }

    @Override
    public CollisionComponent getCollisionComponent() {
        return this.collisionComponent;
    }
}
