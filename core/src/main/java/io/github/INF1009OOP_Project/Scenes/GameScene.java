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
import io.github.INF1009OOP_Project.EntityFactory;
import io.github.INF1009OOP_Project.MathOperations;
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
import io.github.INF1009OOP_Project.Entities.UI.ModeCheckbox;
import io.github.INF1009OOP_Project.Entities.UI.QuestionsFactory;

public class GameScene extends Scene {
    private ShapeRenderer shape;
    private Texture playerTexture;
    private Texture bulletTexture;
    private ModeCheckbox[] questionOps;
    private QuestionsFactory qnsF;
    private ArrayList<Entity> currentQuestionEntities = new ArrayList<>();
    private int currentQuestionNumber = -1;
    private Entity player;
    private float consoleTimer;
    private int qnCount = 10;
    private float cooldownTimer = 2f;

    private int score = 0;
    // max score is fixed
    private final int maxScore = 10;
    // 10 mins = 600 sec
    private float gameTimer = 600f;
    private Text scoreText;
    private Text timerText;

    public GameScene(SceneManager sceneManager, IOManager io, ModeCheckbox[] options) {
        super(sceneManager, io);

        this.questionOps = options;
        shape = new ShapeRenderer();
        playerTexture = new Texture(Gdx.files.internal("Ship.png"));
        bulletTexture = new Texture(Gdx.files.internal("Bullet.png"));

        if (!io.getSound().isMuted()) {
            io.getSound().soundOn();
        }

        initializeGame();
    }

    @Override
    public void update() {
        io.update();

        if (io.getKeyboard().isKeyPressed(Keys.ENTER)) {
            // if player die or game ends, push end scene
            sceneManager.push(new EndScene(sceneManager, io, score));
        }

        cooldownTimer -= Gdx.graphics.getDeltaTime();

        if (io.getKeyboard().isKeyJustPressed(Keys.SPACE) && cooldownTimer <= 0) {
            // if (Gdx.input.isKeyJustPressed(Keys.SPACE) && cooldownTimer <= 0) {
            shoot();
            if (io.getSound() != null)
                io.getSound().playShootingSound();
            cooldownTimer = 2f; // reset cooldown timer to 2s
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
        entityManager.updateEntities(delta);
        // update timer
        gameTimer -= delta;
        if (gameTimer <= 0) {
            sceneManager.push(new EndScene(sceneManager, io, score));
        }

        // format timer
        int mins = (int) (gameTimer / 60);
        int sec = (int) (gameTimer % 60);
        timerText.setText("Time: " + String.format("%02d:%02d", mins, sec));

        // Wait a bit until the question renders
        consoleTimer += delta;
        // Render every 3 seconds
        if (consoleTimer > 3) {
            // Check if there are no more questions, then show main menu (won!)
            if (qnsF.getQuestions().isEmpty()) {
                sceneManager.push(new EndScene(sceneManager, io, score));
                consoleTimer = 0;
                return;
            }

            // Only render questions if question is not active
            if (currentQuestionNumber == -1) {
                // Get 1 question first
                currentQuestionNumber = MathUtils.random(0, qnsF.getQuestions().size() - 1); // 0 - question size
                MathOperations ops = qnsF.getQuestions().get(currentQuestionNumber);

                // Generate question entities
                ArrayList<Entity> qEntities = qnsF.generateQuestionEntities(
                        ops,
                        qnsF.getQuestions().size(),
                        font,
                        () -> {
                            if (currentQuestionNumber != -1) {
                                qnsF.getQuestions().remove(currentQuestionNumber);
                                currentQuestionNumber = -1;

                                // increment score
                                score++;
                                scoreText.setText("Score: " + score + "/" + maxScore);

                                clearQuestionEntities();
                            }
                        },
                        () -> {
                            if (currentQuestionNumber != -1) {
                                qnsF.getQuestions().remove(currentQuestionNumber);
                                currentQuestionNumber = -1;

                                clearQuestionEntities();
                            }
                        });

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

        // Clear old question entities
        currentQuestionEntities.clear();
        currentQuestionNumber = -1;
        // Generate questions
        qnsF = new QuestionsFactory(qnCount, questionOps);

        // Create player
        player = EntityFactory.createPlayer(100, 0, 100, 100, playerTexture, entityManager, 200, io);
        // Register player entity
        entityManager.addEntity(player, true);

        // timer
        timerText = new Text(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 50, 200, 50, "Time: 10:00", 25,
                Color.WHITE, font);
        entityManager.addEntity(timerText, false);

        // score
        scoreText = new Text(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 80, 200, 50, "Score: 0/10", 25,
                Color.WHITE, font);
        entityManager.addEntity(scoreText, false);

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

        float bw = 70;
        float bx = pt.getX() + pt.getWidth() / 2f - bw / 2f;
        float by = 75 + pt.getY() + pt.getHeight() / 2f - 10; // add 75 for it to spawn slightly above player
        Entity newBullet = EntityFactory.createBullet(bx, by, 70, 70, 300, bulletTexture, entityManager);

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
