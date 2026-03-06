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

        // Generate 3 other wrong answers
        for (int i = 0; i < 3; i++) {
            int alternateAns = (int) (Math.random() * 101); // 0 - 100
            // Only accept if it is NOT the answer
            if (this.ans != alternateAns)
                this.wrongAns.add(alternateAns);
        }
    }

    public int getA() {
        return a;
    }

    public String getOps() {
        return ops;
    }

    public int getB() {
        return b;
    }

    public int getAns() {
        return ans;
    }

    public ArrayList<Integer> getWrongAns() {
        return wrongAns;
    }
}
