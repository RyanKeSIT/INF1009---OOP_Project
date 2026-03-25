package io.github.INF1009OOP_Project.Entities;

import com.badlogic.gdx.graphics.Texture;
import io.github.INF1009OOP_Project.Engine.Entities.*;
import io.github.INF1009OOP_Project.Engine.Entities.Components.*;
import io.github.INF1009OOP_Project.Engine.IO.IOManager;
import io.github.INF1009OOP_Project.Entities.Components.Health;

public class PlayerFactory implements EntityFactory<Entity> {
    private final float x, y, width, height, speed;
    private final Texture texture;
    private final EntityManager entityManager;
    private final IOManager io;

    public PlayerFactory(float x, float y, float width, float height,
                         Texture texture, EntityManager entityManager,
                         float speed, IOManager io) {
        this.x = x; this.y = y;
        this.width = width; this.height = height;
        this.texture = texture;
        this.entityManager = entityManager;
        this.speed = speed;
        this.io = io;
    }

    @Override
    public Entity createEntity() {
        Entity player = new Entity();
        player.add(new Transform(x, y, width, height));
        player.add(new PhysicsBody(player));
        player.add(new Renderable(player, texture));
        PlayerMovement movement = new PlayerMovement(player, speed);
        movement.setIOManager(io);
        player.add(movement);
        player.add(new Health(player, 3));
        player.add(new CollisionHandler(player, (self, other) -> {
            System.out.println("Player collided with something");
        }));
        return player;
    }
}