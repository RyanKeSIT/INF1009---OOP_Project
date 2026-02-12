package io.github.INF1009OOP_Project;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class EntityManager {
	private ArrayList<Entity> entityList;
	
	public EntityManager() {
		entityList = new ArrayList<>();
	}
	
	public ArrayList<Entity> getEntities() {
	    return entityList;
	}
	
	public void addEntity(Entity e) {
		entityList.add(e);
	}
	
	public void deleteEntity(Entity e) {
		entityList.remove(e);
	}
	
	public void updateEntities() {
		for(Entity e: entityList) {
			e.update();
		}
	}
	
	public void draw(SpriteBatch batch, ShapeRenderer shape) {
		batch.begin();
	    for (Entity e : entityList) e.draw(batch);
	    batch.end();

	    shape.begin(ShapeRenderer.ShapeType.Filled);
	    for (Entity e : entityList) e.draw(shape);
	    shape.end();
	}
	
	public void moveAll() {
		float delta = Gdx.graphics.getDeltaTime();
		for(Entity e : entityList) {
			if(e instanceof iMoveable) {
				((iMoveable) e).move(e.getX(), e.getY(), delta);
			}
		}
	}
	
	
}
