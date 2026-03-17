package io.github.INF1009OOP_Project.Engine.Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.INF1009OOP_Project.Engine.Entities.Components.Component;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Renderable;

public class ComponentManager {
	private Map<Class<?>, ArrayList<Component>> components;
	public ComponentManager() {
		components = new HashMap<>();
	}
	
	public void addComponents(Entity entity) {
		//System.out.println("Add components from entity: ");
		//System.out.println(entity);
		 for (Component component : entity.get().values()) {
			 System.out.println(component);
			 components.computeIfAbsent(component.getClass(), k -> new ArrayList<>()).add(component);
			 // also add under all superclasses up to Component
			 // For ui elements that have custom rendering
			 // which instantiate an anonymous Renderable class
		     Class<?> clazz = component.getClass().getSuperclass();
		     while (clazz != null && Component.class.isAssignableFrom(clazz)) {
		         components.computeIfAbsent((Class<? extends Component>) clazz, k -> new ArrayList<>()).add(component);
		         clazz = clazz.getSuperclass();
		     }
		 }
		 //System.out.println(components);
	}
	
	public void removeComponents(Entity entity) {
		 for (Component component : entity.get().values()) {
			 components.get(component.getClass()).remove(component);
			 Class<?> clazz = component.getClass().getSuperclass();
		     while (clazz != null && Component.class.isAssignableFrom(clazz)) {
		         components.get((Class<? extends Component>)clazz).remove(component);
		         clazz = clazz.getSuperclass();
		     }
	     }
	}
	
	public <T extends Component> void updateComponent(Class<T> cl, float delta) {
		ArrayList<Component> list = components.get(cl); // get the 
		
	    if (list == null) return; // nothing to update

	    for (Component c : list) {
	        if (c != null) {       
	            c.update(delta);
	        }
	    }
	}
	
	public <T extends Component> ArrayList<T> getComponentsOfType(Class<T> type) {
	    ArrayList<Component> list = components.get(type);
	    if (list == null) return new ArrayList<>();
	    ArrayList<T> typedList = new ArrayList<>();
	    for (Component c : list) {
	        typedList.add((T) c);
	    }
	    return typedList;
	}
	
	public void draw(SpriteBatch sb) {
		for (Renderable r : getComponentsOfType(Renderable.class)) {
		    r.draw(sb);
		}
	}
	
	public void clear() {
		components.clear();
	}
	
}
