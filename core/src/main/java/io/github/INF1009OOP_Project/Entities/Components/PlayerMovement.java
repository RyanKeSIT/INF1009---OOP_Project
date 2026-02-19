package io.github.INF1009OOP_Project.Entities.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import io.github.INF1009OOP_Project.IOManager;
import io.github.INF1009OOP_Project.Entities.Entity;

public class PlayerMovement extends Movement{
	
	private IOManager io;
	
	public PlayerMovement(Entity entity, float speed) {
        super(entity, speed);
    }
	
	public void setIOManager(IOManager io) {
        this.io = io;
    }
	
	@Override
    public void move(float delta) {
        Transform transform = entity.get(Transform.class);//entity from movement
        PhysicsBody pb = entity.get(PhysicsBody.class);
        if (transform == null ||pb ==null) return;
        
        float vx = 0f;
        
        // Move left
        if (io.getKeyboard().isKeyPressed(Keys.LEFT) && transform.getX() > 50) {
            vx = -speed;
        }

        // Move right
        if (io.getKeyboard().isKeyPressed(Keys.RIGHT) && transform.getX() < (Gdx.graphics.getWidth() - 50)) {
            vx = speed;
        }
        
     // Apply velocity , physics body will move the transform
        pb.setVelocity(vx, 0f);
        
    }
}
