package com.xipu.game;

import com.badlogic.gdx.math.Rectangle;

public class NPCActor extends CharacterActor {

    public NPCActor(String path) {
        super(path);
        inputComponent = new NPCInputComponent(this);
    }

    public void test(){

        Rectangle re = new Rectangle();


    }

    @Override
    public boolean canCollideWith(CanCollide myActor) {
        return this.collisionComponent.checkCollisionWith(myActor);
    }

    @Override
    public CollisionComponent getCollisionComponent() {
        return this.collisionComponent;
    }
}
