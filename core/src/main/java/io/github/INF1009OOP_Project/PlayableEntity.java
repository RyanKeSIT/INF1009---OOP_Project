package io.github.INF1009OOP_Project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayableEntity extends Entity implements iMoveable{
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
		
	}
	
	public void getBounds(Bounds bounds) {
		
	}
	
	public void onCollision(Entity collidable) {
		
	}
	
	@Override
	public void move(float x,float y,float speed,float delta) {
		setSpeed(speed);
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
