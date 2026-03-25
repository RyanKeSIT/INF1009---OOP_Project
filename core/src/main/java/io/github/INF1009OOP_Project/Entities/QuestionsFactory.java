package io.github.INF1009OOP_Project.Entities;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;

import io.github.INF1009OOP_Project.Engine.Entities.Entity;
import io.github.INF1009OOP_Project.Engine.Entities.UI.Text;
import io.github.INF1009OOP_Project.Logic.MathOperations;
import io.github.INF1009OOP_Project.Entities.Components.RandomMovement;
import io.github.INF1009OOP_Project.Entities.Components.TransformSync;

public class QuestionsFactory {
    ArrayList<MathOperations> questions = new ArrayList<>();

    public QuestionsFactory(int numberOfQuestions, ArrayList<String> ops) {
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

    public Integer getQuestionSize() {
        return questions.size();
    }

    public Boolean isEmpty() {
        return questions.isEmpty();
    }

    public MathOperations getQuestionByNumber(int qNo) {
        return questions.get(qNo);
    }

    public void removeQuestionByNumber(int qNo) {
        this.questions.remove(qNo);
    }

    /**
     * Generates an arraylist of question entities (text and obstacles) for a given
     * question
     * NOTE: These entities are not in the EntityManager, need to add them
     * afterwards yeah....
     */
    public ArrayList<Entity> generateQuestionEntities(MathOperations ops, int remainingQuestions,int enemyHealth,
            BitmapFont font, Consumer<Entity> onCorrect, Consumer<Entity> onWrong) {
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
            float screenW = Gdx.graphics.getWidth();
            float screenH = Gdx.graphics.getHeight();
            float bottomBound = 200f;
            if (i == correctAnswerIndex) {
                // Correct answer
                String correctAns = ops.getAns().toString();
                Entity correctAnswerEntity = new ObstacleFactory(x, 300, imageWidth, imageWidth, obstacleTexture, enemyHealth, 100f, 0f, screenW, bottomBound, 450f,
                        (self, other) -> {
                            if (!other.has(RandomMovement.class))
                                onCorrect.accept(self);
                        }).createEntity();

                
                Text correctAnswerText = new Text(x, 300, imageWidth, imageWidth, correctAns, 30, Color.WHITE, font);
                correctAnswerEntity.add(new TransformSync(correctAnswerEntity, correctAnswerText));//sync text position to entity
                entities.add(correctAnswerEntity);
                entities.add(correctAnswerText);
                //entities.add(new Text(x, 300, imageWidth, imageWidth, correctAns, 30, Color.WHITE, font));
            } else {
                // Wrong answer
                String wrongAnsStr = ops.getWrongAns().get(wrongAnswerIndex).toString();
                wrongAnswerIndex++;

                //Entity wrongAnswerEntity   = new ObstacleFactory(x, 300, imageWidth, imageWidth, obstacleTexture, enemyHealth).createEntity();
                Entity wrongAnswerEntity = new ObstacleFactory(x, 300, imageWidth, imageWidth, obstacleTexture, enemyHealth, 100f, 0f, screenW, bottomBound, 450f,
                        (self, other) -> {
                            if (!other.has(RandomMovement.class))
                                onWrong.accept(self);
                        }).createEntity();

               
                Text wrongAnswerText = new Text(x, 300, imageWidth, imageWidth, wrongAnsStr, 30, Color.WHITE, font);
                wrongAnswerEntity.add(new TransformSync(wrongAnswerEntity,wrongAnswerText));//sync text position to entity
                entities.add(wrongAnswerEntity);
                entities.add(wrongAnswerText);
            }
        }

        return entities;
    }
}
