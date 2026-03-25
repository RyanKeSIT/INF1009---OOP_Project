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
        // get position of source entity (obstacle in this use case)
        Transform source = entity.get(Transform.class);
        // get position of target entity (text label in this use case)
        Transform targetT = target.get(Transform.class);
        if (source == null || targetT == null)
            return;
        // copy obstacles current position to text label every frame, makes text follow
        // obstacle
        targetT.setX(source.getX());
        targetT.setY(source.getY());
    }
}