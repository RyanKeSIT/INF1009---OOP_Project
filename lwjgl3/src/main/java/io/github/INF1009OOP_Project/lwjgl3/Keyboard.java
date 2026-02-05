package io.github.INF1009OOP_Project.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import java.util.HashSet;
import java.util.Set;

public class Keyboard {

	// stores current pressed keys
	private Set<Integer> keysPressed;

	public Keyboard() {
		keysPressed = new HashSet<>();
	}

	public void update() {
		keysPressed.clear();

		// left movement
		if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
			keysPressed.add(Keys.LEFT);
		}

		// right movement
		if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
			keysPressed.add(Keys.RIGHT);
		}

		// shooting
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			keysPressed.add(Keys.SPACE);
		}
	}

	public boolean isKeyPressed(int key) {
		return keysPressed.contains(key);
	}

	public void keyPressed(int key) {
		keysPressed.add(key);
	}

	public void keyRelease(int key) {
		keysPressed.remove(key);
	}

	public boolean moveLeft() {
		return isKeyPressed(Keys.LEFT);
	}

	public boolean moveRight() {
		return isKeyPressed(Keys.RIGHT);
	}

	public boolean isShooting() {
		return isKeyPressed(Keys.SPACE);
	}
}