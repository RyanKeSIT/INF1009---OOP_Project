package io.github.INF1009OOP_Project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.utils.ScreenUtils;

public class StartScreen extends ScreenAdapter {
	GameMaster game;

	public StartScreen(GameMaster game) {
		this.game = game;
	}

	@Override
	public void show() {
		// set input processor for button, once button is click, go to next scene by calling
		// game.setScreen(new GameScreen(game));

	}

	@Override
	public void render(float delta) {

		// test
		ScreenUtils.clear(0, 0, 0.2f, 1);

		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
		}

		game.batch.begin();
		game.font.draw(game.batch, "start screen", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
		game.font.draw(game.batch, "click anywhere to start", Gdx.graphics.getWidth() * .25f,
				Gdx.graphics.getHeight() * .5f);
		game.batch.end();
	}

	@Override
	public void hide() {
		// Gdx.input.setInputProcessor(null);

	}

}
