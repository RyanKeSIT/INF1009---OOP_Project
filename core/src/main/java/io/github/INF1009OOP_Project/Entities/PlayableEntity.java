package io.github.INF1009OOP_Project.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.INF1009OOP_Project.Collision.*;

public class PlayableEntity extends Entity implements iMoveable,Collidable{
	private Texture texture;
	private float speed;
	//private Keyboard keyboard(?)
	public PlayableEntity() {
		super();
	}
	public PlayableEntity(float x,float y,float height, float width,Texture texture,float speed) {
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
		System.out.println("Collided with non playable");

	}
	
	@Override
	public void move(float x,float y,float delta) {
		if (Gdx.input.isKeyPressed(Keys.LEFT) && super.getX()>50) {
			float tempX = super.getX();
			tempX -= getSpeed() * delta;
			super.setX(tempX);
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT) && super.getY()<(Gdx.graphics.getWidth()-50)) {
			float tempX = getX();
			tempX += getSpeed() * delta;
			super.setX(tempX);
		}
	}

}
