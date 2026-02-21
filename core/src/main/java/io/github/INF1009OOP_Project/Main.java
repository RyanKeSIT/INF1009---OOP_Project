package io.github.INF1009OOP_Project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private GameMaster gameMaster;
    @Override
    public void create() {
        gameMaster = new GameMaster();
    }

    @Override
    public void render() {
    	ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
    	gameMaster.render();
    }

    @Override
    public void dispose() {
    	gameMaster.dispose();
    }
    
}
