package io.github.INF1009OOP_Project.Scenes;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.Engine.Entities.Entity;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Clickable;
import io.github.INF1009OOP_Project.Engine.Entities.UI.Button;
import io.github.INF1009OOP_Project.Engine.Entities.UI.ClickEvent;
import io.github.INF1009OOP_Project.Engine.Entities.UI.Text;
import io.github.INF1009OOP_Project.Engine.IO.IOManager;
import io.github.INF1009OOP_Project.Engine.Scene.Scene;
import io.github.INF1009OOP_Project.Engine.Scene.SceneManager;
import io.github.INF1009OOP_Project.Entities.ModeCheckbox;

public class ModeSelectionScene extends Scene {
	private ModeCheckbox add, sub, mult, div;
	private ModeCheckbox[] options = new ModeCheckbox[4];
	private Button startBtn;

	public ModeSelectionScene(SceneManager sceneManager, IOManager io) {
		super(sceneManager, io);
		// TODO Auto-generated constructor stub
		entityManager.addEntity(new Text(200, 400, 200, 50, "Mode select", 50, Color.WHITE, font), false);

		float checkboxSize = (Gdx.graphics.getWidth() - 50) / 4;
		add = new ModeCheckbox(10, Gdx.graphics.getHeight() / 2, checkboxSize, checkboxSize, "+", 40, font);
		sub = new ModeCheckbox(10 + checkboxSize + 10, Gdx.graphics.getHeight() / 2, checkboxSize, checkboxSize, "-",
				40, font);
		mult = new ModeCheckbox(10 + (checkboxSize + 10) * 2, Gdx.graphics.getHeight() / 2, checkboxSize, checkboxSize,
				"x", 40, font);
		div = new ModeCheckbox(10 + (checkboxSize + 10) * 3, Gdx.graphics.getHeight() / 2, checkboxSize, checkboxSize,
				"/", 40, font);

		entityManager.addEntity(add, false);
		entityManager.addEntity(sub, false);
		entityManager.addEntity(mult, false);
		entityManager.addEntity(div, false);

		startBtn = new Button(500, 100, 100, 50, "Start", 20, font, new ClickEvent() {
			@Override
			public void onClick() {

				// Only push options with checked inputs
				ArrayList<String> MCOps = new ArrayList<>();
				for (int i = 0; i < options.length; i++)
					if (options[i].checked())
						MCOps.add(options[i].getText());

				// push game scenes
				sceneManager.push(new GameScene(sceneManager, io, MCOps));

			}
		});
		entityManager.addEntity(startBtn, false);

	}

	@Override
	public void update() {
		entityManager.updateEntities(updateRegistry, 0);
		options[0] = add;
		options[1] = sub;
		options[2] = mult;
		options[3] = div;

		if (options[0].checked() || options[1].checked() || options[2].checked() || options[3].checked()) {
			startBtn.show();
		} else {
			startBtn.hide();
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
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
