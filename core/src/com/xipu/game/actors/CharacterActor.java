package com.xipu.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.xipu.game.*;
import com.xipu.game.components.CharacterGraphicsComponent;
import com.xipu.game.components.CollisionComponent;
import com.xipu.game.components.InputComponent;
import com.xipu.game.components.PlayerInputComponent;

public abstract class CharacterActor extends Actor implements CanCollide {

    protected CharacterGraphicsComponent graphicsComponent;
    protected InputComponent inputComponent;
    protected CollisionComponent collisionComponent;

    public CharacterActor(String path) {
        graphicsComponent = new CharacterGraphicsComponent(this, path);
        collisionComponent = new CollisionComponent(this, true,
                new Rectangle(
                        graphicsComponent.getX(),
                        graphicsComponent.getY(),
                        graphicsComponent.getWidth(),
                        graphicsComponent.getHeight()
                )
        );
    }

    @Override
    protected void positionChanged() {
        graphicsComponent.setPosition(getX(), getY());
        collisionComponent.setCollisionAreaPosition(getX(), getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
//        batch.begin();
        graphicsComponent.draw(batch);
        this.setDebug(true);
//        ShapeRenderer shape = new ShapeRenderer();
//        shape.begin(ShapeRenderer.ShapeType.Line);
////        this.drawDebugBounds(shape);
//        shape.end();
    }

    @Override
    public void moveBy(float x, float y) {
        super.moveBy(x, y);
        collisionComponent.moveCollisionAreaBy(x, y);
    }

    public void stopAnimation(PlayerInputComponent.DIRECTION direction) {
        graphicsComponent.resetToFirstFrame(direction);
    }

    public void runAnimation(float dt, PlayerInputComponent.DIRECTION direction) {
        graphicsComponent.update(dt, direction);
    }

}
