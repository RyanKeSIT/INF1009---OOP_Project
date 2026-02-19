package io.github.INF1009OOP_Project.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.Collision.*;
import io.github.INF1009OOP_Project.Entities.*;
import io.github.INF1009OOP_Project.Entities.Components.PhysicsBody;
import io.github.INF1009OOP_Project.Entities.Components.Transform;
import io.github.INF1009OOP_Project.Entities.IO.IOManager;
import io.github.INF1009OOP_Project.UI.Text;


public class GameScene extends Scene {
    private ShapeRenderer shape;
    private Entity player,bullet;
    private Texture playerTexture;
    private Texture bulletTexture;
    private Texture obstacleTexture;
    
	public GameScene(SceneManager sceneManager, IOManager io) {
        super(sceneManager, io);
        shape = new ShapeRenderer();
        playerTexture = new Texture(Gdx.files.internal("Ship.png"));
        bulletTexture = new Texture(Gdx.files.internal("Bullet.png"));
        obstacleTexture = new Texture(Gdx.files.internal("bucket.png"));
        		
        entityManager.addEntity(new Text(300, 300, 200, 50, "Escape to pause!", 50,Color.WHITE, font), false);
        entityManager.addEntity(new Text(300, 400, 200, 50, "Enter to end game!", 50,Color.WHITE, font), false);
        
        player = EntityFactory.createPlayer(100,100,100,100, playerTexture,bulletTexture,entityManager, 100, io);
        bullet = EntityFactory.createObstacle(100, 400, 70, 70, obstacleTexture);
        io.getSound().soundOn();
        
        entityManager.addEntity(player,true);
        entityManager.addEntity(bullet,true);
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
            io.getSound().playShootingSound();
        }
        
        
        float delta = Gdx.graphics.getDeltaTime();
        entityManager.updateEntities(delta);
    }

    @Override
    public void render() {
    	ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
    	float delta = Gdx.graphics.getDeltaTime();
    	
    	ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.end();
    	
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
