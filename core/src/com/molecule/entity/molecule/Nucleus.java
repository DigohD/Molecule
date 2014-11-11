package com.molecule.entity.molecule;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.molecule.system.util.TextureLoader;

public class Nucleus{

	private Texture img;
	private float targetX, targetY, x, y;
	private float centerOffsetX, centerOffsetY;
	
	private Particle child;
	
	public Nucleus(){
		img = TextureLoader.textures.get("core");
		
		child = new Particle(this);
		
		centerOffsetX = img.getWidth() / 2;
		centerOffsetY = img.getHeight() / 2;
	}
	
	public Nucleus(String image){
		img = TextureLoader.textures.get(image);
		
		centerOffsetX = img.getWidth() / 2;
		centerOffsetY = img.getHeight() / 2;
	}
	
	public void draw(SpriteBatch batch){
		x = targetX;
		y = targetY;
		
		batch.draw(img, x - centerOffsetX, y - centerOffsetY);
		child.draw(batch);
	}

	public float getTargetX() {
		return targetX;
	}

	public void setTargetX(float targetX) {
		this.targetX = targetX;
	}

	public float getTargetY() {
		return targetY;
	}

	public void setTargetY(float targetY) {
		this.targetY = targetY;
	}
	
	public float getCenterX(){
		return x + centerOffsetX;
	}
	
	public float getCenterY(){
		return y + centerOffsetY;
	}
	
	
	
}
