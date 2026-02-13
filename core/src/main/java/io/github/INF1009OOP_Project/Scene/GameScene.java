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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.IOManager;
import io.github.INF1009OOP_Project.Collision.*;
import io.github.INF1009OOP_Project.Entities.*;


public class GameScene extends Scene {
	private SpriteBatch batch;
    private Entity player,bullet;
    private Texture image;
    private ShapeRenderer shape;
    private EntityManager entityManager = new EntityManager();
    private CollisionManager collisionManager = new CollisionManager();
    private IOManager io = new IOManager();
    
	public GameScene(SceneManager sceneManager, IOManager io) {
        super(sceneManager, io);
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        image = new Texture("libgdx.png");
        player = new Player(100,100,100,100, new Texture(Gdx.files.internal("bucket.png")),100);
        bullet = new Bullet(20,100,70,70,100);
        io.getSound().soundOn();
        
        entityManager.addEntity(player);
        entityManager.addEntity(bullet);
        collisionManager.registerEntity((Collidable) player);
        collisionManager.registerEntity((Collidable) bullet);
        
        
        Gdx.input.setInputProcessor(new InputAdapter() {
        	@Override
        	public boolean keyDown (int keycode) {
        		if(keycode == Keys.SPACE) {
        			((Player)player).shoot();
        		}
        		return false;
        	}
        });
    }
	
	// TODO : call when game ends
	public void resetState() {
		
	}

    @Override
    public void update() {
    	io.update();
    	// switch to end scene
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            sceneManager.setScene(2); 
        }
        
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
        	System.out.println("Pause game");
        	sceneManager.setScene(3);
        }
    }

    @Override
    public void render() {
    	ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
    	float delta = Gdx.graphics.getDeltaTime();
        batch.begin();
        batch.end();
    	io.update();
    	// Keyboard, Mouse, Sound test
    	
    	//batch.begin();
    	//batch.draw(image, 140, 210);
    	//batch.end();
    	entityManager.moveAll();
    	entityManager.updateEntities();
    	collisionManager.update();
    	entityManager.draw(batch, shape);
    	
    	//visualize bounds hitbox
    	shape.begin(ShapeRenderer.ShapeType.Line);

        Bounds a = player.getBounds();
        shape.rect(a.getX(), a.getY(), a.getWidth(), a.getHeight());

        Bounds b = bullet.getBounds();
        shape.rect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

        shape.end();
    }

    @Override
    public void dispose() {
       
    }
}
