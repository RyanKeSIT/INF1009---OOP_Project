package io.github.INF1009OOP_Project.Engine.Entities;

public interface EntityFactoryNew<T extends Entity> {
    T createEntity();
}
