package io.github.INF1009OOP_Project.Scene;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.UI.Button;
import io.github.INF1009OOP_Project.ClickEvent;
import io.github.INF1009OOP_Project.GameMaster;
import io.github.INF1009OOP_Project.IOManager;
import io.github.INF1009OOP_Project.Collision.*;
import io.github.INF1009OOP_Project.Entities.*;

public class StartScene extends Scene {
	private SpriteBatch batch;
    private EntityManager entityManager = new EntityManager();
    private ArrayList<Button> buttonList = new ArrayList<Button>();
    
	public StartScene(SceneManager sceneManager, IOManager io) {
		super(sceneManager, io);
        batch = new SpriteBatch();
        
        Button startButton = new Button(100,300, 100, 50,"Start", 20, GameMaster.font, new ClickEvent(){
        	@Override
        	public void onClick(){
        		System.out.println("Start button clicked");
        		sceneManager.setScene(1);
        	}
        });
        buttonList.add(startButton);
        
        
	}

	@Override
	public void update() {
		io.update();
		// switch to game scene
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			sceneManager.setScene(1); 
		}
		
		if (io.getMouse().mousePressed(Buttons.LEFT)) {
			for(Button b : buttonList) {
				if(b.isHover(io.getMouse().getX(), io.getMouse().getY())) {
					b.onClick();
				}
			}
		}
		
	}

	@Override
	public void render() {
		ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
		batch.begin();
        for(Button b : buttonList) {
        	b.draw(batch);
        }
        batch.end();
	}

	@Override
	public void dispose() {
		
	}

}
