package io.github.INF1009OOP_Project.Engine.Entities.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import io.github.INF1009OOP_Project.Engine.Entities.Entity;
import io.github.INF1009OOP_Project.Engine.IO.IOManager;

public class PlayerMovement extends Movement {

    private IOManager io;

    public PlayerMovement(Entity entity, float speed) {
        super(entity, speed);
    }

    public void setIOManager(IOManager io) {
        this.io = io;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return this.speed;
    }

    @Override
    public void move(float delta) {
        Transform transform = entity.get(Transform.class);// entity from movement
        PhysicsBody pb = entity.get(PhysicsBody.class);
        if (transform == null || pb == null)
            return;

        float vx = 0f;

        // Move left
        if (io.getKeyboard().isKeyPressed(Keys.LEFT) && transform.getX() > 0) {
            vx = -speed;
        }

        // Move right
        if (io.getKeyboard().isKeyPressed(Keys.RIGHT)
                && transform.getX() < (Gdx.graphics.getWidth() - transform.getWidth())) {
            vx = speed;
        }

        // Apply velocity , physics body will move the transform
        pb.setVelocity(vx, 0f);

    }
}
