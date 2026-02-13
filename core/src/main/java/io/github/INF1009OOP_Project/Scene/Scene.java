package io.github.INF1009OOP_Project.Scene;

import io.github.INF1009OOP_Project.IOManager;

public abstract class Scene {

	protected SceneManager sceneManager;
	protected IOManager io;

	public Scene(SceneManager sceneManager, IOManager io) {
		this.sceneManager = sceneManager;
		this.io = io;
	}

	public abstract void update();

	public abstract void render();

	public abstract void dispose();

}
