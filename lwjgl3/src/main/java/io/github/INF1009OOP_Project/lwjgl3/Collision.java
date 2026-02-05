package io.github.INF1009OOP_Project.lwjgl3;

import java.util.Objects;
//collision stores pair of collided objects 
//created in CollisionDetection if collidables intersect and temporarily stored in an arraylist
public class Collision {
	private final Collidable a;
	private final Collidable b;
	
	public Collision(Collidable a, Collidable b) {
		this.a = Objects.requireNonNull(a, "a cannot be null");
		this.b = Objects.requireNonNull(b, "b cannot be null");
	}
	
	public Collidable getA() { return a; }
	public Collidable getB() { return b; }
}
