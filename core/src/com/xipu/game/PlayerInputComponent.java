package com.xipu.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class PlayerInputComponent extends InputComponent implements MouseInteractable {

    public static final int SPEED = 100;
    private PlayerActor myActor;
    private boolean downMove;
    private boolean leftMove;
    private boolean rightMove;
    private boolean upMove;
    private boolean disabled = false;

    public PlayerInputComponent(PlayerActor myActor) {
        this.myActor = myActor;
    }

    @Override
    public void onEnter() {
        StageUtility.changeCursor();
    }

    @Override
    public void onExit() {
        StageUtility.setDefaultCursor();
    }

    @Override
    public void onRightClick() {
        System.out.println("Clicked");
        GUI.showDialog(myActor.getStage());
        disableMovement();
        reset();
    }

    public enum DIRECTION {
        DOWN,
        RIGHT,
        UP,
        LEFT
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        if (disabled) {
            return true;
        }
        switch (keycode) {
            case Input.Keys.S:
                downMove = true;
                break;
            case Input.Keys.A:
                leftMove = true;
                break;
            case Input.Keys.D:
                rightMove = true;
                break;
            case Input.Keys.W:
                upMove = true;
                break;
            case Input.Keys.ESCAPE:
                disableMovement();
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(InputEvent event, int keycode) {
        if (disabled) {
            return true;
        }
        switch (keycode) {
            case Input.Keys.S:
                downMove = false;
                myActor.stopAnimation(DIRECTION.DOWN);
                break;
            case Input.Keys.A:
                leftMove = false;
                myActor.stopAnimation(DIRECTION.LEFT);
                break;
            case Input.Keys.D:
                rightMove = false;
                myActor.stopAnimation(DIRECTION.RIGHT);
                break;
            case Input.Keys.W:
                upMove = false;
                myActor.stopAnimation(DIRECTION.UP);
                break;
        }
        return true;
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

    @Override
    public void update(float dt) {
        if(disabled) return;
        float dx = 0, dy = 0;
        if (downMove) {
            myActor.runAnimation(dt, DIRECTION.DOWN);
            dy = -SPEED * dt;
        }

        if (leftMove) {
            myActor.runAnimation(dt, DIRECTION.LEFT);
            dx = -SPEED * dt;
        }

        if (rightMove) {
            myActor.runAnimation(dt, DIRECTION.RIGHT);
            dx = SPEED * dt;
        }

        if (upMove) {
            myActor.runAnimation(dt, DIRECTION.UP);
            dy = SPEED * dt;
        }

        if (!myActor.checkIfThereIsAnyCollision(dx, dy)){
            myActor.moveBy(dx, dy);
        }
        else if(myActor.checkIfThereIsAnyCollision()){ // possibly due to floating point precision
            System.out.println(dx + "," + dy);
            myActor.moveBy(-dx/10, -dy/10);
        }
    }


    private void disableMovement() {
        disabled = true;
//        reset();
    }

    public void reset() {
        upMove = false;
        downMove = false;
        leftMove = false;
        rightMove = false;
    }

    public DIRECTION getWalkDirection(){
        if (downMove) {
            return DIRECTION.DOWN;
        }
        else if (leftMove) {
            return DIRECTION.LEFT;
        }
        else if (rightMove) {
            return DIRECTION.RIGHT;
        }
        else if (upMove){
            return DIRECTION.UP;
        }
        else{
            return null;
        }
    }
}
