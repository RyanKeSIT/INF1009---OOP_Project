package io.github.INF1009OOP_Project.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class PlayerMovement extends Movement{
	
	public PlayerMovement(Entity entity, float speed) {
        super(entity, speed);
    }
	
	@Override
    public void move(float delta) {
        Transform transform = entity.get(Transform.class);//entity from movement
        PhysicsBody pb = entity.get(PhysicsBody.class);
        if (transform == null ||pb ==null) return;
        
        float vx = 0f;
        
        // Move left
        if (Gdx.input.isKeyPressed(Keys.LEFT) && transform.getX() > 50) {
            vx = -speed;
        }

        // Move right
        if (Gdx.input.isKeyPressed(Keys.RIGHT) && transform.getX() < (Gdx.graphics.getWidth() - 50)) {
            vx = speed;
        }
        
     // Apply velocity , physics body will move the transform
        pb.setVelocity(vx, 0f);
        
    }
}
