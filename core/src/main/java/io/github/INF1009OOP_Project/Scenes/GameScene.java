package io.github.INF1009OOP_Project.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.EntityFactory;
import io.github.INF1009OOP_Project.Engine.Collision.*;
import io.github.INF1009OOP_Project.Engine.Entities.*;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Clickable;
import io.github.INF1009OOP_Project.Engine.Entities.Components.PhysicsBody;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Transform;
import io.github.INF1009OOP_Project.Engine.Entities.UI.Button;
import io.github.INF1009OOP_Project.Engine.Entities.UI.ClickEvent;
import io.github.INF1009OOP_Project.Engine.Entities.UI.Text;
import io.github.INF1009OOP_Project.Engine.IO.IOManager;
import io.github.INF1009OOP_Project.Engine.Scene.Scene;
import io.github.INF1009OOP_Project.Engine.Scene.SceneManager;

public class GameScene extends Scene {
	private ShapeRenderer shape;
	private Entity player, bullet;
	private Texture playerTexture;
	private Texture bulletTexture;
	private Texture obstacleTexture;

	private int qnCount = 10;

	public GameScene(SceneManager sceneManager, IOManager io, boolean[] options) {
		super(sceneManager, io);
		shape = new ShapeRenderer();
		playerTexture = new Texture(Gdx.files.internal("Ship.png"));
		bulletTexture = new Texture(Gdx.files.internal("Bullet.png"));
		obstacleTexture = new Texture(Gdx.files.internal("bucket.png"));

		if (!io.getSound().isMuted()) {
			io.getSound().soundOn();
		}
		initializeGame();
	}

	@Override
	public void update() {
		io.update();

		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			shoot();
			if (io.getSound() != null)
				io.getSound().playShootingSound();
		}

		if (io.getMouse().isMousePressed(Buttons.LEFT)) {
			for (Entity entity : entityManager.getEntities()) {
				Clickable c = entity.get(Clickable.class);
				if (c != null && c.isHover(io.getMouse().getX(), io.getMouse().getY())) {
					c.onClick();
				}
			}
		}

		float delta = Gdx.graphics.getDeltaTime();
		entityManager.updateEntities(delta);
	}

	@Override
	public void render() {
		ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
		float delta = Gdx.graphics.getDeltaTime();

		ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
		batch.begin();
		batch.end();

		entityManager.draw(batch);

		// visualize bounds hitbox
		shape.begin(ShapeRenderer.ShapeType.Line);

		for (Entity entity : entityManager.getEntities()) {
			PhysicsBody pb = entity.get(PhysicsBody.class);
			if (pb != null) {
				Bounds b = pb.getBounds();
				shape.rect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
			}
		}

		shape.end();
	}

	// methods

	private void initializeGame() {
		// clear old entities first
		entityManager.clearAll();

		// create player and bullet
		player = EntityFactory.createPlayer(100, 100, 100, 100, playerTexture, bulletTexture, entityManager, 100, io);
		bullet = EntityFactory.createObstacle(100, 400, 70, 70, obstacleTexture);

		entityManager.addEntity(player, true);
		entityManager.addEntity(bullet, true);

		// Pause button position (bottom right)
		float pw = 70;
		float ph = 70;
		float padding = 10;

		float px = Gdx.graphics.getWidth() - pw - padding;
		float py = padding; // bottom edge plus padding

		Button pauseButton = new Button(px, py, pw, ph, "Pause", 20, font, new ClickEvent() {
			@Override
			public void onClick() {
				System.out.println("Pause game");
				sceneManager.push(new PauseScene(sceneManager, io));
			}
		});
		entityManager.addEntity(pauseButton, false);

		// Settings button position (beside pause button)
		float sw = 90;
		float sh = 70;
		float sx = px - sw - padding;
		float sy = padding;

		Button settingButton = new Button(sx, sy, sw, sh, "Settings", 20, font, new ClickEvent() {
			@Override
			public void onClick() {
				System.out.println("Settings");
				sceneManager.push(new SettingScene(sceneManager, io));
			}
		});
		entityManager.addEntity(settingButton, false);
	}

	// acts as getter method for initializeGame so startscene can access
	public void resetGame() {
		initializeGame();
	}

	private void shoot() {
		Transform pt = player.get(Transform.class);
		if (pt == null)
			return;

		float bw = 70;
		float bh = 70;

		float bx = pt.getX() + pt.getWidth() / 2f - bw / 2f;
		float by = 75 + pt.getY() + pt.getHeight() / 2f - 10; // add 75 for it to spawn slightly above player
		Entity newBullet = EntityFactory.createBullet(bx, by, 70, 70, 300, bulletTexture, entityManager);

		entityManager.addEntity(newBullet, true);

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}