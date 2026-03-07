package io.github.INF1009OOP_Project.Scenes;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.EntityFactory;
import io.github.INF1009OOP_Project.MathOperations;
import io.github.INF1009OOP_Project.Engine.Collision.*;
import io.github.INF1009OOP_Project.Engine.Entities.*;
import io.github.INF1009OOP_Project.Engine.Entities.Components.CollisionHandler;
import io.github.INF1009OOP_Project.Engine.Entities.Components.PhysicsBody;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Transform;
import io.github.INF1009OOP_Project.Engine.Entities.Components.AIMovement;
import io.github.INF1009OOP_Project.Engine.Entities.UI.Text;
import io.github.INF1009OOP_Project.Engine.IO.IOManager;
import io.github.INF1009OOP_Project.Engine.Scene.Scene;
import io.github.INF1009OOP_Project.Engine.Scene.SceneManager;

public class GameScene extends Scene {
    private ShapeRenderer shape;
    private Texture playerTexture;
    private Texture bulletTexture;
    private Texture obstacleTexture;
    private ArrayList<MathOperations> questions = new ArrayList<>();
    private int currentQuestionNumber = -1;
    private Entity player;
    private float consoleTimer;

    public GameScene(SceneManager sceneManager, IOManager io) {
        super(sceneManager, io);

        shape = new ShapeRenderer();
        playerTexture = new Texture(Gdx.files.internal("Ship.png"));
        bulletTexture = new Texture(Gdx.files.internal("Bullet.png"));
        obstacleTexture = new Texture(Gdx.files.internal("bucket.png"));
        io.getSound().soundOn();

        initializeGame();
    }

    @Override
    public void update() {
        if (io.getKeyboard().isKeyPressed(Keys.ENTER)) {
            // if player die or game ends, push end scene
            sceneManager.push(new EndScene(sceneManager, io));
        }
        if (io.getKeyboard().isKeyPressed(Keys.ESCAPE)) {
            System.out.println("Pause game");
            // push pause scene
            sceneManager.push(new PauseScene(sceneManager, io));
        }
        if (io.getKeyboard().isKeyJustPressed(Keys.SPACE)) {
            shoot();
            io.getSound().playShootingSound();
        }

        // Update entities based on game tick time
        float delta = Gdx.graphics.getDeltaTime();
        entityManager.updateEntities(delta);

        // Wait a bit until the question renders
        consoleTimer += delta;
        // Render every 3 seconds
        if (consoleTimer > 3) {
            // Check if there are no more questions, then show main menu (won!)
            if (questions.isEmpty()) {
                sceneManager.push(new EndScene(sceneManager, io));
                consoleTimer = 0;
                return;
            }

            // Only render questions if question is not active
            if (currentQuestionNumber == -1) {
                // Get 1 question first
                currentQuestionNumber = (int) (Math.random() * questions.size()); // 0 - question size
                MathOperations ops = questions.get(currentQuestionNumber);
                // Render question
                entityManager.addEntity(
                        new Text(100, 400, 200, 50,
                                "Q: " + ops.getA() + " " + ops.getOps() + " " + ops.getB()
                                        + " = " + " ?",
                                25,
                                Color.WHITE, font),
                        false);
                entityManager.addEntity(
                        new Text(100, 425, 200, 50, questions.size() + " questions left...", 25, Color.WHITE, font),
                        false);

                // Get the 4 random and correct answers and render them at index
                int maxAnswerLength = 4;
                int correctAnswerIndex = (int) (Math.random() * 4); // 0 - 3
                int wrongAnswerIndex = 0;
                int imageWidth = 50;
                for (int i = 0; i < maxAnswerLength; i++) {
                    // Equally distribute space and add offset
                    int x = (int) Gdx.graphics.getWidth() / maxAnswerLength * i + imageWidth;

                    if (i == correctAnswerIndex) {
                        // Render correct answer
                        String correctAns = ops.getAns().toString();

                        // Render actual collidable
                        Entity correctAnswerEntity = EntityFactory.createObstacle(x, 300, imageWidth, imageWidth,
                                obstacleTexture);
                        correctAnswerEntity.add(new CollisionHandler(correctAnswerEntity, (self, other) -> {
                            if (other.has(AIMovement.class)) {
                                // Check if hit by bullet
                                if (currentQuestionNumber != -1) {
                                    questions.remove(currentQuestionNumber);
                                    currentQuestionNumber = -1;

                                    // Clear buckets and text only, don't reset player
                                    entityManager.clearAll();
                                    // Re-add player (don't create new)
                                    entityManager.addEntity(player, true);
                                    // Re-add UI
                                    entityManager.addEntity(
                                            new Text(400, 0, 200, 50, "Escape to pause!", 20, Color.WHITE, font),
                                            false);
                                    entityManager.addEntity(
                                            new Text(400, 25, 200, 50, "Enter to end game!", 20, Color.WHITE, font),
                                            false);
                                }
                            }
                        }));
                        entityManager.addEntity(correctAnswerEntity, true);
                        // Render text
                        entityManager.addEntity(new Text(x, 300, imageWidth, imageWidth, correctAns, 25, font), false);
                    } else {
                        // Fix for wrong answer index
                        if (i > wrongAnswerIndex)
                            wrongAnswerIndex = i - 1;
                        else
                            wrongAnswerIndex = i;

                        // Render wrong answer
                        String wrongAns = ops.getWrongAns().get(wrongAnswerIndex).toString();

                        // Render actual collidable
                        Entity wrongAnswerEntity = EntityFactory.createObstacle(x, 300, imageWidth, imageWidth,
                                obstacleTexture);
                        wrongAnswerEntity.add(new CollisionHandler(wrongAnswerEntity, (self, other) -> {
                            if (other.has(AIMovement.class)) {
                                // Game Over
                                sceneManager.push(new EndScene(sceneManager, io));
                            }
                        }));
                        entityManager.addEntity(wrongAnswerEntity, true);
                        // Render text
                        entityManager.addEntity(new Text(x, 300, imageWidth, imageWidth, wrongAns, 25, font), false);
                    }
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

        // UI
        entityManager.addEntity(new Text(400, 0, 200, 50, "Escape to pause!", 20, Color.WHITE, font), false);
        entityManager.addEntity(new Text(400, 25, 200, 50, "Enter to end game!", 20, Color.WHITE, font), false);

        // Add questions now
        if (questions.isEmpty()) {
            float max = 3; // Number of questions
            String[] ops = { "+", "-", "x", "/" };
            for (int i = 0; i < max; i++) {
                // Dynamically generate numbers and their answers
                int firstNum = (int) (Math.random() * 21); // 0 - 20
                int secondNum = (int) (Math.random() * 20) + 1; // 1 - 20
                String operation = ops[(int) (Math.random() * 4)]; // 0 - 3
                // For division only, generate perfectly divisible integers
                if (operation.equals("/")) {
                    secondNum = (int) (Math.random() * 20) + 1; // 1 to 20
                    int answer = (int) (Math.random() * 20) + 1; // 1 to 20

                    firstNum = secondNum * answer;
                }

                questions.add(new MathOperations(firstNum, secondNum, operation));
            }
        }

        // Create player
        player = EntityFactory.createPlayer(100, 0, 100, 100, playerTexture, bulletTexture, entityManager, 100, io);
        // Register player entity
        entityManager.addEntity(player, true);
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
