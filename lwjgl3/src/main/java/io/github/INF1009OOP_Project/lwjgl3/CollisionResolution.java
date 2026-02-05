package io.github.INF1009OOP_Project.lwjgl3;

public class CollisionResolution {
	public void resolve(Collision collision) {
		Collidable a = collision.getA();
		Collidable b = collision.getB();

		a.onCollision(b);
		b.onCollision(a);
	}
}