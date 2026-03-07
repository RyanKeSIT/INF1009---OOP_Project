package io.github.INF1009OOP_Project.Engine.IO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import java.util.HashSet;
import java.util.Set;

public class Mouse {

	private int x;
	private int y;

	// stores pressed buttons
	private Set<Integer> mousePressed;
	
	private int lmbCD = 0;
	private int rmbCD = 0;
	
	public Mouse() {
		mousePressed = new HashSet<>();
	}

	public void update() {
		if(lmbCD > 0) {
			lmbCD --;
		}
		if(rmbCD > 0) {
			rmbCD --;
		}
		
		x = Gdx.input.getX();
		y = Gdx.input.getY();

		mousePressed.clear();

		if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
			if(lmbCD == 0) {
				mousePressed.add(Buttons.LEFT);				
				lmbCD = 10;
			}
		}
		else if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
			if(rmbCD == 0) {
				mousePressed.add(Buttons.RIGHT);				
				rmbCD = 10;
			}
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
	
}