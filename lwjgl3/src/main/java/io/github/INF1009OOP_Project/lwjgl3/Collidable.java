package io.github.INF1009OOP_Project.lwjgl3;

public interface Collidable {
	public Bounds getBounds();
	public void onCollision(Collidable entity);
}
