package io.github.INF1009OOP_Project;

public class MathOperations {
    float a;
    float b;
    String ops;
    float ans;

    public MathOperations(float a, float b, String ops) {
        this.a = a;
        this.b = b;
        this.ops = ops;

        // Set the answer based on ops
        switch (ops) {
            // Addition
            case "+":
                this.ans = a + b;
                break;
            // Subtraction
            case "-":
                this.ans = a - b;
                break;
            // Multiplication
            case "*":
            case "x":
                this.ans = a * b;
                break;
            // Division
            case "/":
                this.ans = a / b;
                break;
        }
    }

    public float getA() {
        return a;
    }

    public String getOps() {
        return ops;
    }

    public float getB() {
        return b;
    }

    public float getAns() {
        return ans;
    }
}
