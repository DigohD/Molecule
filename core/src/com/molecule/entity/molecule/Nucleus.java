package com.molecule.entity.molecule;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.molecule.entity.stats.StatsSheet;
import com.molecule.system.util.TextureLoader;

public class Nucleus{

	private Texture img;
	private float targetX, targetY, x, y;
	private float centerOffsetX, centerOffsetY;
	
	private StatsSheet stats = new StatsSheet();
	
	private Particle child, child2, child3;
	
	public Nucleus(){
		img = TextureLoader.textures.get("core");
		
		child = new Particle(this);
		child2 = new Particle(this, 6.28f / 3, 3.14f / 3);
		child3 = new Particle(this, (6.28f / 3) * 2, (6.28f / 3) * 2);
		
		stats = new StatsSheet();
		
		centerOffsetX = img.getWidth() / 2;
		centerOffsetY = img.getHeight() / 2;
	}
	
	public Nucleus(String image){
		img = TextureLoader.textures.get(image);
		
		centerOffsetX = img.getWidth() / 2;
		centerOffsetY = img.getHeight() / 2;
	}
	
	public void draw(SpriteBatch batch, float targetX, float targetY){
		x = targetX - centerOffsetX;
		y = targetY - centerOffsetY;
		
		batch.draw(img, x, y);
		child.draw(batch);
		child2.draw(batch);
		child3.draw(batch);
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
		return y + centerOffsetX;
	}
	
	
	
}
