package io.github.INF1009OOP_Project.Entities.Components;

import io.github.INF1009OOP_Project.Engine.Entities.Components.Component;
//gives obstacles a 0.2 second window where collision is ignoroed so they can have time to move apart before next collision trigger
public class BounceCooldown implements Component {
    private float cooldown = 0f;
    private static final float COOLDOWN_TIME = 0.2f;

    @Override
    public void update(float delta) {
        if (cooldown > 0)
            cooldown -= delta;
    }

    public boolean canBounce() {
        return cooldown <= 0;
    }

    public void trigger() {
        cooldown = COOLDOWN_TIME;
    }
}