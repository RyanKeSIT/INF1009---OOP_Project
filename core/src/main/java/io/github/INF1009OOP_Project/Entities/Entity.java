package io.github.INF1009OOP_Project.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.INF1009OOP_Project.Collision.*;
import java.util.Map;
import java.util.HashMap;

public class Entity {
    private Map<Class<?>, Component> components;
    
    public Entity() {
        components = new HashMap<>();
    }
    
    //add component to entity
    public <T extends Component> void add(T component) {
        components.put(component.getClass(), component);
    }
    
    //get component from entity
    public <T extends Component> T get(Class<T> componentClass) {
        return componentClass.cast(components.get(componentClass));
    }
    
    //check for component in entity
    public <T extends Component> boolean has(Class<T> componentClass) {
        return components.containsKey(componentClass);
    }
    
    //delete component from entity
    public <T extends Component> void remove(Class<T> componentClass) {
        components.remove(componentClass);
    }
    
    //for components that need update
    public void update(float delta) {
        for (Component component : components.values()) {
            component.update(delta);
        }
    }
    
    //checks if renderable and calls renderable to draw
    public void draw(SpriteBatch sb) {
        Renderable renderable = get(Renderable.class);
        if (renderable != null) {
            renderable.draw(sb);
        }
    }
}
