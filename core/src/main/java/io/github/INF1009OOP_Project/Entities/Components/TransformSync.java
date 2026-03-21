package io.github.INF1009OOP_Project.Entities.Components;

import io.github.INF1009OOP_Project.Engine.Entities.Entity;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Component;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Transform;


public class TransformSync implements Component {
    private final Entity entity;
    private final Entity target;

    public TransformSync(Entity entity, Entity target) {
        this.entity = entity;
        this.target = target;
    }

    @Override
    public void update(float delta) {
        Transform source = entity.get(Transform.class);
        Transform targetT = target.get(Transform.class);
        if (source == null || targetT == null) return;

        targetT.setX(source.getX());
        targetT.setY(source.getY());
    }
}