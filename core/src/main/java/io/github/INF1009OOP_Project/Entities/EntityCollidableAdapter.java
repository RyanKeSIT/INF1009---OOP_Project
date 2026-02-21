package io.github.INF1009OOP_Project.Entities;

import io.github.INF1009OOP_Project.Collision.Bounds;
import io.github.INF1009OOP_Project.Collision.Collidable;
import io.github.INF1009OOP_Project.Entities.Components.CollisionHandler;
import io.github.INF1009OOP_Project.Entities.Components.PhysicsBody;

public class EntityCollidableAdapter implements Collidable{
    private Entity entity;

    public EntityCollidableAdapter(Entity entity) {
        this.entity = entity;
    }
    
    @Override
    public Bounds getBounds() {
        PhysicsBody physicsBody = entity.get(PhysicsBody.class);
        if (physicsBody == null) {
            throw new IllegalStateException(
                "Entity must have PhysicsBody component to be collidable"
            );
        }
        return physicsBody.getBounds();
    }
    
    @Override
    public void onCollision(Collidable other) {
        CollisionHandler handler = entity.get(CollisionHandler.class);
        if (handler != null) {
            handler.onCollision(other);
        }
        // If no CollisionHandler, collision is ignored 
    }
    
    //get wrapped entity
    public Entity getEntity() {
        return entity;
    }
}
