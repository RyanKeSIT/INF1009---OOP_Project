package io.github.INF1009OOP_Project.Engine.Entities.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import io.github.INF1009OOP_Project.Engine.Entities.*;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Transform;
import io.github.INF1009OOP_Project.Engine.IO.IOManager;
import io.github.INF1009OOP_Project.Engine.IO.Mouse;

public class Slider extends Entity {

	// texture used to draw bar and knob
	private static final Texture white1x1 = new Texture("white1x1.png");

	private Transform tr;
	private float value = 0.5f;
	private boolean dragging = false;

	private float knobWidth = 12f;

	private Color barColor = Color.BLACK;
	private Color knobColor = Color.WHITE;

	// listener interface for value change
	public interface ValueChangeListener {
		void onValueChanged(float newValue);
	}

	private ValueChangeListener listener;

	public Slider(float x, float y, float w, float h, float initial, ValueChangeListener l) {

		super();

		// transform component for position and size
		this.add(new Transform(x, y, w, h));
		this.tr = this.get(Transform.class);

		this.listener = l;
		setValue(initial);
	}

	public void update(IOManager io) {

		Mouse mouse = io.getMouse();

		// get mouse position
		float mx = mouse.getX();
		float my = mouse.getY();

		boolean leftPressed = mouse.isMousePressed(Input.Buttons.LEFT);
		boolean leftHoldDown = mouse.isButtonDown(Input.Buttons.LEFT);

		// check if mouse is over the slider bar
		boolean hover = mx > tr.getX() && mx < tr.getX() + tr.getWidth() && my > tr.getY()
				&& my < tr.getY() + tr.getHeight();

		// start dragging when mouse clicks slider
		if (leftPressed && hover) {
			dragging = true;
			setValueFromMouse(mx);
		}
		// continue dragging while holding mouse
		else if (dragging && leftHoldDown) {
			setValueFromMouse(mx);
		}
		// stop dragging when mouse released
		else if (dragging && !leftHoldDown) {
			dragging = false;
		}
	}

	// convert mouse position to slider value
	private void setValueFromMouse(float mx) {

		float relative = (mx - tr.getX()) / tr.getWidth();
		if (relative < 0)
			relative = 0;
		if (relative > 1)
			relative = 1;
		setValue(relative);
	}

	// draw slider bar and knob
	public void draw(SpriteBatch sb) {

		sb.setColor(barColor);
		sb.draw(white1x1, tr.getX(), tr.getY() + tr.getHeight() / 2f - 4, tr.getWidth(), 8);

		float knobX = tr.getX() + value * tr.getWidth() - knobWidth / 2f;
		float knobY = tr.getY() + tr.getHeight() / 2f - knobWidth / 2f;

		sb.setColor(knobColor);
		sb.draw(white1x1, knobX, knobY, knobWidth, knobWidth);

		sb.setColor(Color.WHITE);
	}

	public float getValue() {
		return value;
	}

	// set slider value and notify listener
	public void setValue(float value) {

		this.value = value;

		if (listener != null) {
			listener.onValueChanged(value);
		}
	}

	public void setValueChangeListener(ValueChangeListener l) {
		this.listener = l;
	}
}