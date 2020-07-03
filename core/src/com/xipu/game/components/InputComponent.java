package com.xipu.game.components;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public abstract class InputComponent extends InputListener{

    Actor actor;

    public abstract void update(float dt);

}
