package io.github.INF1009OOP_Project.Entities.Components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.INF1009OOP_Project.Entities.Entity;

//This component is used to help render the object using a texture
public class Renderable implements Component{
    private Texture texture;
    private Entity entity;
    
    public Renderable(Entity entity, Texture texture) {
        this.entity = entity;
        this.texture = texture;
    }
    
    public Texture getTexture() {
        return texture;
    }
    
    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    
    public void draw(SpriteBatch spriteBatch) {
        if (texture == null) return;
        
        Transform transform = entity.get(Transform.class);
        if (transform == null) return;
        
        spriteBatch.draw(
            texture,
            transform.getX(),
            transform.getY(),
            transform.getWidth(),
            transform.getHeight()
        );
    }
}
