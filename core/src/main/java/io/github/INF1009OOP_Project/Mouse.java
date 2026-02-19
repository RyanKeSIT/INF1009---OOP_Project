package io.github.INF1009OOP_Project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import java.util.HashSet;
import java.util.Set;

public class Mouse {

	private int x;
	private int y;

	// stores pressed buttons
	private Set<Integer> mousePressed;
//	private Set<Integer> mouseJustPressed;

	public Mouse() {
		mousePressed = new HashSet<>();
//		mouseJustPressed = new HashSet<>();
	}

	public void update() {
		x = Gdx.input.getX();
		y = Gdx.input.getY();
		
//		mouseJustPressed.clear();
//		mouseJustPressed.addAll(mousePressed);
		mousePressed.clear();

		if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
			mousePressed.add(Buttons.LEFT);
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
	
//	public boolean isMouseJustPressed(int button) {
//        return mousePressed.contains(button) && ! mouseJustPressed.contains(button);
//    }
}