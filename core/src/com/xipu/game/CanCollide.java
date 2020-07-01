package com.xipu.game;

public interface CanCollide {

    boolean canCollideWith(CanCollide myActor);

    CollisionComponent getCollisionComponent();
}
