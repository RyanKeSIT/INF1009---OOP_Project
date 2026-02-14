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
import io.github.INF1009OOP_Project.IOManager;
import io.github.INF1009OOP_Project.Collision.*;
import io.github.INF1009OOP_Project.Entities.*;

public class EndScene extends Scene {
	private SpriteBatch batch;
    private BitmapFont font;
    private EntityManager entityManager = new EntityManager();

    private IOManager io = new IOManager();
    
	public EndScene(SceneManager sceneManager) {
		super(sceneManager);
        font = new BitmapFont();
        batch = new SpriteBatch();
        
        
         entityManager.addEntity(new Button(100,300, 150, 50,"Main Menu", 20, font, new ClickEvent() {
        	 @Override
        	 public void onClick(){
        		 System.out.println("Main menu");
        		 sceneManager.setScene(0);
        	 }
         }), false);
     
        
	}

	@Override
	public void update() {

		io.update();
		
		
		
		
		if (io.getMouse().mousePressed(Buttons.LEFT)) {
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
