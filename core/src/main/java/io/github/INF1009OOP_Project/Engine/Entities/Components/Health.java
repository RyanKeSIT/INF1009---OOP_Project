package io.github.INF1009OOP_Project.Engine.Entities.Components;

import io.github.INF1009OOP_Project.Engine.Entities.Entity;

public class Health implements Component {
    private int currentHealth;
    private int maxHealth;
    private Entity entity;

    public Health(Entity entity, int maxHealth) {
        this.entity = entity;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = Math.max(0, Math.min(currentHealth, maxHealth));
    }

    public void takeDamage(int amount) {
        this.currentHealth = Math.max(0, this.currentHealth - amount);
    }

    public boolean isDead() {
        return currentHealth <= 0;
    }
}
