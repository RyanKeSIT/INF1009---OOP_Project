package io.github.INF1009OOP_Project.Entities.UI;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;

import io.github.INF1009OOP_Project.EntityFactory;
import io.github.INF1009OOP_Project.MathOperations;
import io.github.INF1009OOP_Project.Engine.Entities.Entity;
import io.github.INF1009OOP_Project.Engine.Entities.UI.Text;
import io.github.INF1009OOP_Project.Engine.Entities.Components.CollisionHandler;
import io.github.INF1009OOP_Project.Engine.Entities.Components.AIMovement;

public class QuestionsFactory {
    private ArrayList<MathOperations> questions = new ArrayList<>();

    public QuestionsFactory(int numberOfQuestions, ModeCheckbox[] MCOps) {
        // Whitelist checked math operations
        ArrayList<String> ops = new ArrayList<>();
        for (int i = 0; i < MCOps.length; i++)
            if (MCOps[i].checked())
                ops.add(MCOps[i].getText());

        for (int j = 0; j < numberOfQuestions; j++) {
            // Dynamically generate numbers and their answers
            int firstNum = MathUtils.random(0, 10); // 0 - 10
            int secondNum = MathUtils.random(1, 10); // 1 - 10
            // Dynamically generate operator

            String operation = ops.get(MathUtils.random(0, ops.size() - 1)); // 0 - ops.length - 1
            // For division only, generate perfectly divisible integers
            if (operation.equals("/")) {
                secondNum = MathUtils.random(1, 10); // 1 to 10
                int answer = MathUtils.random(1, 10); // 1 to 10

                firstNum = secondNum * answer;
            }

            questions.add(new MathOperations(firstNum, secondNum, operation));
        }
    }

    public ArrayList<MathOperations> getQuestions() {
        return questions;
    }

    /**
     * Generates an arraylist of question entities (text and obstacles) for a given
     * question
     * NOTE: These entities are not in the EntityManager, need to add them!!!
     */
    public ArrayList<Entity> generateQuestionEntities(MathOperations ops, int remainingQuestions,
            BitmapFont font, Runnable onCorrect, Runnable onWrong) {
        Texture obstacleTexture = new Texture(Gdx.files.internal("enemyShip.png"));
        ArrayList<Entity> entities = new ArrayList<>();

        // Question text
        entities.add(new Text(200, 400, 200, 50,
                "Q: " + ops.getA() + " " + ops.getOps() + " " + ops.getB() + " = " + " ?", 25, Color.WHITE,
                font));
        entities.add(new Text(200, 425, 200, 50, remainingQuestions + " questions left...", 25, Color.WHITE,
                font));

        // Get the 4 random and correct answers and render them
        int maxAnswerLength = 4;
        int correctAnswerIndex = MathUtils.random(0, 3); // 0 - 3
        int wrongAnswerIndex = 0;
        int imageWidth = 50;
        for (int i = 0; i < maxAnswerLength; i++) {
            // Distribute space
            int x = (int) Gdx.graphics.getWidth() / maxAnswerLength * i + imageWidth;

            if (i == correctAnswerIndex) {
                // Correct answer
                String correctAns = ops.getAns().toString();
                Entity correctAnswerEntity = EntityFactory.createObstacle(x, 300, imageWidth, imageWidth,
                        obstacleTexture);

                correctAnswerEntity.add(new CollisionHandler(correctAnswerEntity, (self, other) -> {
                    if (other.has(AIMovement.class)) {
                        onCorrect.run();
                    }
                }));
                entities.add(correctAnswerEntity);
                entities.add(new Text(x, 300, imageWidth, imageWidth, correctAns, 30, Color.WHITE, font));
            } else {
                // Wrong answer
                String wrongAnsStr = ops.getWrongAns().get(wrongAnswerIndex).toString();
                wrongAnswerIndex++;

                Entity wrongAnswerEntity = EntityFactory.createObstacle(x, 300, imageWidth, imageWidth,
                        obstacleTexture);

                wrongAnswerEntity.add(new CollisionHandler(wrongAnswerEntity, (self, other) -> {
                    if (other.has(AIMovement.class)) {
                        onWrong.run();
                    }
                }));
                entities.add(wrongAnswerEntity);
                entities.add(new Text(x, 300, imageWidth, imageWidth, wrongAnsStr, 30, Color.WHITE, font));
            }
        }

        return entities;
    }
}
