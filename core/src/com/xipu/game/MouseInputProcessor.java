package com.xipu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MouseInputProcessor extends InputAdapter {

    Batch batch;
    int screenX;
    int screenY;

    public MouseInputProcessor(Batch batch){
        this.batch = batch;
    }


    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();



        this.screenX = screenX;
        this.screenY = screenY;

//        System.out.println();
        return super.mouseMoved(screenX, screenY);
    }
}
