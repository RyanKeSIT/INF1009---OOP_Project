package io.github.INF1009OOP_Project.Entities;

public class Movement implements Component{
	//subclasses need access hence protected
    protected float speed;
    protected Entity entity;
    
    public Movement(Entity entity, float speed) {
        this.entity = entity;
        this.speed = speed;
    }
    
    public float getSpeed() {
        return speed;
    }
    
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    public void move(float delta) {
        // subclasses to override for specific movement behaviour
    }
    
    @Override
    public void update(float delta) {
        move(delta);
    }
}
