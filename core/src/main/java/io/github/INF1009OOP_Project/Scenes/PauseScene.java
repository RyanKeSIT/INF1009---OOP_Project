package io.github.INF1009OOP_Project.Scenes;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.Engine.Entities.*;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Clickable;
import io.github.INF1009OOP_Project.Engine.Entities.UI.Button;
import io.github.INF1009OOP_Project.Engine.Entities.UI.ClickEvent;
import io.github.INF1009OOP_Project.Engine.IO.IOManager;
import io.github.INF1009OOP_Project.Engine.Scene.Scene;
import io.github.INF1009OOP_Project.Engine.Scene.SceneManager;

public class PauseScene extends Scene {

	public PauseScene(SceneManager sceneManager, IOManager io) {
		super(sceneManager, io);

		// Resume Game Button
		entityManager.addEntity(new Button(100, 300, 100, 50, "Resume", 20, font, new ClickEvent() {
			@Override
			public void onClick() {
				System.out.println("Resume game");
				// remove pause scene, game scene underneath will resume
				sceneManager.pop();
			}
		}), false);

		entityManager.addEntity(new Button(100, 200, 150, 50, "Main Menu", 20, font, new ClickEvent() {
			@Override
			public void onClick() {
				System.out.println("Go to Main Menu");
				sceneManager.push(new StartScene(sceneManager, io));
			}
		}), false);
	}

	@Override
	public void update() {
		entityManager.updateEntities(updateRegistry, 0);

		if (io.getKeyboard().isKeyPressed(Keys.ENTER)) {
			sceneManager.pop();
		}

		if (io.getMouse().isMousePressed(Buttons.LEFT)) {
			for (Entity entity : entityManager.getEntities()) {
				Clickable c = entity.get(Clickable.class);
				if (c != null) {
					if (c.isHover(io.getMouse().getX(), io.getMouse().getY())) {
						c.onClick();
					}
				}
			}
		}
	}

	@Override
	public void render() {

		ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

		entityManager.draw(batch);
	}

	@Override
	public boolean isOverlay() {
		return true;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}