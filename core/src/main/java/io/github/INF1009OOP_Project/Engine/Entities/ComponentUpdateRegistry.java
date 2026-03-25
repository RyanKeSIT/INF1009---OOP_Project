package io.github.INF1009OOP_Project.Engine.Entities;

import java.util.ArrayList;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Component;

public class ComponentUpdateRegistry {
    private final ArrayList<Class<? extends Component>> updatableComponents = new ArrayList<>();

    // register a component type that needs to update each frame
    public void register(Class<? extends Component> componentType) {
        updatableComponents.add(componentType);
    }

    // update all registered component types via component manager
    public void update(ComponentManager componentManager, float delta) {// does not own component manager
        for (Class<? extends Component> type : updatableComponents) {
            componentManager.updateComponent(type, delta);
        }
    }
}
