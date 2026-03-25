package io.github.INF1009OOP_Project.Logic;

public class Multiplication extends MathQuestion {
    public Multiplication(int a, int b) {
        super(a, b, "x");
    }

    @Override
    protected int calculateAns(int a, int b) {
        return a * b;
    }
}
