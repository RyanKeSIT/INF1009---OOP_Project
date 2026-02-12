package io.github.INF1009OOP_Project.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.INF1009OOP_Project.Collision.*;

public class Entity {
	private float x = 0;
	private float y = 0;
	private float height = 0;
	private float width = 0;
	private Bounds bounds;
	public Entity() {
		this.setX(0);
		this.setY(0);
		this.setHeight(0);
		this.setWidth(0);
	}
	public Entity(float x, float y, float w, float h) {
		this.setX(x);
		this.setY(y);
		this.setHeight(h);
		this.setWidth(w);
		this.bounds = new Bounds(x,y,w,h);
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	
	public Bounds getBounds() {
		return this.bounds;
	}
	public void update() {
		
	}
	public void draw(SpriteBatch sb) {
		
	}
	public void draw(ShapeRenderer sr) {
		
	}
	
	public void onCollision(Collidable entity) {
		
	}
	
}
