package io.github.INF1009OOP_Project.Entities;

import com.badlogic.gdx.graphics.Texture;
import io.github.INF1009OOP_Project.Engine.Entities.*;
import io.github.INF1009OOP_Project.Engine.Entities.Components.*;

public class BulletFactory implements EntityFactory<Entity> {
    private final float x, y, width, height, speed;
    private final Texture texture;
    private final EntityManager entityManager;

    public BulletFactory(float x, float y, float width, float height,
            float speed, Texture texture, EntityManager entityManager) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.texture = texture;
        this.entityManager = entityManager;
    }

    @Override
    public Entity createEntity() {
        Entity bullet = new Entity();
        bullet.add(new Transform(x, y, width, height));
        bullet.add(new PhysicsBody(bullet));
        bullet.add(new Renderable(bullet, texture));
        bullet.add(new AIMovement(bullet, speed));
        bullet.add(new CollisionHandler(bullet, (self, other) -> {
            entityManager.deleteEntity(self);
        }));
        return bullet;
    }
}