/*package io.github.INF1009OOP_Project.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet extends NonPlayableEntity {
	
	public Bullet() {
		super();
	}
	
	public Bullet(float x,float y,float height, float width,float speed) {
		super(x,y,height,width,new Texture(Gdx.files.internal("droplet.png")),speed);
	}
	@Override
	public void update() {
		this.setY(super.getY() + (super.getSpeed()));
	}
	
	@Override
	public void draw(SpriteBatch spritebatch) {
		spritebatch.draw(getTexture(), getX(), getY(), getWidth(), getHeight());
		//spritebatch.draw(getTexture(), getX(), getY(), getWidth(), getHeight(), 0, 0,(int)super.getWidth() , (int)super.getHeight(), false, true);
	}
}*/
