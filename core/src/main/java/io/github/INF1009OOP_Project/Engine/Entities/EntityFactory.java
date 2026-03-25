package io.github.INF1009OOP_Project.Engine.Entities;

public interface EntityFactory<T extends Entity> {
    T createEntity();
}
