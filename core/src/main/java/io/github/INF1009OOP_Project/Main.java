package io.github.INF1009OOP_Project;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Game;


import io.github.INF1009OOP_Project.UI.Button;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private SpriteBatch batch;
    private PlayableEntity player;
    private NonPlayableEntity nonPlayer;
    private Texture image;
    private ShapeRenderer shape;
    private ArrayList<Button> buttonList = new ArrayList<Button>();
    private BitmapFont font;
    private Button startButton, optionsButton;
    private EntityManager entityManager = new EntityManager();
    private CollisionManager collisionManager = new CollisionManager();
    @Override
    public void create() {
        batch = new SpriteBatch();
        shape = new ShapeRenderer();

        image = new Texture("libgdx.png");
        font = new BitmapFont();
        
        optionsButton = new Button(100,100, 100, 50,"Options", 20, font);
        startButton = new Button(100,300, 100, 50,"Start", 20, font);
        entityManager.addEntity(optionsButton);
        entityManager.addEntity(startButton);
        
        player = new PlayableEntity(100,100,100,100, new Texture(Gdx.files.internal("bucket.png")),100);
        nonPlayer = new NonPlayableEntity(20,100,70,70, new Texture(Gdx.files.internal("droplet.png")),100);
        
        entityManager.addEntity(player);
        entityManager.addEntity(nonPlayer);
        collisionManager.registerEntity(player);
        collisionManager.registerEntity(nonPlayer);
        
        // Example usage of button
        Gdx.input.setInputProcessor(new InputAdapter() {
        	@Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (button == Input.Buttons.LEFT) {
                    
                	// Convert input coordinates to canvas coordinates
                	// input coords: y0 is top left of window
                	// canvas coords: y0 is bottom left of window
                    float y = Gdx.graphics.getHeight() - screenY;
                    System.out.println("Left click at " + screenX + ", " + y);
                    for (Entity e : entityManager.getEntities()) {
                        if (e instanceof Button) {
                            Button b = (Button) e;
                            if (b.isHover(screenX, y)) b.onClick();
                        }
                    }
                }
                return true;
            }
        });
        
        setScreen(new IOTest()); // io test
    }

    @Override
    public void render() {
    	super.render();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, 140, 210);
        for(Button b : buttonList) {
        	b.draw(batch);
        }
        batch.end();
    	ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
    	
    	//batch.begin();
    	//batch.draw(image, 140, 210);
    	//batch.end();
    	
    	float delta = Gdx.graphics.getDeltaTime();
    	entityManager.moveAll();
    	entityManager.updateEntities();
    	collisionManager.update();
    	entityManager.draw(batch, shape);
    	
    	//visualize bounds hitbox
    	shape.begin(ShapeRenderer.ShapeType.Line);

        Bounds a = player.getBounds();
        shape.rect(a.getX(), a.getY(), a.getWidth(), a.getHeight());

        Bounds b = nonPlayer.getBounds();
        shape.rect(b.getX(), b.getY(), b.getWidth(), b.getHeight());

        shape.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
    
}
