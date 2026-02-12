package io.github.INF1009OOP_Project.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;

public class EndScene extends Scene{

	public EndScene(SceneManager sceneManager) {
		super(sceneManager);
	}

	@Override
	public void update() {
		// switch to start scene
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            sceneManager.setScene(0); 
        }	
	}

	@Override
	public void render() {
		ScreenUtils.clear(0,0,1,0);
	}

	@Override
	public void dispose() {
			
	}
}
