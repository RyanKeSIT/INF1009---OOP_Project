package io.github.INF1009OOP_Project.Scene;

import java.util.ArrayList;

public class SceneManager {
	
    private Scene currentScene;
    
	private ArrayList<Scene> sceneList = new ArrayList<>();

    public void addScene(Scene s) {
        sceneList.add(s);
    }
    
    public Scene getScene(int index) {
        return sceneList.get(index);
    }

    public void setScene(int index) {
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