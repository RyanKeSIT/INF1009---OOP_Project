package io.github.INF1009OOP_Project;

import com.badlogic.gdx.ApplicationAdapter;

public class GameMaster extends ApplicationAdapter {

	private SceneManager sceneManager;

	@Override
	public void create() {
		sceneManager = new SceneManager();

		// add scenes
		sceneManager.addScene(new StartScene(sceneManager));
		sceneManager.addScene(new GameScene(sceneManager));
		sceneManager.addScene(new EndScene(sceneManager));

		// set scene to start scene
		sceneManager.setScene(0);
	}

	@Override
	public void render() {
		sceneManager.render();
	}

	@Override
	public void dispose() {
		sceneManager.dispose();
	}
}
