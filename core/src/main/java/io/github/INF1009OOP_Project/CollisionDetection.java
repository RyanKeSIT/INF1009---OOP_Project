package io.github.INF1009OOP_Project;

import java.util.ArrayList;
import java.util.Objects;

public class CollisionDetection {
	public ArrayList<Collision> detect(ArrayList<Collidable> all){//takes in collidable array from collisionManager
		Objects.requireNonNull(all, "all cannot be null");
		ArrayList<Collision> collisions = new ArrayList<>();//create empty collisions array to store collisions
		
		for(int i = 0; i < all.size(); i++) {//loops through collidable array
			Collidable a = all.get(i);//gets first obj
			
			for(int j = i+1; j<all.size(); j++) {//loop to check if first object collided with any of the remaining object
				Collidable b = all.get(j);
				if(a.getBounds().intersects(b.getBounds())) {//if bounds intersect add both objects as a collision object into collisions
					collisions.add(new Collision(a,b));
				}
			}
			
		}
		
		return collisions;//returns collisions array that contains all collisions recorded in collidable
	}
}
