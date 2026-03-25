package io.github.INF1009OOP_Project.Logic;

public class Subtraction extends MathQuestion {
    public Subtraction(int a, int b) {
        super(a, b, "-");
    }

    @Override
    protected int calculateAns(int a, int b) {
        return a - b;
    }
}
