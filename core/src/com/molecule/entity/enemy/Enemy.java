package com.molecule.entity.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.Collideable;
import com.molecule.entity.DynamicEntity;
import com.molecule.entity.molecule.Nucleus;
import com.molecule.entity.particle.Particle;
import com.molecule.entity.particle.offensive.QuarkGun;
import com.molecule.system.Util;

public class Enemy extends DynamicEntity implements Collideable{

	private Nucleus nucleus;
	
	public Enemy(){
		super(new Vector2(Util.rnd.nextInt(1800), Util.rnd.nextInt(900)));
		nucleus = new Nucleus();
		nucleus.setTargetX(Util.rnd.nextInt(1800));
		nucleus.setTargetY(Util.rnd.nextInt(900));
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
	}
	
	public void draw(SpriteBatch batch){
		nucleus.draw(batch, nucleus.getTargetX(), nucleus.getTargetY());
	}

	@Override
	public void tick(float dt) {
		
	}

	@Override
	public void collisionWith(Collideable obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}
}
