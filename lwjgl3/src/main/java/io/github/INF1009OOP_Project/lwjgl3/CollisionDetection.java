package io.github.INF1009OOP_Project.lwjgl3;

import java.util.ArrayList;
import java.util.Objects;

public class CollisionDetection {
	public ArrayList<Collision> detect(ArrayList<Collidable> all){
		Objects.requireNonNull(all, "all cannot be null");
		ArrayList<Collision> collisions = new ArrayList<>();
		
		for(int i = 0; i < all.size(); i++) {
			Collidable a = all.get(i);
			
			for(int j = i+1; j<all.size(); j++) {
				Collidable b = all.get(j);
				if(a.getBounds().intersects(b.getBounds())) {
					collisions.add(new Collision(a,b));
				}
			}
			
		}
		
		return collisions;
	}
}
