package com.molecule.entity.particle;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.granule.ParticleTrail;
import com.molecule.entity.molecule.Nucleus;
import com.molecule.entity.molecule.Nucleus.Type;

public abstract class Particle {

	protected Nucleus parent;
	protected Sprite img, dot;

	protected Vector2 centerV;
	
	protected ArrayList<ParticleMod> mods = new ArrayList<ParticleMod>();
	
	protected float sineTime, angleOffset, sineOffset, ellipseAngle;
	
	protected Color tint;
	
	protected int boostStacks;
	
	protected float centerX, centerY, drawOffsetX, drawOffsetY; 
	
	protected ParticleTrail trail;
	
	public Particle(Nucleus parent){
		this.parent = parent;
		
		centerV = new Vector2(0, 0);
		tint = new Color();
	}
	
	public void addParticleMod(ParticleMod mod){
		mods.add(mod);
	}
	
	public Vector2 getCenter(){
		return centerV;
	}
	
	public void setTint(float r, float g, float b, float a) {
		tint.set(r, g, b, a);
	}
	
	public Type getOwnerType(){
		return parent.getOwnerType();
	}
	
	public abstract void draw(SpriteBatch batch);
}
