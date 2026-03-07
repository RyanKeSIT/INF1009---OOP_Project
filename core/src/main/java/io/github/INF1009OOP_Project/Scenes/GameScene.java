package io.github.INF1009OOP_Project.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.INF1009OOP_Project.EntityFactory;
import io.github.INF1009OOP_Project.Engine.Collision.*;
import io.github.INF1009OOP_Project.Engine.Entities.*;
import io.github.INF1009OOP_Project.Engine.Entities.Components.PhysicsBody;
import io.github.INF1009OOP_Project.Engine.Entities.Components.Transform;
import io.github.INF1009OOP_Project.Engine.Entities.UI.Text;
import io.github.INF1009OOP_Project.Engine.IO.IOManager;
import io.github.INF1009OOP_Project.Engine.Scene.Scene;
import io.github.INF1009OOP_Project.Engine.Scene.SceneManager;


public class GameScene extends Scene {
    private ShapeRenderer shape;
    private Entity player,bullet;
    private Texture playerTexture;
    private Texture bulletTexture;
    private Texture obstacleTexture;
    
    private int qnCount = 10;
    
	public GameScene(SceneManager sceneManager, IOManager io, boolean[] options) {
        super(sceneManager, io);
        shape = new ShapeRenderer();
        playerTexture = new Texture(Gdx.files.internal("Ship.png"));
        bulletTexture = new Texture(Gdx.files.internal("Bullet.png"));
        obstacleTexture = new Texture(Gdx.files.internal("bucket.png"));
        		
        io.getSound().soundOn();
        initializeGame();
    }

    @Override
    public void update() {
    	
        if (io.getKeyboard().isKeyPressed(Keys.ENTER)) {
            // if player die or game ends, push end scene
        	 sceneManager.push(new EndScene(sceneManager, io));
        }

        if(io.getKeyboard().isKeyPressed(Keys.ESCAPE)) {
        	System.out.println("Pause game");
        	// push pause scene
        	 sceneManager.push(new PauseScene(sceneManager, io));
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


    
    //methods
    
    private void initializeGame() {
        // clear old entities first
        entityManager.clearAll();
        
        //UI
        entityManager.addEntity(new Text(300, 300, 200, 50, "Escape to pause!", 50,Color.WHITE, font), false);
        entityManager.addEntity(new Text(300, 400, 200, 50, "Enter to end game!", 50,Color.WHITE, font), false);
        
        //create player and bullet
        player = EntityFactory.createPlayer(100,100,100,100, playerTexture,bulletTexture,entityManager, 100, io);
        bullet = EntityFactory.createObstacle(100, 400, 70, 70, obstacleTexture);
        
        entityManager.addEntity(player,true);
        entityManager.addEntity(bullet,true);
        
    }
    
    //acts as getter method for initializeGame so startscene can access
    public void resetGame() {
        initializeGame();
    }
    
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

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
    
    
}