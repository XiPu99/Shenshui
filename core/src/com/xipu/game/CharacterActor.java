package com.xipu.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class CharacterActor extends Actor implements CanCollide {

    protected GraphicsComponent graphicsComponent;
    protected InputComponent inputComponent;
    protected CollisionComponent collisionComponent;

    public CharacterActor(String path) {
        graphicsComponent = new GraphicsComponent(this, path);
        collisionComponent = new CollisionComponent(this, true,
                new Rectangle(
                        graphicsComponent.getX(),
                        graphicsComponent.getY(),
                        graphicsComponent.getWidth(),
                        graphicsComponent.getHeight()
                )
        );
        System.out.println(new Rectangle(
                graphicsComponent.getX(),
                graphicsComponent.getY(),
                graphicsComponent.getWidth(),
                graphicsComponent.getHeight()
        ));
    }

    @Override
    protected void positionChanged() {
        graphicsComponent.setPosition(getX(),getY());
        collisionComponent.setCollisionAreaPosition(getX(), getY());
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
    public void moveBy(float x, float y){
        super.moveBy(x, y);
        collisionComponent.moveCollisionAreaBy(x, y);
    }
}
