package io.github.INF1009OOP_Project.Logic;

public class Division extends MathQuestion {
    public Division(int a, int b) {
        super(a, b, "/");
    }

    @Override
    protected int calculateAns(int a, int b) {
        return a / b;
    }
}
