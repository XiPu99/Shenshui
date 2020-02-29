package com.xipu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class InputComponent extends InputListener {

    private boolean move;
    private MyActor myActor;

    public InputComponent(MyActor myActor) {
        this.myActor = myActor;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        switch (keycode){
            case Input.Keys.S:
                move = true;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(InputEvent event, int keycode) {
        switch (keycode){
            case Input.Keys.S:
                move = false;
                myActor.stopAnimation();
                break;
        }
        return true;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        super.enter(event, x, y, pointer, fromActor);
        StageUtility.changeCursor();
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        StageUtility.setDefaultCursor();
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(event.getButton() == Input.Buttons.LEFT){
            System.out.println("Clicked");
        }
        return true;
    }

    public void update(float dt) {
        if(move){
            myActor.runAnimation(dt);
            myActor.moveBy(100 * dt, 0);
        }
    }
}
