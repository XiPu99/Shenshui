package com.xipu.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MyActor extends Actor {

    GraphicsComponent graphicsComponent;
    InputComponent inputComponent;
    CollisionComponent collisionComponent;

    public MyActor(String path){
        graphicsComponent = new GraphicsComponent(this, path);
        inputComponent = new InputComponent(this);
        this.addListener(inputComponent);
    }

    @Override
    protected void positionChanged() {
        graphicsComponent.setPosition(getX(),getY());
        super.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        graphicsComponent.draw(batch);
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

    private void update(float dt){
        inputComponent.update(dt);
    }

    void stopAnimation(InputComponent.DIRECTION direction){
        graphicsComponent.resetToFirstFrame(direction);
    }

    void runAnimation(float dt, InputComponent.DIRECTION direction){
        graphicsComponent.update(dt, direction);
    }

}
