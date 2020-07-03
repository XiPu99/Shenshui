package com.xipu.game;

import com.xipu.game.components.CollisionComponent;
import com.xipu.game.components.PlayerInputComponent;

public interface CanCollide {

    void onCollision(PlayerInputComponent.DIRECTION direction);
    void leaveCollision();

    CollisionComponent getCollisionComponent();
}
