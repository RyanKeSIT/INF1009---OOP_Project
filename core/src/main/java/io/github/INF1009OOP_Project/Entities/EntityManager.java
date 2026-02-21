package io.github.INF1009OOP_Project.Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.INF1009OOP_Project.Collision.CollisionManager;
import io.github.INF1009OOP_Project.Entities.Components.PhysicsBody;

public class EntityManager {
	private ArrayList<Entity> entityList;
    private CollisionManager collisionManager;
    private ArrayList<Entity> entitiesToAdd;
    private ArrayList<Entity> entitiesToRemove;
    
    //map entity to collidable adapter for collision system
    private Map<Entity, EntityCollidableAdapter> collidableAdapters;

    
	public EntityManager() {
		entityList = new ArrayList<>();
        collidableAdapters = new HashMap<>();
        this.collisionManager = new CollisionManager();
        entitiesToAdd = new ArrayList<>(); //This is done to add a buffer list for entities being added
        entitiesToRemove = new ArrayList<>(); //This is done to add a buffer list for entities being removed
	}
	
	public ArrayList<Entity> getEntities() {
	    return entityList;
	}
	
	public void addEntity(Entity entity,boolean collision) {
		entitiesToAdd.add(entity);
		if (collision) {
            // Check entity has PhysicsBody
            if (!entity.has(PhysicsBody.class)) {
                throw new IllegalArgumentException("Need PhysicsBody for collision");
            }
            
            // Create adapter
            EntityCollidableAdapter adapter = new EntityCollidableAdapter(entity);
            collidableAdapters.put(entity, adapter);
            
            // Register adapter 
            collisionManager.registerEntity(adapter);  
        }
	}
	
	public void deleteEntity(Entity entity) {
		entitiesToRemove.add(entity);
		EntityCollidableAdapter adapter = collidableAdapters.remove(entity);
        if (adapter != null) {
            collisionManager.deleteEntity(adapter);
        }
	}
	
	public void updateEntities(float delta) {
	    for (Entity entity : entityList) {
	        entity.update(delta);
	    }

	    collisionManager.update();

	    // Add queued entities
	    entityList.addAll(entitiesToAdd);
	    entitiesToAdd.clear();

	    // Remove queued entities
	    for (Entity entity : entitiesToRemove) {
	        entityList.remove(entity);
	        EntityCollidableAdapter adapter = collidableAdapters.remove(entity);
	        if (adapter != null) {
	            collisionManager.deleteEntity(adapter);
	        }
	    }
	    entitiesToRemove.clear();
	}
	
	public void clearAll() {
	    // Unregister all collision adapters
	    for (EntityCollidableAdapter adapter : collidableAdapters.values()) {
	        collisionManager.deleteEntity(adapter);
	    }
	    collidableAdapters.clear();
	    
	    // Clear entity list
	    entityList.clear();	    
	}
	
	public void draw(SpriteBatch batch) {
		batch.begin();
	    for (Entity entity : entityList) entity.draw(batch);
	    batch.end();
	}
	
	
	
	
}
