package com.xipu.game;

import java.util.List;

public class PlayerActor extends CharacterActor {

    List<Observer> observerList;

    public PlayerActor(String path){
        super(path);
        inputComponent = new PlayerInputComponent(this);
        this.addListener(inputComponent);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.update(delta);
    }

    private void update(float dt){
        inputComponent.update(dt);
    }

    void register(Observer observer){
        observerList.add(observer);
    }

    void stopAnimation(PlayerInputComponent.DIRECTION direction){
        graphicsComponent.resetToFirstFrame(direction);
    }

    void runAnimation(float dt, PlayerInputComponent.DIRECTION direction){
        graphicsComponent.update(dt, direction);
        //
    }

}
