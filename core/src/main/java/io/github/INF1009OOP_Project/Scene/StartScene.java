package io.github.INF1009OOP_Project.Scene;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.UI.*;
import io.github.INF1009OOP_Project.UI.ClickEvent;
import io.github.INF1009OOP_Project.IOManager;
import io.github.INF1009OOP_Project.Collision.*;
import io.github.INF1009OOP_Project.Entities.*;
import io.github.INF1009OOP_Project.Entities.Components.Clickable;

public class StartScene extends Scene {
	private Texture image;
	private SpriteBatch batch;
    private BitmapFont font;
    private EntityManager entityManager = new EntityManager();

    private IOManager io = new IOManager();
    
	public StartScene(SceneManager sceneManager) {
		super(sceneManager);
		image = new Texture("libgdx.png");
        font = new BitmapFont();
        batch = new SpriteBatch();
        
        
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
		
		// switch to game scene
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			sceneManager.setScene(1); 
		}
		
		
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
		
		
		
		
		ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
		
		entityManager.draw(batch);
	}

	@Override
	public void dispose() {
		
	}

}
