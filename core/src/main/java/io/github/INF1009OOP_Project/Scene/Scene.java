package io.github.INF1009OOP_Project.Scene;

public abstract class Scene {

	protected SceneManager sceneManager;

	public Scene(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}

	public abstract void update();

	public abstract void render();

	public abstract void dispose();

}
