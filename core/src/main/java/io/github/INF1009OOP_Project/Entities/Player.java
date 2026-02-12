package io.github.INF1009OOP_Project.Entities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends PlayableEntity{
	private ArrayList<Entity> activeBullets = new ArrayList<>();
	

	public Player() {
		super();
	}
	
	public Player(float x,float y,float height, float width,Texture texture,float speed) {
		super(x,y,height,width,texture,speed);
	}
	
	public void shoot(float delta,SpriteBatch spritebatch) {
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			Entity newBullet = new Bullet(super.getX(), super.getY(), super.getWidth(), super.getHeight(), super.getSpeed());
		    activeBullets.add(newBullet);
		}
		for (int i = activeBullets.size() - 1; i >= 0; i--) {
		    Entity bullet = activeBullets.get(i);
		    float tempY = bullet.getY();
		        
		    if (tempY > Gdx.graphics.getHeight()) {
		    	activeBullets.remove(i); 
		    } else {
		        ((Bullet) bullet).moveBullet(delta,tempY);
		        ((Bullet) bullet).drawBullet(spritebatch);
		    }
		}
	}
}
