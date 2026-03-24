package io.github.INF1009OOP_Project.Scenes;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.Engine.Entities.*;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Clickable;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Health;
import io.github.INF1009OOP_Project.Engine.Entities.Components.PhysicsBody;
import io.github.INF1009OOP_Project.Engine.Entities.Components.PlayerMovement;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Transform;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Movement;
import io.github.INF1009OOP_Project.Engine.Entities.Components.AIMovement;
import io.github.INF1009OOP_Project.Engine.Entities.UI.Button;
import io.github.INF1009OOP_Project.Engine.Entities.UI.ClickEvent;
import io.github.INF1009OOP_Project.Engine.Entities.UI.Text;
import io.github.INF1009OOP_Project.Engine.IO.IOManager;
import io.github.INF1009OOP_Project.Engine.Scene.Scene;
import io.github.INF1009OOP_Project.Engine.Scene.SceneManager;
import io.github.INF1009OOP_Project.Entities.UI.QuestionsFactory;
import io.github.INF1009OOP_Project.Logic.MathOperations;
import io.github.INF1009OOP_Project.Entities.PlayerFactory;
import io.github.INF1009OOP_Project.Entities.Components.RandomMovement;
import io.github.INF1009OOP_Project.Entities.Components.BounceCooldown;
import io.github.INF1009OOP_Project.Entities.Components.TransformSync;
import io.github.INF1009OOP_Project.Entities.BulletFactory;

public class GameScene extends Scene {
	//Textures and shapes
	private ShapeRenderer shape;
	private Texture playerTexture;
	private Texture bulletTexture;
	
	//Questions
	private ArrayList<String> questionOps;
	private QuestionsFactory qnsF;
	private ArrayList<Entity> currentQuestionEntities = new ArrayList<>();
	private int currentQuestionNumber = -1;
	private int qnCount = 10;
	//Player
	private Entity player;
	private float cooldownTimer = 1f;
	private int roundCount;
	//Obstacle
	private int enemyHealth=2;
	
	//UI
	private Text resultText;
	private Text scoreText;
	private Text timerText;
	
	//Game Variables
	private float resultTimer = 2f;
	private float consoleTimer;
	private int score = 0;
	private final int maxScore = 10; // max score is fixed
	private float gameTimer; // 10 mins = 600 sec
	private float roundTime = 18f; // 10 mins = 600 sec
	
	public GameScene(SceneManager sceneManager, IOManager io, ArrayList<String> questionOps) {
		super(sceneManager, io);

		this.questionOps = questionOps;
		shape = new ShapeRenderer();
		playerTexture = new Texture(Gdx.files.internal("Ship.png"));
		bulletTexture = new Texture(Gdx.files.internal("Bullet.png"));
		//register components that need updating per frame
		updateRegistry.register(Movement.class);
	    updateRegistry.register(PlayerMovement.class);
	    updateRegistry.register(AIMovement.class);
	    updateRegistry.register(PhysicsBody.class);
	    updateRegistry.register(RandomMovement.class);
	    updateRegistry.register(BounceCooldown.class);
	    updateRegistry.register(TransformSync.class);
	    updateRegistry.register(Transform.class);

		initializeGame();
	}

	@Override
	public void update() {
		io.update();

		if (io.getKeyboard().isKeyPressed(Keys.ENTER)) {
			// if player die or game ends, push end scene
			sceneManager.push(new EndScene(sceneManager, io, score));
		}

		// update cooldown timer
		cooldownTimer -= Gdx.graphics.getDeltaTime();

		if (io.getKeyboard().isKeyJustPressed(Keys.SPACE) && cooldownTimer <= 0) {
			shoot();
			if (io.getSound() != null)
				io.getSound().playShootingSound();
			cooldownTimer = 0.5f;
		}

		// update result text timer
		if (resultTimer > 0) {
			resultTimer -= Gdx.graphics.getDeltaTime();
			if (resultTimer <= 0) {
				resultText.setText("");
			}
		}

		if (io.getMouse().isMousePressed(Buttons.LEFT)) {
			for (Entity entity : entityManager.getEntities()) {
				Clickable c = entity.get(Clickable.class);
				if (c != null && c.isHover(io.getMouse().getX(), io.getMouse().getY())) {
					c.onClick();
				}
			}
		}

		// Update entities based on game tick time
		float delta = Gdx.graphics.getDeltaTime();
		entityManager.updateEntities(updateRegistry,delta);//update registry declared as protected in scene, inherits directly
		// update timer
		gameTimer -= delta;
		if (gameTimer <= 0) {
			sceneManager.push(new EndScene(sceneManager, io, score));
		}
		int mins = (int) (gameTimer / 60);
		int sec = (int) (gameTimer % 60);
		timerText.setText("Time: " + String.format("%02d:%02d", mins, sec));
		

		// Wait a bit until the question renders
		consoleTimer += delta;
		// format timer
		// Render every 3 seconds
		if (consoleTimer > 3) {
			// Check if there are no more questions, then show main menu (won!)
			if (qnsF.isEmpty()) {
				sceneManager.push(new EndScene(sceneManager, io, score));
				consoleTimer = 0;
				return;
			}
			// Only render questions if question is not active
			if (currentQuestionNumber == -1) {
				// Get 1 question first
				currentQuestionNumber = MathUtils.random(0, qnsF.getQuestionSize() - 1); // 0 - question size
				MathOperations ops = qnsF.getQuestionByNumber(currentQuestionNumber);

				// Generate question entities
				ArrayList<Entity> qEntities = qnsF.generateQuestionEntities(ops, qnsF.getQuestionSize(),enemyHealth, font,
						(enemy) -> {
							// Correct answer
							if (currentQuestionNumber == -1)
								return;

							Health h = enemy.get(Health.class);
							if (h != null) {
								h.takeDamage(1);

								if (h.isDead()) {
									qnsF.removeQuestionByNumber(currentQuestionNumber);
									currentQuestionNumber = -1;
									score++;
									scoreText.setText("Score: " + score + "/" + maxScore);
									
									showCorrectResult();
									clearQuestionEntities();
								}
							}
						}, (enemy) -> {
							// Wrong answer
							if (currentQuestionNumber == -1)
								return;

							Health h = enemy.get(Health.class);
							if (h != null) {
								h.takeDamage(1);

								if (h.isDead()) {
									Health ph = player.get(Health.class);
									if (ph != null) {
										ph.takeDamage(1);
										if (ph.isDead())
											sceneManager.push(new EndScene(sceneManager, io, score));
									}

									qnsF.removeQuestionByNumber(currentQuestionNumber);
									currentQuestionNumber = -1;

									showWrongResult();

									clearQuestionEntities();
								}
							}
						});
				gameTimer=roundTime;
				
				if(roundCount==4) {
					increaseDifficulty();
					showWarning();
				}
				roundCount++;
				// Add to both tracking list and entity manager
				for (Entity e : qEntities) {
					currentQuestionEntities.add(e);
					entityManager.addEntity(e, e.has(PhysicsBody.class));
				}
			}

			// Reset 5 second timer
			consoleTimer = 0;
		}
	}

	@Override
	public void render() {
		ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

		batch.begin();
		batch.end();

		entityManager.draw(batch);

		// Render health bars
		shape.begin(ShapeRenderer.ShapeType.Filled);
		for (Entity entity : entityManager.getEntities()) {
			Health h = entity.get(Health.class);
			Transform t = entity.get(Transform.class);

			if (h != null && t != null) {
				float healthPercent = (float) h.getCurrentHealth() / h.getMaxHealth();
				float barWidth = t.getWidth() * 0.8f;
				float barHeight = 5;
				float barX = t.getX() + (t.getWidth() - barWidth) / 2f;

				// Red background bar
				shape.setColor(Color.RED);
				// Positioning of the health bar
				float barY;
				if (entity == player)
					barY = t.getY(); // Bottom for player
				else
					barY = t.getY() + t.getHeight() + 5; // Top for enemies
				shape.rect(barX, barY, barWidth, barHeight);

				// Green health bar
				shape.setColor(Color.GREEN);
				shape.rect(barX, barY, barWidth * healthPercent, barHeight);
			}
		}
		shape.end();
	}

	// to display right or wrong result
	private void showCorrectResult() {
		resultText.setText("Correct!");
		resultText.setTextColor(Color.GREEN);
		resultTimer = 2f;

		if (io.getSound() != null) {
			io.getSound().playCorrectSound();
		}
	}

	private void showWrongResult() {
		resultText.setText("Wrong!");
		resultText.setTextColor(Color.RED);
		resultTimer = 2f;

		if (io.getSound() != null) {
			io.getSound().playWrongSound();
		}
	}
	private void showWarning() {
		resultText.setText("Get Ready! Things are about to get fast!");
		resultText.setTextColor(Color.RED);
		resultTimer = 2f;
	}
	//Increase difficulty
	private void increaseDifficulty() {
		roundTime=12f;
		enemyHealth =1;
		cooldownTimer=0.25f;
		PlayerMovement pm = player.get(PlayerMovement.class);
		if(pm!=null) {
			pm.setSpeed(pm.getSpeed()+100);
		}
	}

	// methods
	private void initializeGame() {
		// clear old entities first
		entityManager.clearAll();
		
		// Clear old question entities
		currentQuestionEntities.clear();
		currentQuestionNumber = -1;
		roundCount=0;
		enemyHealth=2;
		gameTimer =roundTime;
		// Generate questions and populate
		qnsF = new QuestionsFactory(qnCount, questionOps);

		// Create player
		player = new PlayerFactory(100, 0, 100, 100, playerTexture, entityManager, 300, io).createEntity();
		entityManager.addEntity(player, true);

		// timer
		timerText = new Text(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 50, 200, 50, "Time: 00:00", 25,
				Color.WHITE, font);
		entityManager.addEntity(timerText, false);
		// score
		scoreText = new Text(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 80, 200, 50, "Score: 0/10", 25,
				Color.WHITE, font);
		entityManager.addEntity(scoreText, false);

		// display result text
		resultText = new Text(Gdx.graphics.getWidth() / 2f - 100, Gdx.graphics.getHeight() / 2f, 200, 50, "", 25,
				Color.WHITE, font);
		entityManager.addEntity(resultText, false);

		Button pauseButton = new Button(20, 400, 70, 70, "Pause", 20, font, new ClickEvent() {
			@Override
			public void onClick() {
				System.out.println("Pause game");
				sceneManager.push(new PauseScene(sceneManager, io));
			}
		});
		entityManager.addEntity(pauseButton, false);

		Button settingButton = new Button(100, 400, 70, 70, "Settings", 20, font, new ClickEvent() {
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

		float bw = 50;
		float bx = pt.getX() + pt.getWidth() / 2f - bw / 2f;
		float by = 75 + pt.getY() + pt.getHeight() / 2f - 10; // add 75 for it to spawn slightly above player
		Entity newBullet = new BulletFactory(bx, by, bw, bw, 300, bulletTexture, entityManager).createEntity();// new
																												// bullet
																												// factory
		entityManager.addEntity(newBullet, true);
	}

	private void clearQuestionEntities() {
		for (Entity e : currentQuestionEntities) {
			entityManager.deleteEntity(e);
		}
		currentQuestionEntities.clear();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}
