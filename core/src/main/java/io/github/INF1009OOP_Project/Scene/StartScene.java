package io.github.INF1009OOP_Project.Scene;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.UI.Button;
import io.github.INF1009OOP_Project.Collision.*;
import io.github.INF1009OOP_Project.Entities.*;

public class StartScene extends Scene {
	private Texture image;
	private SpriteBatch batch;
    private BitmapFont font;
    private Button startButton, optionsButton;
    private EntityManager entityManager = new EntityManager();
    private ArrayList<Button> buttonList = new ArrayList<Button>();
    
	public StartScene(SceneManager sceneManager) {
		super(sceneManager);
		image = new Texture("libgdx.png");
        font = new BitmapFont();
        batch = new SpriteBatch();
        
        optionsButton = new Button(100,100, 100, 50,"Options", 20, font);
        startButton = new Button(100,300, 100, 50,"Start", 20, font);
     
        
	}

	@Override
	public void update() {
		// switch to game scene
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			sceneManager.setScene(1); 
		}
	}

	@Override
	public void render() {
		ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
		batch.begin();
        batch.draw(image, 140, 210);
        for(Button b : buttonList) {
        	b.draw(batch);
        }
        batch.end();
	}

	@Override
	public void dispose() {
		
	}

}
