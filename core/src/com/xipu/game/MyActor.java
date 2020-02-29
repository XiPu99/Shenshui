package com.xipu.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class MyActor extends Actor {
    Texture walkSheet;
    Sprite currentFrame;
    Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
    float stateTime = 0;
    boolean move = false;

    public MyActor(String path){
        walkSheet = new Texture(Gdx.files.internal(path));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / 4,
                walkSheet.getHeight() / 4);

        currentFrame = new Sprite(tmp[0][0]);
//        currentFrame.setScale(3);
        currentFrame.setOrigin(0, 0);
        currentFrame.setScale(3);
        this.setBounds(currentFrame.getX(),currentFrame.getY(),currentFrame.getWidth()*3,currentFrame.getHeight()*3);

        walkAnimation = new Animation<TextureRegion>(0.25f, tmp[0]);


        addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
//                if(keycode == Input.Keys.D){
//                    MoveByAction mba = new MoveByAction();
//                    mba.setAmount(100f,0f);
//                    mba.setDuration(5f);
//
//                    MyActor.this.addAction(mba);
//                }
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
                        stateTime = 0;
                        currentFrame.setRegion(walkAnimation.getKeyFrame(stateTime, true));
                        break;
                }
                return true;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                System.out.println(x+","+y);
                Pixmap pm = new Pixmap(Gdx.files.internal("gcc_sprite_sheet.png"));
                Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
                pm.dispose();
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

    }

    @Override
    protected void positionChanged() {
        currentFrame.setPosition(getX(),getY());
        super.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        currentFrame.draw(batch);
        this.setDebug(true);
        ShapeRenderer shape = new ShapeRenderer();
        shape.begin(ShapeRenderer.ShapeType.Line);
        this.drawDebugBounds(shape);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        update(delta);
    }

    private void update(float delta){
        if(move){
            stateTime += delta;
            if(stateTime >= 1f) stateTime = 0f;
//            currentFrame = new Sprite(walkAnimation.getKeyFrame(stateTime, true));
            currentFrame.setRegion(walkAnimation.getKeyFrame(stateTime, true));
//            currentFrame.setPosition(this.getX(), this.getY());
//            currentFrame.setScale(3);
//            currentFrame.setOrigin(0, 0);
            currentFrame.translateX(100*Gdx.graphics.getDeltaTime());
            MyActor.this.moveBy(100*Gdx.graphics.getDeltaTime(), 0);
        }
    }
}
