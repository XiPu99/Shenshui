package com.xipu.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class InputComponent extends InputListener {

    public static final int SPEED = 100;
    private MyActor myActor;
    private boolean downMove;
    private boolean leftMove;
    private boolean rightMove;
    private boolean upMove;
    private boolean disabled = false;

    public InputComponent(MyActor myActor) {
        this.myActor = myActor;
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
        super.enter(event, x, y, pointer, fromActor);
        StageUtility.changeCursor();
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        StageUtility.setDefaultCursor();
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (event.getButton() == Input.Buttons.LEFT) {
            System.out.println("Clicked");
            GUI.showDialog();
            disableMovement();
            reset();
        }
        return true;
    }

    public void update(float dt) {
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
        myActor.moveBy(dx, dy);
    }

    private void disableMovement() {
        disabled = true;
        reset();
    }

    public void reset() {
        upMove = false;
        downMove = false;
        leftMove = false;
        rightMove = false;
    }
}
