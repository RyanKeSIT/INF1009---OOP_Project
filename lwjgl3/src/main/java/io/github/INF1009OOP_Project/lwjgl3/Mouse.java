package io.github.INF1009OOP_Project.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import java.util.HashSet;
import java.util.Set;

public class Mouse {

	private int x;
	private int y;

	// stores pressed buttons
	private Set<Integer> mousePressed;

	public Mouse() {
		mousePressed = new HashSet<>();
	}

	public void update() {
		x = Gdx.input.getX();
		y = Gdx.input.getY();
		mousePressed.clear();

		if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
			mousePressed.add(Buttons.LEFT);
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean mousePressed(int button) {
		return mousePressed.contains(button);
	}
}