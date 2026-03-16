package io.github.INF1009OOP_Project.Engine.Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.INF1009OOP_Project.Engine.Collision.CollisionManager;
import io.github.INF1009OOP_Project.Engine.Entities.Components.AIMovement;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Movement;
import io.github.INF1009OOP_Project.Engine.Entities.Components.PhysicsBody;
import io.github.INF1009OOP_Project.Engine.Entities.Components.PlayerMovement;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Renderable;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Transform;

public class EntityManager {
	private ArrayList<Entity> entityList;
    private CollisionManager collisionManager;
    private ArrayList<Entity> entitiesToAdd;
    private ArrayList<Entity> entitiesToRemove;
    
   private ComponentManager componentManager;
    
    //map entity to collidable adapter for collision system
    private Map<Entity, EntityCollidableAdapter> collidableAdapters;

    
	public EntityManager() {
		entityList = new ArrayList<>();
		
		componentManager = new ComponentManager();
		
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
		
		componentManager.addComponents(entity);
		
		
	}
	
	public void deleteEntity(Entity entity) {
		entitiesToRemove.add(entity);
		EntityCollidableAdapter adapter = collidableAdapters.remove(entity);
        if (adapter != null) {
            collisionManager.deleteEntity(adapter);
        }
        
        componentManager.removeComponents(entity);
	}
	
	
	public void updateEntities(float delta) {
	    componentManager.updateComponent(Movement.class, delta);
	    componentManager.updateComponent(PlayerMovement.class, delta);
	    componentManager.updateComponent(AIMovement.class, delta);
	    componentManager.updateComponent(PhysicsBody.class, delta);
	    componentManager.updateComponent(Transform.class, delta);
	    
	    
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
	    componentManager.clear();
	}
	
	public void draw(SpriteBatch batch) {
		batch.begin();
		componentManager.draw(batch);
	    batch.end();
	}
	
	
	
	
}
