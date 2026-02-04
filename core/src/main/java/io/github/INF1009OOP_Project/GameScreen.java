package io.github.INF1009OOP_Project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.ScreenUtils;


public class GameScreen extends ScreenAdapter {

	GameMaster game;

	public GameScreen(GameMaster game) {
		this.game = game;
	}

	@Override
	public void show() {
		// set input processor for button, once button is click, go to next scene by calling
		// game.setScreen(new EndScreen(game));

	}

	@Override
	public void render(float delta) {

		// test
		ScreenUtils.clear(0, 0, 0.2f, 1);

		if (Gdx.input.isKeyPressed(Keys.A)) {
			game.setScreen(new EndScreen(game));
		}

		game.batch.begin();
		game.font.draw(game.batch, "game screen", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
		game.font.draw(game.batch, "press letter A on your keyboard to win the game", Gdx.graphics.getWidth() * .25f,
				Gdx.graphics.getHeight() * .5f);
		game.batch.end();

	}

	@Override
	public void hide() {
		// Gdx.input.setInputProcessor(null);
	}
}
