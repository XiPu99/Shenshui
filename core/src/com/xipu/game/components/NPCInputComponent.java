package com.xipu.game.components;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.xipu.game.MouseInteractable;
import com.xipu.game.actors.NPCActor;

public class NPCInputComponent extends InputComponent implements MouseInteractable {

    private NPCActor myActor;

    public NPCInputComponent(NPCActor myActor) {
        this.myActor = myActor;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void onEnter() {

    }

    @Override
    public void onExit() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        onEnter();
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        onExit();
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (event.getButton() == Input.Buttons.RIGHT) {
            onRightClick();
        }
        return true;
    }
}
