package io.github.INF1009OOP_Project;

import java.util.ArrayList;

public class MathOperations {
    int a;
    int b;
    String ops;
    int ans;
    ArrayList<Integer> wrongAns = new ArrayList<>();

    public MathOperations(int a, int b, String ops) {
        this.a = a;
        this.b = b;
        this.ops = ops;

        // Set the answer based on ops
        switch (this.ops) {
            // Addition
            case "+":
                this.ans = a + b;
                break;
            // Subtraction
            case "-":
                this.ans = a - b;
                break;
            // Multiplication
            case "x":
                this.ans = a * b;
                break;
            // Division
            case "/":
                this.ans = a / b;
                break;
        }

        // Generate 3 other unique wrong answers
        while (this.wrongAns.size() < 3) {
            int alternateAns = generateRandomInteger();

            // Number must not be the correct answer and must not already be in the wrongAns
            // list
            if (alternateAns != this.ans && !this.wrongAns.contains(alternateAns))
                this.wrongAns.add(alternateAns);
        }
    }

    Integer generateRandomInteger() {
        // 0 - (a * b + b), where
        return (int) (Math.random() * Math.max(this.a * this.b + this.b, this.a + 10));
    }

    public Integer getA() {
        return a;
    }

    public String getOps() {
        return ops;
    }

    public Integer getB() {
        return b;
    }

    public Integer getAns() {
        return ans;
    }

    public ArrayList<Integer> getWrongAns() {
        return wrongAns;
    }
}
