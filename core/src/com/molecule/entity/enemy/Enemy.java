package com.molecule.entity.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.molecule.entity.molecule.Nucleus;
import com.molecule.entity.particle.Particle;
import com.molecule.entity.particle.offensive.QuarkGun;
import com.molecule.system.Util;

public class Enemy{

	private Nucleus nucleus;
	
	public Enemy(){
		nucleus = new Nucleus();
		nucleus.setTargetX(Util.rnd.nextInt(1800));
		nucleus.setTargetY(Util.rnd.nextInt(900));
		nucleus.setTint(1f, 0.4f, 0.4f, 1f);
		
		Particle p = new Particle(nucleus);
		p.addParticleMod(new QuarkGun(p));
		
		nucleus.addParticle(p);
	}
	
	public void draw(SpriteBatch batch){
		nucleus.draw(batch, nucleus.getTargetX(), nucleus.getTargetY());
	}
	
}
