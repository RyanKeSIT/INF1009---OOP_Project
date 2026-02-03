package io.github.INF1009OOP_Project.lwjgl3;

public class Bounds {
	private float x;
	private float y;
	private float height;
	private float width;
	
	public Bounds(float x, float y, float h, float w) {
		this.x = x;
		this.y = y;
		this.height = h;
		this.width = w;
	}
	public boolean intersects(Bounds e) {
		return false;
	}
	
}
