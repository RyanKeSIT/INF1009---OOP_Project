package io.github.INF1009OOP_Project.Scenes;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.Engine.Entities.Entity;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Clickable;
import io.github.INF1009OOP_Project.Engine.Entities.UI.*;
import io.github.INF1009OOP_Project.Engine.IO.IOManager;
import io.github.INF1009OOP_Project.Engine.Scene.Scene;
import io.github.INF1009OOP_Project.Engine.Scene.SceneManager;

public class SettingScene extends Scene {

	private Slider volSlider;
	private Text volText;
	private Button soundToggle;

	public SettingScene(SceneManager sceneManager, IOManager io) {
		super(sceneManager, io);

		// back to game button
		entityManager.addEntity(new Button(20, 20, 80, 40, "Back", 20, font, new ClickEvent() {
			@Override
			public void onClick() {
				System.out.println("Back to game");
				sceneManager.pop();
			}
		}), false);

		// current vol from sound manager
		float initialVol = 0.5f;
		if (io.getSound() != null) {
			initialVol = io.getSound().getVolume();
		}

		volText = new Text(150, 350, 300, 30, "Volume: " + Math.round(initialVol * 100), 18, Color.WHITE, font);

		volSlider = new Slider(150, 300, 300, 40, initialVol, new Slider.ValueChangeListener() {
			@Override
			public void onValueChanged(float newValue) {

				// update sound manager volume
				if (io.getSound() != null) {
					io.getSound().setVolume(newValue);
				}

				volText.setText("Volume: " + Math.round(newValue * 100));
			}
		});

		entityManager.addEntity(volSlider, false);
		entityManager.addEntity(volText, false);

		String toggleLabel = "Sound: On";

		if (io.getSound() == null) {
			toggleLabel = "Sound: N/A";
		} else if (io.getSound().isMuted()) {
			toggleLabel = "Sound: Off";
		}

		soundToggle = new Button(120, 20, 140, 40, toggleLabel, 14, font, new ClickEvent() {

			@Override
			public void onClick() {

				if (io.getSound() == null)
					return;

				if (io.getSound().isMuted()) {
					io.getSound().soundOn();
					soundToggle.setText("Sound: On");
				} else {
					io.getSound().soundOff();
					soundToggle.setText("Sound: Off");
				}
			}
		});
		entityManager.addEntity(soundToggle, false);
	}

	@Override
	public void update() {
		io.update();

		float mx = io.getMouse().getX();
		float my = io.getMouse().getY();

		volSlider.update(io);

		if (io.getMouse().isMousePressed(Buttons.LEFT)) {
			for (Entity entity : entityManager.getEntities()) {
				Clickable c = entity.get(Clickable.class);
				if (c != null && c.isHover(mx, my)) {
					c.onClick();
				}
			}
		}
		entityManager.updateEntities(updateRegistry, 0);
	}

	@Override
	public void render() {
		ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
		entityManager.draw(batch);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
}