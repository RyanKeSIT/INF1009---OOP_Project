package io.github.INF1009OOP_Project.Entities.Components;

import io.github.INF1009OOP_Project.Entities.Entity;

public class AIMovement extends Movement{
	 public AIMovement(Entity entity, float speed) {
	        super(entity, speed);
	 }
	 
	 @Override  
	 public void move(float delta) {  
		 PhysicsBody pb = entity.get(PhysicsBody.class);  
	     if (pb == null) return;  

	     // move up from player position
	     pb.setVelocity(0f, speed);  
	 }
}
