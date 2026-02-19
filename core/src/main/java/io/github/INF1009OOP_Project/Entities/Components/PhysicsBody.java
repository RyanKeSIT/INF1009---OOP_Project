package io.github.INF1009OOP_Project.Entities.Components;

import io.github.INF1009OOP_Project.Collision.Bounds;
import io.github.INF1009OOP_Project.Entities.Entity;

//This component is to handle the physics-based movement of the entity
public class PhysicsBody implements Component{
	private Entity entity;
	
	private float velocityX;
    private float velocityY;
    private float mass;
    
    public PhysicsBody(Entity entity) {
        this(entity, 0, 0, 1.0f);
    }
    
    public PhysicsBody(Entity entity, float velocityX, float velocityY, float mass) {
        this.entity = entity;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.mass = mass;
    }
    
    @Override
    public void update(float delta) {
        Transform t = entity.get(Transform.class);
        if (t == null) return;

        // Apply velocity to position
        t.setX(t.getX() + velocityX * delta);
        t.setY(t.getY() + velocityY * delta);
    }
    
    public float getVelocityX() {
    	return velocityX;
    }
    
    public float getVelocityY() {
    	return velocityY;
    }

    public void setVelocityX(float vx) {
    	this.velocityX = vx;
    }
    
    public void setVelocityY(float vy) {
    	this.velocityY = vy;
    }
    
    public void setVelocity(float vx, float vy) {
        this.velocityX = vx;
        this.velocityY = vy;
    }
    
    public float getMass() {
    	return mass;
    }
    
    public void setMass(float mass) {
    	this.mass = mass;
    }
    
    public Bounds getBounds() {
        Transform transform = entity.get(Transform.class);
        if (transform == null) {
            return new Bounds(0, 0, 0, 0);
        }
        return new Bounds(
            transform.getX(), 
            transform.getY(), 
            transform.getWidth(), 
            transform.getHeight()
        );
    }
    
    
}
