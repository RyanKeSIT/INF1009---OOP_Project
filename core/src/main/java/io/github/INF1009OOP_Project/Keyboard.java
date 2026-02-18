package io.github.INF1009OOP_Project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import java.util.HashSet;
import java.util.Set;

public class Keyboard {

	// stores current pressed key
	private Set<Integer> keysPressed;
//	private Set<Integer> keysJustPressed;

	public Keyboard() {
		keysPressed = new HashSet<>();
//		keysJustPressed = new HashSet<>();
	}

	public void update() {
//		keysJustPressed.clear();
//		keysJustPressed.addAll(keysPressed);
		keysPressed.clear();

		// left movement
		if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
			keysPressed.add(Keys.LEFT);
		}

		// right movement
		if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
			keysPressed.add(Keys.RIGHT);
		}

		// for shooting
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			keysPressed.add(Keys.SPACE);
		}
		
		// for controls
		if (Gdx.input.isKeyPressed(Keys.ENTER)) {
            keysPressed.add(Keys.ENTER);
        }

        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            keysPressed.add(Keys.ESCAPE);
        }
	}

	public boolean isKeyPressed(int key) {
		return keysPressed.contains(key);
	}
	
//    public boolean isKeyJustPressed(int key) {
//        return keysPressed.contains(key) && ! keysJustPressed.contains(key);
//    }

	public void keyPressed(int key) {
		keysPressed.add(key);
	}

	public void keyRelease(int key) {
		keysPressed.remove(key);
	}

	public boolean moveLeft() {
		return Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A);
	}

	public boolean moveRight() {
		return isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D);
	}

	public boolean isShooting() {
		return isKeyPressed(Keys.SPACE);
	}
}