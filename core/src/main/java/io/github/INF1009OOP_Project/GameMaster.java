package io.github.INF1009OOP_Project;

import com.badlogic.gdx.ApplicationAdapter;
import io.github.INF1009OOP_Project.Scene.*;

public class GameMaster  {

	private SceneManager sceneManager;

	public GameMaster() {
		sceneManager = new SceneManager();

		// add scenes
		sceneManager.addScene(new StartScene(sceneManager));
		sceneManager.addScene(new GameScene(sceneManager));
		sceneManager.addScene(new EndScene(sceneManager));

		// set scene to start scene
		sceneManager.setScene(0);
	}

	public void render() {
		sceneManager.render();
	}

	public void dispose() {
		sceneManager.dispose();
	}
}
