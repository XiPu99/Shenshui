package com.xipu.game.actors;

import com.xipu.game.components.CollisionComponent;
import com.xipu.game.Observer;
import com.xipu.game.components.PlayerInputComponent;

import java.util.List;

public class PlayerActor extends CharacterActor {

    List<Observer> observerList;

    public PlayerActor(String path) {
        super(path);
        inputComponent = new PlayerInputComponent(this);
        this.addListener(inputComponent);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.update(delta);
    }

    private void update(float dt) {
        inputComponent.update(dt);
    }

    void register(Observer observer) {
        observerList.add(observer);
    }

    public boolean checkIfThereIsAnyCollision() {
        return this.collisionComponent.checkCollisionHelper();
    }

    public boolean checkIfThereIsAnyCollision(float dx, float dy) {
        if (dx == 0 && dy == 0) return false;
        return this.collisionComponent.canMoveByWithoutCollision(dx, dy);
    }

    @Override
    public void onCollision(PlayerInputComponent.DIRECTION direction) {

    }

    @Override
    public void leaveCollision() {

    }

    @Override
    public CollisionComponent getCollisionComponent() {
//        this.collisionComponent.checkCollisionWith()

        return this.collisionComponent;
    }

    public PlayerInputComponent.DIRECTION getWalkDirection(){
        return ((PlayerInputComponent) inputComponent).getWalkDirection();
    }

}
