package io.github.INF1009OOP_Project.Entities.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

import io.github.INF1009OOP_Project.Entities.Entity;
import io.github.INF1009OOP_Project.Entities.EntityFactory;
import io.github.INF1009OOP_Project.Entities.EntityManager;

public class PlayerShoot implements Component{
	
	private Texture bulletTexture;
	private EntityManager entityManager;
	private Entity player;
	
	public PlayerShoot(Entity player,Texture bullet) {
		this.bulletTexture = bullet;
		this.player = player;
	}
	
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
	@Override
    public void update(float delta) {
        shoot();
    }
	
	private void shoot() {

		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			if(entityManager ==null) return;
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

}
