package io.github.INF1009OOP_Project.Entities;

import com.badlogic.gdx.graphics.Texture;
//use the static methods in scene to create our entities

import io.github.INF1009OOP_Project.Entities.Components.AIMovement;
import io.github.INF1009OOP_Project.Entities.Components.PhysicsBody;
import io.github.INF1009OOP_Project.Entities.Components.PlayerMovement;
import io.github.INF1009OOP_Project.Entities.Components.Renderable;
import io.github.INF1009OOP_Project.Entities.Components.Transform;

public class EntityFactory {
	
	public static Entity createPlayer(float x, float y, float width, float height, Texture texture, float speed) {
		Entity player = new Entity();
		//add components
		player.add(new Transform(x,y,width,height));
		player.add(new PhysicsBody(player));
		player.add(new Renderable(player,texture));
		player.add(new PlayerMovement(player,speed));
		//collision system
		player.add(new CollisionHandler(player, (self, other) -> {
            System.out.println("Player collided with something");
            
        }));
		return player;
	}
	
	public static Entity createObstacle(float x, float y, float width, float height, Texture texture) {
		Entity obstacle = new Entity();
		obstacle.add(new Transform(x,y,width,height));
		obstacle.add(new PhysicsBody(obstacle));
        obstacle.add(new Renderable(obstacle, texture));
        
        obstacle.add(new CollisionHandler(obstacle, (self, other) -> {
            System.out.println("Something coolided with obstacle");
        }));
		return obstacle;
	}
	
	public static Entity createBullet(float x,float y,float width, float height,float speed,Texture texture,EntityManager entityManager) {
		Entity bullet =  new Entity();
		bullet.add(new Transform(x,y,width,height));
		bullet.add(new PhysicsBody(bullet));
		bullet.add(new Renderable(bullet, texture));
		bullet.add(new AIMovement(bullet,speed));
		bullet.add(new CollisionHandler(bullet, (self, other) -> {
	        entityManager.deleteEntity(self);   // remove bullet on collision with other
	    }));
		return bullet;
	}
}
