package io.github.INF1009OOP_Project.Scene;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.IOManager;
import io.github.INF1009OOP_Project.Collision.*;
import io.github.INF1009OOP_Project.Entities.*;
import io.github.INF1009OOP_Project.Entities.Components.PhysicsBody;
import io.github.INF1009OOP_Project.Entities.Components.PlayerShoot;
import io.github.INF1009OOP_Project.Entities.Components.Transform;
import io.github.INF1009OOP_Project.UI.Text;


public class GameScene extends Scene {
	//private SpriteBatch batch;
    //private Entity player,bullet;
	
	//private Texture image;
    private ShapeRenderer shape;
    //private EntityManager entityManager;
    private Entity player,bullet;
    //private BitmapFont font;
    private Texture playerTexture;
    private Texture bulletTexture;
    
	public GameScene(SceneManager sceneManager, IOManager io) {
        super(sceneManager, io);
        //entityManager = new EntityManager();
        //batch = new SpriteBatch();
        shape = new ShapeRenderer();
        //image = new Texture("libgdx.png");
        //font = new BitmapFont();
        //player = new Player(100,100,100,100, new Texture(Gdx.files.internal("bucket.png")),100);
        //bullet = new Bullet(20,100,70,70,100);
        playerTexture = new Texture(Gdx.files.internal("bucket.png"));
        bulletTexture = new Texture(Gdx.files.internal("droplet.png"));
        
        entityManager.addEntity(new Text(300, 300, 200, 50, "Escape to pause!", 50,Color.WHITE, font), false);
        entityManager.addEntity(new Text(300, 400, 200, 50, "Enter to end game!", 50,Color.WHITE, font), false);
        
        player = EntityFactory.createPlayer(100,100,100,100, playerTexture,bulletTexture,entityManager, 100, io);
        bullet = EntityFactory.createObstacle(100, 400, 70, 70, bulletTexture);
        //player.get(PlayerShoot.class).setEntityManager(entityManager);
        //player.get(PlayerShoot.class).setIOManager(io);
        io.getSound().soundOn();
        
        entityManager.addEntity(player,true);
        entityManager.addEntity(bullet,true);
        
        
        /*Gdx.input.setInputProcessor(new InputAdapter() {
        	@Override
        	public boolean keyDown (int keycode) {
        		if(keycode == Keys.SPACE) {
        			((Player)player).shoot();
        		}
        		return false;
        	}
        });*/
    }

    @Override
    public void update() {
    	// switch to end scene
        if (io.getKeyboard().isKeyPressed(Keys.ENTER)) {
            sceneManager.setScene(2); 
        }

        if(io.getKeyboard().isKeyPressed(Keys.ESCAPE)) {
        	System.out.println("Pause game");
        	sceneManager.setScene(3);
        }

        if (io.getKeyboard().isKeyJustPressed(Keys.SPACE)) {
            shoot();
        }
        
        
        float delta = Gdx.graphics.getDeltaTime();
        /*
        if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
        // if (io.getKeyboard().isKeyPressed(Keys.SPACE)) {
            shoot();
            io.getSound().playShootingSound();
        }
        }*/
        entityManager.updateEntities(delta);
    }

    @Override
    public void render() {
    	ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
    	float delta = Gdx.graphics.getDeltaTime();
    	
    	ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.end();
    	
    	// Keyboard, Mouse, Sound test
    	
    	//batch.begin();
    	//batch.draw(image, 140, 210);
    	//batch.end();
    	// entityManager.updateEntities(delta);
    	entityManager.draw(batch);
    	
    	//visualize bounds hitbox
    	shape.begin(ShapeRenderer.ShapeType.Line);
    	
    	for (Entity entity : entityManager.getEntities()) {  
    	    PhysicsBody pb = entity.get(PhysicsBody.class);
    	    if (pb != null) {
    	        Bounds b = pb.getBounds();
    	        shape.rect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
    	    }
    	}

        shape.end();
    }

    @Override
    public void dispose() {
       
    }
    
    //methods
    
    private void shoot() {
        Transform pt = player.get(Transform.class);
        if (pt == null) return;
        
        float bw = 70;
        float bh = 70;
        
        float bx = pt.getX() + pt.getWidth() / 2f - bw / 2f;
        float by = 75 + pt.getY() + pt.getHeight()/2f - 10; //add 75 for it to spawn slightly above player 
        Entity newBullet = EntityFactory.createBullet(bx, by, 70, 70, 300, bulletTexture,entityManager);

        entityManager.addEntity(newBullet, true);

    }
}
