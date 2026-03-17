package io.github.INF1009OOP_Project;

import java.util.*;

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
        int minValue = Math.min(0, this.ans - 10);
        int maxValue = Math.max(this.a * this.b + this.b, this.a + 10);
        List<Integer> pool = new ArrayList<>();
        for (int i = minValue; i <= maxValue; i++)
            if (i != this.ans)
                pool.add(i);

        Collections.shuffle(pool, new Random());
        for (int i = 0; i < Math.min(3, pool.size()); i++)
            this.wrongAns.add(pool.get(i));
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
