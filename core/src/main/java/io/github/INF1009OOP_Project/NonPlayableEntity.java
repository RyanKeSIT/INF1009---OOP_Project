package io.github.INF1009OOP_Project;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NonPlayableEntity extends Entity implements iMoveable {

	private Texture texture;
	private float speed;
	
	public NonPlayableEntity() {
		super();
	}
	
	public NonPlayableEntity(float x,float y,float height, float width,Texture texture,float speed) {
		super(x,y,height,width);
		this.texture = texture;
		this.speed = speed;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public void draw(SpriteBatch spritebatch) {
		
	}
	
	public void getBounds(Bounds bounds) {
		
	}
	
	public void onCollision(Entity collidable) {
		
	}
	
	public void move(float x,float y,float delta) {
		float tempDropY = super.getY();
		tempDropY -= getSpeed() * delta;
		super.setX(x);
		super.setY(tempDropY);
	}

}
