
package io.github.INF1009OOP_Project.Scene;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.UI.*;
import io.github.INF1009OOP_Project.UI.ClickEvent;
import io.github.INF1009OOP_Project.Entities.*;
import io.github.INF1009OOP_Project.Entities.Components.Clickable;
import io.github.INF1009OOP_Project.Entities.IO.IOManager;

public class StartScene extends Scene {
    
	public StartScene(SceneManager sceneManager, IOManager io) {
		super(sceneManager, io);
        
        entityManager.addEntity(new Text(300, 300, 200, 50, "Space Invaders", 50,Color.WHITE, font), false);
        entityManager.addEntity(new Button(100,300, 100, 50,"Start", 20, font, new ClickEvent() {
        	 @Override
        	 public void onClick(){
        		 System.out.println("Start button clicked");
        		 sceneManager.setScene(1);
        	 }
        }), false);
     
        
	}

	@Override
	public void update() {
		
		io.update();
		entityManager.updateEntities(0);
		// switch to game scene
		if (io.getKeyboard().isKeyPressed(Keys.ENTER)) {
			sceneManager.setScene(1); 
		}
		
		
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

		ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
		
		entityManager.draw(batch);
	}

	@Override
	public void dispose() {
		
	}

}
