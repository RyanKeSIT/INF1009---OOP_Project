package io.github.INF1009OOP_Project.Engine.Scene;

import java.util.Stack;

public class SceneManager {
 
    private Stack<Scene> stack = new Stack<>();

    // add a new scene on top of the stack
    public void push(Scene scene) {
        stack.push(scene);
    }

    // remove the top scene
    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    // return the top scene without removing it
    public Scene peek() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        return null;
    }

    
    public void update() {
        if (!stack.isEmpty()) {
            stack.peek().update();
        }
    }

    public void render() {
        if (!stack.isEmpty()) {
        	 int startIndex = stack.size() - 1;

             while (startIndex > 0 && stack.get(startIndex).isOverlay()) {
                 startIndex--;
             }

             for (int i = startIndex; i < stack.size(); i++) {
                 stack.get(i).render();
             }
        }  
    }
}