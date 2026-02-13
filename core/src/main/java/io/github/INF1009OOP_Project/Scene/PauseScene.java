package io.github.INF1009OOP_Project.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.ClickEvent;
import io.github.INF1009OOP_Project.GameMaster;
import io.github.INF1009OOP_Project.IOManager;
import io.github.INF1009OOP_Project.UI.Button;

public class PauseScene extends Scene{
	
	private Button resumeButton;
	private SpriteBatch batch;
	

	public PauseScene(SceneManager sceneManager, IOManager io) {
		super(sceneManager, io);

        batch = new SpriteBatch();
        
		resumeButton = new Button(100,300, 100, 50,"Resume", 20, GameMaster.font, new ClickEvent(){
	        	@Override
	        	public void onClick(){
	        		System.out.println("Resume button clicked");
	        		sceneManager.setScene(1);
	        	}
	     });
	}

	@Override
	public void update() {
		io.update();
		
		if (io.getMouse().mousePressed(Buttons.LEFT)) {
			if(resumeButton.isHover(io.getMouse().getX(), io.getMouse().getY())) {
				resumeButton.onClick();
			}
			
		}
	}

	@Override
	public void render() {
		ScreenUtils.clear(0,0,0,0);
		batch.begin();
		resumeButton.draw(batch);
		batch.end();
	}

	@Override
	public void dispose() {
			
	}
}
