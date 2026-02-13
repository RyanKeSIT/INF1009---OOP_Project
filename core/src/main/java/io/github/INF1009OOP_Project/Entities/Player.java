/*package io.github.INF1009OOP_Project.Entities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends PlayableEntity{
	private ArrayList<Entity> activeBullets = new ArrayList<>();
	private float bulletSpeed = 10;
	

	public Player() {
		super();
	}
	
	public Player(float x,float y,float height, float width,Texture texture,float speed) {
		super(x,y,height,width,texture,speed);
	}
	
	public void shoot() {
		Entity newBullet = new Bullet(super.getX(), super.getY(), super.getWidth(), super.getHeight(), this.getBulletSpeed());
		activeBullets.add(newBullet);
		
	}
	
	@Override
	public void update() {
		for (int i = activeBullets.size() - 1; i >= 0; i--) {
		    Entity bullet = activeBullets.get(i);
		    if (bullet.getY() > Gdx.graphics.getHeight()) {
		    	activeBullets.remove(i); 
		    } else {
		        ((Bullet) bullet).update();
		    }
		}
	}
	
	@Override
	public void draw(SpriteBatch sb) {
		for(Entity b : this.activeBullets) {
	        ((Bullet) b).draw(sb);
		}
		sb.draw(this.getTexture(), this.getX(), this.getY());
	}

	public float getBulletSpeed() {
		return bulletSpeed;
	}

	public void setBulletSpeed(float bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}
}*/
