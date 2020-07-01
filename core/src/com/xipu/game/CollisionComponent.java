package com.xipu.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;


public class CollisionComponent {

    Actor canCollideActor;
    boolean canBlockMovement;
    Rectangle collisionArea;

    public CollisionComponent(Actor actor, boolean canBlockMovement, Rectangle collisionArea) {
        this.canCollideActor = actor;
        this.canBlockMovement = canBlockMovement;
        this.collisionArea = collisionArea;
    }

    public boolean checkCollisionHelper() {
        Array<Actor> actors = canCollideActor.getStage().getActors();
        for (Actor actor: actors){
            if (actor == canCollideActor){ // don't check collision with self
                continue;
            }
            if (actor instanceof CanCollide){
                if (this.checkCollisionWith((CanCollide) actor)){
                    return true;
                }
            }
        }
        return false;
    }

    public void moveCollisionAreaBy(float dx, float dy){
        float x = collisionArea.getX();
        float y = collisionArea.getY();
        collisionArea.setPosition(x + dx, y + dy);
    }

    public void setCollisionAreaPosition(float x, float y){
        collisionArea.setPosition(x, y);
    }

//    public boolean canCollideWith(CharacterActor myActor){
//        return collisionArea.overlaps(myActor.collisionComponent.collisionArea);
//    }

    public boolean checkCollisionWith(CanCollide actor){
        return this.collisionArea.overlaps(actor.getCollisionComponent().collisionArea);
    }

}
