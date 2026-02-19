package io.github.INF1009OOP_Project.Scene;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.UI.Button;
import io.github.INF1009OOP_Project.UI.ClickEvent;
import io.github.INF1009OOP_Project.Collision.*;
import io.github.INF1009OOP_Project.Entities.*;
import io.github.INF1009OOP_Project.Entities.Components.Clickable;
import io.github.INF1009OOP_Project.Entities.IO.IOManager;

public class EndScene extends Scene {
    
	public EndScene(SceneManager sceneManager, IOManager io) {
		super(sceneManager, io);
        
         entityManager.addEntity(new Button(300,300, 150, 50,"Main Menu", 20, font, new ClickEvent() {
        	 @Override
        	 public void onClick(){
        		 System.out.println("Main menu");
        		 sceneManager.setScene(0);
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
		
	}

}