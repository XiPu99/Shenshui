package com.xipu.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class CharacterActor extends Actor {

    protected GraphicsComponent graphicsComponent;
    protected InputComponent inputComponent;
    protected CollisionComponent collisionComponent;

    public CharacterActor(String path) {
        graphicsComponent = new GraphicsComponent(this, path);
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

}
