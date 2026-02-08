package io.github.INF1009OOP_Project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.utils.ScreenUtils;

public class StartScene extends Scene {
	
	public StartScene(SceneManager sceneManager) {
		super(sceneManager);
	}

	@Override
	public void update() {
		// switch to game scene
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			sceneManager.setScene(1); 
		}
	}

	@Override
	public void render() {
		ScreenUtils.clear(1, 0, 0, 0);
	}

	@Override
	public void dispose() {
		
	}

}
