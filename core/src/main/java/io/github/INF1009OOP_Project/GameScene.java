package io.github.INF1009OOP_Project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScene extends Scene {

	public GameScene(SceneManager sceneManager) {
        super(sceneManager);
    }

    @Override
    public void update() {
    	// switch to end scene
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            sceneManager.setScene(2); 
        }
    }

    @Override
    public void render() {
    	ScreenUtils.clear(0,1,0,0);
    }

    @Override
    public void dispose() {
       
    }
}
