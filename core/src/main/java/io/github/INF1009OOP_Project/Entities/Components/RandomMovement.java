package io.github.INF1009OOP_Project.Entities.Components;

import java.util.Random;
import io.github.INF1009OOP_Project.Engine.Entities.Entity;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Component;
import io.github.INF1009OOP_Project.Engine.Entities.Components.PhysicsBody;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Transform;

public class RandomMovement implements Component {
    private final Entity entity;
    private final float minX, maxX, minY, maxY;//area bounds for obstacles
    private final float speed;//base speed of obstacle

    public RandomMovement(Entity entity, float speed,
                          float minX, float maxX, float minY, float maxY) {
        this.entity = entity;
        this.speed = speed;
        this.minX = minX; this.maxX = maxX;
        this.minY = minY; this.maxY = maxY;
        //pick a random angle in deg and convert to direction vector, gives obstacle unique starting direction every spawn
        Random rand = new Random();
        float angle = rand.nextFloat() * 360;
        float vx = (float) Math.cos(Math.toRadians(angle)) * speed;
        float vy = (float) Math.sin(Math.toRadians(angle)) * speed;
        //apply initial velocity to pb, pb will move entity by this velocity each frame
        PhysicsBody pb = entity.get(PhysicsBody.class);
        if (pb != null) pb.setVelocity(vx, vy);
    }

    @Override
    public void update(float delta) {
        PhysicsBody pb = entity.get(PhysicsBody.class);
        Transform t = entity.get(Transform.class);
        if (pb == null || t == null) return;

        // bounce off left bound 
        if (t.getX() <= minX) {
            pb.setVelocityX(Math.abs(pb.getVelocityX()));
            t.setX(minX);
        }
        // bounce off right bound 
        if (t.getX() + t.getWidth() >= maxX) {
            pb.setVelocityX(-Math.abs(pb.getVelocityX()));
            t.setX(maxX - t.getWidth());
        }
        // bounce off top bound 
        if (t.getY() + t.getHeight() >= maxY) {
            pb.setVelocityY(-Math.abs(pb.getVelocityY()));
            t.setY(maxY - t.getHeight());
        }
        // graceful redirect at bottom bound
        if (t.getY() <= minY) {
            float currentVy = pb.getVelocityY();
            if (currentVy < 0) {
                pb.setVelocityY(Math.abs(currentVy));
                // add a small random horizontal nudge for organic feel
                // each obstacle redirects slightly differently at the bottom
                float nudge = (new Random().nextFloat() - 0.5f) * 50f;
                pb.setVelocityX(pb.getVelocityX() + nudge);
                // cap velocity to prevent nudge from making obstacle too fast
                float maxSpeed = speed * 1.3f;
                pb.setVelocityX(Math.max(-maxSpeed, Math.min(maxSpeed, pb.getVelocityX())));
                pb.setVelocityY(Math.max(-maxSpeed, Math.min(maxSpeed, pb.getVelocityY())));
            }
            t.setY(minY);
        }
    }
}