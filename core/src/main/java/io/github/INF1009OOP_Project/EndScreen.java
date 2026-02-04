package io.github.INF1009OOP_Project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class EndScreen extends ScreenAdapter {

	GameMaster game;

	public EndScreen(GameMaster game) {
		this.game = game;
	}

	@Override
	public void show() {
		
	
	}

	@Override
	public void render(float delta) {
		
		// test
		ScreenUtils.clear(0, 0, 0.2f, 1);
		
		game.batch.begin();
		game.font.draw(game.batch, "you win", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
		game.batch.end();
	}

	@Override
	public void hide() {
		// Gdx.input.setInputProcessor(null);
	
	}

}
