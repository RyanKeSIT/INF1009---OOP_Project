package io.github.INF1009OOP_Project.Scenes;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.Engine.Entities.*;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Clickable;
import io.github.INF1009OOP_Project.Engine.Entities.UI.Button;
import io.github.INF1009OOP_Project.Engine.Entities.UI.ClickEvent;
import io.github.INF1009OOP_Project.Engine.Entities.UI.Text;
import io.github.INF1009OOP_Project.Engine.IO.IOManager;
import io.github.INF1009OOP_Project.Engine.Scene.Scene;
import io.github.INF1009OOP_Project.Engine.Scene.SceneManager;

public class EndScene extends Scene {

	private int finalScore;
	private String message;

	public EndScene(SceneManager sceneManager, IOManager io, int score) {
		super(sceneManager, io);
		this.finalScore = score;

		entityManager.addEntity(new Text(100, 400,
				300, 50,
				"Final Score: " + finalScore + "/10", 30, Color.WHITE, font), false);

		if (finalScore < 5) {
			message = "You did not pass. Try harder next time.";
		} else {
			message = "You pass. Good game!";
		}

		entityManager.addEntity(new Text(100, 350, 300, 50,
				message, 25, Color.WHITE, font), false);

		entityManager.addEntity(new Button(200, 250, 150, 50, "Main Menu", 20, font, new ClickEvent() {
			@Override
			public void onClick() {
				System.out.println("Main menu");
				// remove end scene
				sceneManager.pop();
				// remove game scene
				sceneManager.pop();
				// push start scene
				sceneManager.push(new StartScene(sceneManager, io));
			}
		}), false);
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