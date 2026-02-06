package io.github.INF1009OOP_Project;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;



import io.github.INF1009OOP_Project.UI.Button;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Entity player;
    private Texture image;
    private ArrayList<Button> buttonList = new ArrayList<Button>();
    private BitmapFont font;
    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        font = new BitmapFont();
        buttonList.add(new Button(100,100, 100, 50,"Options", 20, font));
        buttonList.add(new Button(100,300, 100, 50,"Start", 20, font));
        player = new PlayableEntity(100,100,100,100, new Texture(Gdx.files.internal("bucket.png")),100);
        
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
                	for(Button b : buttonList) {
                    	if(b.isHover(screenX, y)) {
                    		b.onClick();
                    	}
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void render() {
    	float delta = Gdx.graphics.getDeltaTime();
    	ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
    	((PlayableEntity) player).move(player.getX(),player.getY(),delta); //move example
        batch.begin();
        player.draw(batch); //draw example
        batch.draw(image, 140, 210);
        for(Button b : buttonList) {
        	b.draw(batch);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
