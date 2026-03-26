package io.github.INF1009OOP_Project.Entities;

import com.badlogic.gdx.graphics.Texture;
import io.github.INF1009OOP_Project.Engine.Entities.Entity;
import io.github.INF1009OOP_Project.Engine.Entities.EntityFactory;
import io.github.INF1009OOP_Project.Engine.Entities.Components.CollisionHandler;
import io.github.INF1009OOP_Project.Engine.Entities.Components.AIMovement;
import io.github.INF1009OOP_Project.Engine.Entities.Components.PhysicsBody;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Transform;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Renderable;
import io.github.INF1009OOP_Project.Entities.Components.RandomMovement;
import io.github.INF1009OOP_Project.Entities.Components.BounceCooldown;
import io.github.INF1009OOP_Project.Entities.Components.Health;

public class ObstacleFactory implements EntityFactory<Entity> {
    private final float x, y, width, height, speed;
    private final Texture texture;
    private final int health;
    private final boolean movable;
    private final CollisionHandler.CollisionCallback onCollide;
    private final float minX, maxX, minY, maxY;

    // static obstacle
    public ObstacleFactory(float x, float y, float width, float height,
            Texture texture, int health) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.health = health;
        this.speed = 0;
        this.movable = false;
        this.onCollide = null;
        this.minX = 0;
        this.maxX = 0;
        this.minY = 0;
        this.maxY = 0;
    }

    // moving obstacle (overloading)
    public ObstacleFactory(float x, float y, float width, float height,
            Texture texture, int health, float speed,
            float minX, float maxX, float minY, float maxY,
            CollisionHandler.CollisionCallback onCollide) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.health = health;
        this.speed = speed;
        this.movable = true;
        this.onCollide = onCollide;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    @Override
    public Entity createEntity() {
        Entity entity = new Entity();
        entity.add(new Transform(x, y, width, height));
        entity.add(new PhysicsBody(entity));
        entity.add(new Renderable(entity, texture));
        entity.add(new Health(entity, health));

        if (movable) {
            entity.add(new RandomMovement(entity, speed, minX, maxX, minY, maxY));
            entity.add(new BounceCooldown());
        }

        entity.add(new CollisionHandler(entity, (self, other) -> {
            // Bullet hit has AIMovement, delegate to external callback
            if (other.has(AIMovement.class)) {
                if (onCollide != null)
                    onCollide.onCollide(self, other);
                return;
            }

            // Obstacle to obstacle bounce using cooldown to prevent sticking
            PhysicsBody selfPb = self.get(PhysicsBody.class);
            PhysicsBody otherPb = other.get(PhysicsBody.class);
            if (selfPb != null && otherPb != null) {
                BounceCooldown selfCd = self.get(BounceCooldown.class);
                BounceCooldown otherCd = other.get(BounceCooldown.class);
                // collision only works when both obstacles cooldown expire, prevents rapid
                // repeated swaps
                if (selfCd != null && otherCd != null && selfCd.canBounce() && otherCd.canBounce()) {
                    // elastic collision, swaps velocities between the two obstacles
                    float tempX = selfPb.getVelocityX();// temp store obstacle velocity
                    float tempY = selfPb.getVelocityY();
                    selfPb.setVelocity(otherPb.getVelocityX(), otherPb.getVelocityY());// obstacle A gets Bs velocity
                    otherPb.setVelocity(tempX, tempY);// B gets A
                    // start cooldown on both obstacles
                    // prevents them from colliding again until they've separated
                    selfCd.trigger();
                    otherCd.trigger();

                }
            }
        }));
        return entity;
    }
}