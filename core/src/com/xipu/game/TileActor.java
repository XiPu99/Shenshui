package com.xipu.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TileActor extends Actor {

    private GraphicsComponent graphicsComponent;
    private CollisionComponent collisionComponent;

    public TileActor(String path) {
        this.graphicsComponent = new GraphicsComponent(this, fix(path));
    }

    public TileActor buildCollision(){
        return this;
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
    public void draw(Batch batch, float parentAlpha) {
        graphicsComponent.draw(batch);
        this.setDebug(true);
        ShapeRenderer shape = new ShapeRenderer();
        shape.begin(ShapeRenderer.ShapeType.Line);
        this.drawDebugBounds(shape);
    }

}
