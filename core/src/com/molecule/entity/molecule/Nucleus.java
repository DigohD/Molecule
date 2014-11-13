package com.molecule.entity.molecule;

import java.util.ArrayList;
import java.util.Vector;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.granule.NucleusTrail;
import com.molecule.entity.particle.Particle;
import com.molecule.entity.stats.StatsSheet;
import com.molecule.system.util.GranuleBuffer;
import com.molecule.system.util.TextureLoader;

public class Nucleus{

	private Sprite img;
	private float targetX, targetY, x, y;
	private float centerOffsetX, centerOffsetY;
	private float sineX, sineY, sineTime;
	private Color tint;
	
	private Rectangle rect;
	
	private NucleusTrail trail;
	
	private StatsSheet stats = new StatsSheet();
	
	ArrayList<Particle> children = new ArrayList<Particle>();
	
	public Nucleus(){
		this("core");
	}
	
	public Nucleus(String image){
		img = new Sprite(TextureLoader.textures.get(image));
		
		tint = new Color(1f, 1f, 1f, 1f);
		
		stats = new StatsSheet();
		
		centerOffsetX = img.getWidth() / 2;
		centerOffsetY = img.getHeight() / 2;
		
		trail = new NucleusTrail(this, 3, 10);
		
		rect = new Rectangle(0,0, img.getWidth(), img.getHeight());
	}
	
	public void draw(SpriteBatch batch, float targetX, float targetY){
		sineX = (float) (Math.sin(sineTime) * 10);
		sineY = (float) (Math.sin(sineTime / 3) * 10);
		
		sineTime = sineTime + 0.1f;
		
		x = targetX - centerOffsetX + sineX;
		y = targetY - centerOffsetY + sineY;
		
		rect.setX(x);
		rect.setY(y);

		GranuleBuffer.getGranule().spawn(10, "quark", getCenterX(), getCenterY(), 0, 0);
		
//		trail.tick(1f);
//		trail.render(batch);
		
		img.setColor(tint);
		img.setPosition(x, y);
		img.draw(batch);

		for(Particle p : children)
			p.draw(batch);
	}

	public void addParticle(Particle p){
		children.add(p);
	}
	
	public void removeParticle(Particle p){
		children.remove(p);
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
		return x + centerOffsetX + sineX;
	}
	
	public float getCenterY(){
		return y + centerOffsetX + sineY;
	}

	public void setTint(float r, float g, float b, float a) {
		this.tint = new Color(r, g, b, a);
	}

	public Sprite getImg() {
		return img;
	}

	public Rectangle getRect() {
		return rect;
	}
	
	
	
}
