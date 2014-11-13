package com.molecule.entity.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.Collideable;
import com.molecule.entity.DynamicEntity;
import com.molecule.entity.Entity;
import com.molecule.entity.Tickable;
import com.molecule.entity.molecule.Nucleus;
import com.molecule.entity.molecule.Nucleus.Type;
import com.molecule.entity.particle.Particle;
import com.molecule.entity.particle.offensive.QuarkGun;
import com.molecule.system.EntityManager;
import com.molecule.system.Util;

public class Enemy extends Entity implements Tickable, Collideable{

	private Nucleus nucleus;
	
	public Enemy(){
		super(new Vector2(0, 0));
		nucleus = new Nucleus("core", new Vector2(Util.rnd.nextInt(1800), Util.rnd.nextInt(900)), Type.ENEMY);
		nucleus.setTint(1f, 0.4f, 0.4f, 1f);
		
		Particle p = new Particle(nucleus);
		p.addParticleMod(new QuarkGun(p));
		p.setTint(1f, 0.1f, 0.1f, 1f);
		nucleus.addParticle(p);
		
		p = new Particle(nucleus);
		p.setTint(1f, 0.1f, 0.1f, 1f);
		nucleus.addParticle(p);
		p = new Particle(nucleus);
		p.setTint(1f, 0.1f, 0.1f, 1f);
		nucleus.addParticle(p);
		
		EntityManager.addEntity(this);
	}
	
	public void render(SpriteBatch batch){
		nucleus.render(batch);
	}

	@Override
	public void tick(float dt) {
		
	}

	@Override
	public void collisionWith(Collideable obj) {
		
	}

	@Override
	public Rectangle getRect() {
		return nucleus.getRect();
	}
	
	public Nucleus getNucleus(){
		return nucleus;
	}
	
	@Override
	public Vector2 getPosition(){
		return nucleus.getPosition();
	}
}
