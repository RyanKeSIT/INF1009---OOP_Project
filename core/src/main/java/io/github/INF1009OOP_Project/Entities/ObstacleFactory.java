package io.github.INF1009OOP_Project.Entities;

import com.badlogic.gdx.graphics.Texture;
import io.github.INF1009OOP_Project.Engine.Entities.*;
import io.github.INF1009OOP_Project.Engine.Entities.Components.*;

public class ObstacleFactory implements EntityFactoryNew<Entity> {
    private final float x, y, width, height, speed;
    private final Texture texture;
    private final int health;
    private final boolean movable;

    //static obstacle
    public ObstacleFactory(float x, float y, float width, float height,
                           Texture texture, int health) {
        this.x = x; this.y = y;
        this.width = width; this.height = height;
        this.texture = texture;
        this.health = health;
        this.speed = 0;
        this.movable = false;
    }

    //moving obstacle (overloading)
    public ObstacleFactory(float x, float y, float width, float height,
                           Texture texture, int health, float speed) {
        this.x = x; this.y = y;
        this.width = width; this.height = height;
        this.texture = texture;
        this.health = health;
        this.speed = speed;
        this.movable = true;
    }

    @Override
    public Entity createEntity() {
        Entity entity = new Entity();
        entity.add(new Transform(x, y, width, height));
        entity.add(new PhysicsBody(entity));
        entity.add(new Renderable(entity, texture));
        entity.add(new Health(entity, health));

        if (movable)
            entity.add(new AIMovement(entity, speed));

        entity.add(new CollisionHandler(entity, (self, other) -> {
            System.out.println("Something collided with obstacle");
        }));
        return entity;
    }
}