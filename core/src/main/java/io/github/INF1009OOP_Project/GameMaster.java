package io.github.INF1009OOP_Project;

import io.github.INF1009OOP_Project.Engine.IO.IOManager;
import io.github.INF1009OOP_Project.Engine.Scene.*;
import io.github.INF1009OOP_Project.Scenes.StartScene;

public class GameMaster  {

	private SceneManager sceneManager;
	private IOManager io;

	public GameMaster() {
		sceneManager = new SceneManager();
		io = new IOManager();

		io.getSound().soundOn();
		
		// push start scene 
        sceneManager.push(new StartScene(sceneManager, io));
	}

	public void render() {
		io.update();
		sceneManager.update(); 
        sceneManager.render();
	
	}

	public void dispose() {
		while (sceneManager.peek() != null) {
			sceneManager.pop();
		}
	}
}