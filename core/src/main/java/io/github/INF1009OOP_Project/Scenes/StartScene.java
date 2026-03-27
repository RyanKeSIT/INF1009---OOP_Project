
package io.github.INF1009OOP_Project.Scenes;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.Engine.Entities.Entity;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Clickable;
import io.github.INF1009OOP_Project.Engine.Entities.UI.Text;
import io.github.INF1009OOP_Project.Engine.Entities.UI.Button;
import io.github.INF1009OOP_Project.Engine.Entities.UI.ClickEvent;
import io.github.INF1009OOP_Project.Engine.IO.IOManager;
import io.github.INF1009OOP_Project.Engine.Scene.Scene;
import io.github.INF1009OOP_Project.Engine.Scene.SceneManager;

public class StartScene extends Scene {

	public StartScene(SceneManager sceneManager, IOManager io) {
		super(sceneManager, io);

		entityManager.addEntity(new Text(200, 400, 200, 50, "Math Invaders", 50, Color.WHITE, font), false);
		entityManager.addEntity(new Button(100, 300, 100, 50, "Start", 20, font, new ClickEvent() {
			@Override
			public void onClick() {
				System.out.println("Start button clicked");
				startNewGame();
			}
		}), false);

		Button settingButton = new Button(100, 200, 100, 50, "Settings", 20, font, new ClickEvent() {
			@Override
			public void onClick() {
				System.out.println("Settings");
				// push setting scene, start scene is below the stack
				sceneManager.push(new SettingScene(sceneManager, io));
			}
		});
		entityManager.addEntity(settingButton, false);
	}

	@Override
	public void update() {

		entityManager.updateEntities(updateRegistry, 0);

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

	private void startNewGame() {
		// push mode selection scene, start scene is below the stack
		sceneManager.push(new ModeSelectionScene(sceneManager, io));
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