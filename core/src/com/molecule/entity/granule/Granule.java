package com.molecule.entity.granule;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.DynamicEntity;
import com.molecule.system.EntityManager;
import com.molecule.system.util.TextureLoader;

public class Granule extends DynamicEntity{

	private Sprite sprite;
	private int lifetime;
	private float posX, posY, vX, vY;
	
	public Granule() {
		super(null);
		sprite = new Sprite();
		sprite.setPosition(0, 0);
		live = true;
		lifetime = 0;
	}

	@Override
	public void tick(float dt) {
		if(lifetime <= 0){
			live = false;
		}
		lifetime--;
		posX += vX;
		posY += vY;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		sprite.setPosition(posX, posY);
		sprite.draw(batch);
	}
	
	public void spawn(int lifetime, String imageName){
		sprite.setTexture(TextureLoader.textures.get(imageName));
		this.lifetime = lifetime;
		live = true;
	}
	
	public void spawn(int lifetime, String imageName, float posX, float posY){
		sprite.setRegion(TextureLoader.textures.get(imageName));
		sprite.setSize(TextureLoader.textures.get(imageName).getWidth(), TextureLoader.textures.get(imageName).getHeight());
		this.lifetime = lifetime;
		live = true;
		
		this.posX = posX;
		this.posY = posY;
		
		EntityManager.addEntity(this);
	}
	
	public void spawn(int lifetime, String imageName, float posX, float posY, float vX, float vY){
		sprite.setRegion(TextureLoader.textures.get(imageName));
		sprite.setSize(TextureLoader.textures.get(imageName).getWidth(), TextureLoader.textures.get(imageName).getHeight());
		this.lifetime = lifetime;
		live = true;
		
		this.vX = vX;
		this.vY = vY;
		this.posX = posX;
		this.posY = posY;
		
		EntityManager.addEntity(this);
	}

	public void setLifetime(int lifetime) {
		this.lifetime = lifetime;
	}
	
	

}
