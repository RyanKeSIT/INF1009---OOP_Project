package io.github.INF1009OOP_Project;

import java.util.ArrayList;

public class CollisionManager {
	private final ArrayList<Collidable> collidables = new ArrayList<>();
	private final CollisionDetection detection = new CollisionDetection();
	private final CollisionResolution resolution = new CollisionResolution();
	
	//add collidable entity to collidables arr
	public void registerEntity(Collidable e) {
		collidables.add(e);
	}
	//remove collidable
	public void deleteEntity(Collidable e) {
		collidables.remove(e);
	}
	//called in render(), loops through collisions and resolves
	public void update() {
		ArrayList<Collision> collisions = detection.detect(collidables);
		for(Collision c : collisions) {
			resolution.resolve(c);
		}
		
	}
}
