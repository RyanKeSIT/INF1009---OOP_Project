package io.github.INF1009OOP_Project.Scene;

import java.util.ArrayList;

public class SceneManager {
	
    private Scene currentScene;
    
	private ArrayList<Scene> sceneList = new ArrayList<>();

    public void addScene(Scene s) {
        sceneList.add(s);
    }

    public void setScene(int index) {
    	// clean up old scene before switching scene
        //if (currentScene != null) {
            //currentScene.dispose();
        //}
    	
        // get new scene
        currentScene = sceneList.get(index);
    }

    public void render() {
        if (currentScene != null) {
            currentScene.update();
            currentScene.render();
        }
    }

    public void dispose() {
    	for (Scene scene : sceneList) {
            scene.dispose();
        }
    }
}