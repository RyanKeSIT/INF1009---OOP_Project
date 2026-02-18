package io.github.INF1009OOP_Project.Scene;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.UI.Button;
import io.github.INF1009OOP_Project.UI.ClickEvent;
import io.github.INF1009OOP_Project.IOManager;
import io.github.INF1009OOP_Project.Collision.*;
import io.github.INF1009OOP_Project.Entities.*;
import io.github.INF1009OOP_Project.Entities.Components.Clickable;

public class PauseScene extends Scene {
	private SpriteBatch batch;
    private BitmapFont font;
    private EntityManager entityManager = new EntityManager();
    
	public PauseScene(SceneManager sceneManager, IOManager io) {
		super(sceneManager, io);
        font = new BitmapFont();
        batch = new SpriteBatch();
        
        
         entityManager.addEntity(new Button(100,300, 100, 50,"Resume", 20, font, new ClickEvent() {
        	 @Override
        	 public void onClick(){
        		 System.out.println("Resume game");
        		 sceneManager.setScene(1);
        	 }
         }), false);
     
        
	}

	@Override
	public void update() {
		entityManager.updateEntities(0);
		// switch to game scene
		if (io.getKeyboard().isKeyPressed(Keys.ENTER)) {
			sceneManager.setScene(2); 
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
