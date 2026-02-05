package io.github.INF1009OOP_Project.lwjgl3;

public class CollisionResolution {
	public void resolve(Collision collision) {
		Collidable a = collision.getA();//get first object involved in collision
		Collidable b = collision.getB();//second obj involved in collision

		//notify that both objects have collided
		a.onCollision(b);
		b.onCollision(a);
	}
}