package com.xipu.game;

public interface CanCollide {

    void onCollision(PlayerInputComponent.DIRECTION direction);
    void leaveCollision();

    CollisionComponent getCollisionComponent();
}
