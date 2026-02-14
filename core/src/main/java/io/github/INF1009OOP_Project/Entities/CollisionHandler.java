package io.github.INF1009OOP_Project.Entities;
import io.github.INF1009OOP_Project.Collision.Collidable;

public class CollisionHandler implements Component{
	private Entity entity;
    private CollisionCallback callback;
    
    @FunctionalInterface
    public interface CollisionCallback {//functinoal interface can be implemented using lambda function
        void onCollide(Entity self, Entity other);
    }
    
    public CollisionHandler(Entity entity, CollisionCallback callback) {
        this.entity = entity;
        this.callback = callback;
    }
    
    public void onCollision(Collidable other) {
        if (callback == null) {
            return;
        }
        
        // Unwrap the adapter to get the actual entity
        Entity otherEntity = null;
        if (other instanceof EntityCollidableAdapter) {
            otherEntity = ((EntityCollidableAdapter) other).getEntity();
        }
        
        // Call the callback with both entities
        callback.onCollide(entity, otherEntity);
    }
    
}
