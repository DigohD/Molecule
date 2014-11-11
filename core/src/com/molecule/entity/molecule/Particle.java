package com.molecule.entity.molecule;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.molecule.system.TextureLoader;

public class Particle{

	private Nucleus parent;
	private Texture img;
	
	private float xOffset, yOffset, centerOffsetX, centerOffsetY; 
	
	public Particle(Nucleus parent){
		this.parent = parent;
		img = TextureLoader.textures.get("particle");
		
		xOffset = 55;
		
		centerOffsetX = img.getWidth() / 2;
		centerOffsetY = img.getHeight() / 2;
	}
	
	public void draw(SpriteBatch batch){
		float x = parent.getCenterX() + xOffset - centerOffsetX;
		float y = parent.getCenterY() + yOffset - centerOffsetY;
		
		batch.draw(img, x, y);
	}
	
}