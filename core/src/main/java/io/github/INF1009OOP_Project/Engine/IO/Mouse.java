package io.github.INF1009OOP_Project.Engine.IO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import java.util.HashSet;
import java.util.Set;

public class Mouse {

	private int x;
	private int y;

	// stores buttons pressed this frame
	private Set<Integer> mousePressed;

	public Mouse() {
		mousePressed = new HashSet<>();
	}

	public void update() {
		x = Gdx.input.getX();
		y = Gdx.input.getY();

		mousePressed.clear();

		if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
			mousePressed.add(Buttons.LEFT);
		}
		if (Gdx.input.isButtonJustPressed(Buttons.RIGHT)) {
			mousePressed.add(Buttons.RIGHT);
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return Gdx.graphics.getHeight() - y;
	}

	public boolean isMousePressed(int button) {
		return mousePressed.contains(button);
	}

	public boolean isButtonDown(int button) {
		return Gdx.input.isButtonPressed(button);
	}
}