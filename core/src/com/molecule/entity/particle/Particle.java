package com.molecule.entity.particle;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.molecule.entity.Tickable;
import com.molecule.entity.granule.ParticleTrail;
import com.molecule.entity.molecule.Nucleus;
import com.molecule.entity.molecule.Nucleus.Type;
import com.molecule.entity.particle.defensive.Plasmatron;
import com.molecule.system.Util;

public abstract class Particle implements Tickable{

	protected Nucleus parent;
	protected Sprite img, dot;

	protected Vector2 centerV;
	
	protected ArrayList<ParticleMod> mods = new ArrayList<ParticleMod>();
	
	protected float sineTime, angleOffset, sineOffset, ellipseAngle;
	
	protected Color tint;
	protected Vector3 color;
	
	protected int boostStacks;
	
	protected float centerX, centerY, drawOffsetX, drawOffsetY; 
	
	protected ParticleTrail trail;
	
	protected String name;
	
	public Particle(Nucleus parent){
		this.parent = parent;
		name = "Particle " + Util.rnd.nextInt(999);
		
		centerV = new Vector2(0, 0);

		color = new Vector3(1,1,1);
	}
	
	public void addParticleMod(ParticleMod mod){
		mods.add(mod);
	}
	
	public void applyStatsChanges(){
		for(ParticleMod x : mods){
			if(x instanceof Plasmatron){
				((Plasmatron) x).applyStatChanges();
			}
		}
	}
	
	public void removeStatsChanges(){
		for(ParticleMod x : mods){
			if(x instanceof Plasmatron){
				((Plasmatron) x).removeStatChanges();
			}
		}
	}
	
	public Vector2 getCenter(){
		return centerV;
	}
	
	public void setTint(float r, float g, float b, float a) {
		tint.set(r, g, b, a);
		color.x = r;
		color.y = g;
		color.z = b;
	}
	
	public Type getOwnerType(){
		return parent.getOwnerType();
	}
	
	public Nucleus getParent(){
		return parent;
	}
	
	public void setParent(Nucleus parent) {
		this.parent = parent;
	}

	public abstract void draw(SpriteBatch batch);

	public Sprite getImg() {
		return img;
	}

	public String getName() {
		return name;
	}

	public ArrayList<ParticleMod> getMods() {
		return mods;
	}

	public Color getColor() {
		return tint;
	}
	
	
}
