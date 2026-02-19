package io.github.INF1009OOP_Project;

import io.github.INF1009OOP_Project.Entities.IO.IOManager;
import io.github.INF1009OOP_Project.Scene.*;

public class GameMaster  {

	private SceneManager sceneManager;
	private IOManager io;

	public GameMaster() {
		sceneManager = new SceneManager();
		io = new IOManager();

		// add scenes
		sceneManager.addScene(new StartScene(sceneManager, io));
		sceneManager.addScene(new GameScene(sceneManager, io));
		sceneManager.addScene(new EndScene(sceneManager, io));
		sceneManager.addScene(new PauseScene(sceneManager, io));

		// set scene to start scene
		sceneManager.setScene(0);
	}

	public void render() {
		io.update();
		sceneManager.render();
	}

	public void dispose() {
		sceneManager.dispose();
	}
}
