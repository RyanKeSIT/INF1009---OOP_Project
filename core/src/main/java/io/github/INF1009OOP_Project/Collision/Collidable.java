package io.github.INF1009OOP_Project.Collision;

public interface Collidable {
	Bounds getBounds();
	void onCollision(Collidable entity);//collision game logic in entity
}
