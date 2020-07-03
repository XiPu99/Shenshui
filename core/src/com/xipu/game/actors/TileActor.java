package com.xipu.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.xipu.game.CanCollide;
import com.xipu.game.components.CollisionComponent;
import com.xipu.game.components.PlayerInputComponent;
import com.xipu.game.components.TileGraphicsComponent;

public class TileActor extends Actor implements CanCollide {

    private TileGraphicsComponent graphicsComponent;
    private CollisionComponent collisionComponent;

    public TileActor(String path) {
        this.graphicsComponent = new TileGraphicsComponent(this, fix(path));
    }

    public void test(){
        this.setZIndex(0);
    }

    private String fix(String path){
        if(!path.endsWith(".png")){
            path += ".png";
        }
        return path;
    }

    @Override
    protected void positionChanged() {
        graphicsComponent.setPosition(getX(), getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        graphicsComponent.draw(batch);
        this.setDebug(true);
    }

    public void onCollide(){
        // play sound
    }

    @Override
    public void onCollision(PlayerInputComponent.DIRECTION direction) {

    }

    @Override
    public void leaveCollision() {

    }

    @Override
    public CollisionComponent getCollisionComponent() {
        return new CollisionComponent(this, true, new Rectangle());
    }
}
