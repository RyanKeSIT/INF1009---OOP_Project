package io.github.INF1009OOP_Project.Scene;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.INF1009OOP_Project.Entities.EntityManager;
import io.github.INF1009OOP_Project.Entities.IO.IOManager;

public abstract class Scene {

	protected SceneManager sceneManager;
	protected IOManager io;

	protected EntityManager entityManager;
	protected SpriteBatch batch;
	protected BitmapFont font;

	public Scene(SceneManager sceneManager, IOManager io) {
		this.sceneManager = sceneManager;
		this.io = io;
	
		this.entityManager = new EntityManager();
		this.batch = new SpriteBatch();
		this.font = new BitmapFont();
	}

	public abstract void update();

	public abstract void render();

	public abstract void dispose();

}
