package io.github.INF1009OOP_Project;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NonPlayableEntity extends Entity implements iMoveable , Collidable{

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
		spritebatch.draw(getTexture(), super.getX(),super.getY(),super.getWidth(),super.getHeight());
	}
	
	public Bounds getBounds() {
		return new Bounds(getX(),getY(),getWidth(),getHeight());
	}
	
	public void onCollision(Collidable collidable) {
		System.out.println("Collided with playable");
	}
	
	public void move(float x,float y,float delta) {
		
	}

}
