package io.github.INF1009OOP_Project.lwjgl3;
import java.util.Objects;

public class Bounds {
	private float x;
	private float y;
	private float height;
	private float width;
	
	//construct a box around entity for collision detection
	public Bounds(float x, float y, float h, float w) {
		this.x = x;
		this.y = y;
		this.height = h;
		this.width = w;
	}
	
	public float getX() {
    	return x;
    }
    public float getY() {
    	return y; 
    }
    public float getWidth() {
    	return width;
    }
    public float getHeight() {
    	return height;
    }
    
    //aabb intersect check
    public boolean intersects(Bounds e) {
    	Objects.requireNonNull(e,"entity bounds cannot be null");
    	return this.x < e.x + e.width &&
                this.x + this.width > e.x &&
                this.y < e.y + e.height &&
                this.y + this.height > e.y;
    }
	
}
