package io.github.INF1009OOP_Project.Scenes;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.Engine.Entities.*;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Clickable;
import io.github.INF1009OOP_Project.Engine.Entities.UI.Button;
import io.github.INF1009OOP_Project.Engine.Entities.UI.ClickEvent;
import io.github.INF1009OOP_Project.Engine.IO.IOManager;
import io.github.INF1009OOP_Project.Engine.Scene.Scene;
import io.github.INF1009OOP_Project.Engine.Scene.SceneManager;

public class EndScene extends Scene {
    
	public EndScene(SceneManager sceneManager, IOManager io) {
		super(sceneManager, io);
        
		entityManager.addEntity(new Button(300, 300, 150, 50, "Main Menu", 20, font, new ClickEvent() {
			@Override
			public void onClick() {
				System.out.println("Main menu");
				// remove end scene
				sceneManager.pop();
				// remove game scene
				sceneManager.pop();
				// push start scene
				sceneManager.push(new StartScene(sceneManager, io));
			}
		}), false);
	}

	@Override
	public void update() {
		entityManager.updateEntities(0);
		if (io.getMouse().isMousePressed(Buttons.LEFT)) {
			for (Entity entity : entityManager.getEntities()) {  
	    	    Clickable c = entity.get(Clickable.class);
	    	    if (c!=null) {
	    	    	if(c.isHover(io.getMouse().getX(), io.getMouse().getY())) {
						c.onClick();
					}
	    	    }
	    	}
		}
		
	}

	@Override
	public void render() {

		ScreenUtils.clear(0,0,1,0);		
		entityManager.draw(batch);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}