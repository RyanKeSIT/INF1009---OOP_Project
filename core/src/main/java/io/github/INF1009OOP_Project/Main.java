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

import io.github.INF1009OOP_Project.UI.Button;

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
