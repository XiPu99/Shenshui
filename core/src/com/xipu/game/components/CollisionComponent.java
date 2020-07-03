package com.xipu.game.components;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.xipu.game.CanCollide;
import com.xipu.game.actors.PlayerActor;


public class CollisionComponent {

    Actor canCollideActor;
    boolean canBlockMovement;
    Rectangle collisionArea;

    public CollisionComponent(Actor actor, boolean canBlockMovement, Rectangle collisionArea) {
        this.canCollideActor = actor;
        this.canBlockMovement = canBlockMovement;
        this.collisionArea = collisionArea;
    }

    /**
     * This method will check collision with every other actor in the stage
     * @return if is colliding with anything
     */
    public boolean checkCollisionHelper() {
        Array<Actor> actors = canCollideActor.getStage().getActors();
        for (Actor actor : actors) {
            if (actor == canCollideActor) { // avoid checking collision with self
                continue;
            }
            if (actor instanceof CanCollide) {
                if (this.checkCollisionWith((CanCollide) actor)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void moveCollisionAreaBy(float dx, float dy) {
        float x = collisionArea.getX();
        float y = collisionArea.getY();
        collisionArea.setPosition(x + dx, y + dy);
    }

    public boolean canMoveByWithoutCollision(float dx, float dy) {
        Rectangle newArea = new Rectangle(this.collisionArea);
        newArea.setPosition(collisionArea.x + dx, collisionArea.y + dy);
        return checkRectCollision(newArea);
    }

    public boolean checkRectCollision(Rectangle rectangle){
        Array<Actor> actors = canCollideActor.getStage().getActors();
        for (Actor actor : actors) {
            if (actor == canCollideActor) { // avoid checking collision with self
                continue;
            }
            if (actor instanceof CanCollide) {
                if (rectangle.overlaps(((CanCollide) actor).getCollisionComponent().collisionArea)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setCollisionAreaPosition(float x, float y) {
        collisionArea.setPosition(x, y);
    }


    public boolean checkCollisionWith(CanCollide actor) {
        boolean result = this.collisionArea.overlaps(actor.getCollisionComponent().collisionArea);

        if (result) {
            actor.onCollision(((PlayerActor) canCollideActor).getWalkDirection());
        } else {
            actor.leaveCollision();
        }

        return result;
    }

}
