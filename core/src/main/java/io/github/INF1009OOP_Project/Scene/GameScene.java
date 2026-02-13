package io.github.INF1009OOP_Project.Scene;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.INF1009OOP_Project.Collision.*;
import io.github.INF1009OOP_Project.Entities.*;


public class GameScene extends Scene {
	private SpriteBatch batch;
    private Entity player,bullet;
    private Texture image;
    private ShapeRenderer shape;
    private EntityManager entityManager = new EntityManager();
    private CollisionManager collisionManager = new CollisionManager();
    
	public GameScene(SceneManager sceneManager) {
        super(sceneManager);
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        image = new Texture("libgdx.png");
        player = new Player(100,100,100,100, new Texture(Gdx.files.internal("bucket.png")),100);
        bullet = new Bullet(20,100,70,70,100);
        
        entityManager.addEntity(player);
        entityManager.addEntity(bullet);
        collisionManager.registerEntity((Collidable) player);
        collisionManager.registerEntity((Collidable) bullet);
    }

    @Override
    public void update() {
    	// switch to end scene
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            sceneManager.setScene(2); 
        }
    }

    @Override
    public void render() {
    	ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
    	float delta = Gdx.graphics.getDeltaTime();
        batch.begin();
        ((Player)player).shoot(delta,batch);
        batch.draw(image, 140, 210);
        batch.end();
    	
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
