package com.molecule.entity.enemy;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.Collideable;
import com.molecule.entity.Entity;
import com.molecule.entity.Tickable;
import com.molecule.entity.granule.emitter.Emitter;
import com.molecule.entity.molecule.Nucleus;
import com.molecule.entity.molecule.Nucleus.Type;
import com.molecule.entity.particle.ExternalParticle;
import com.molecule.entity.particle.offensive.gun.Projectile;
import com.molecule.entity.particle.offensive.gun.QuarkGun;
import com.molecule.entity.stats.StatsSheet.StatID;
import com.molecule.system.EntityManager;
import com.molecule.system.Util;
import com.molecule.system.util.SoundLoader;

public class Enemy extends Entity implements Tickable, Collideable{

	private Nucleus nucleus;
	
	public Enemy(){
		super(new Vector2(0, 0));
		nucleus = new Nucleus("core", new Vector2(Util.rnd.nextInt(1800), Util.rnd.nextInt(900)), Type.ENEMY);
		nucleus.setTint(1f, 0.4f, 0.4f, 1f);
		
		ExternalParticle p = new ExternalParticle(nucleus);
		p.addParticleMod(new QuarkGun(p));
		p.setTint(1f, 0.1f, 0.1f, 1f);
		nucleus.addParticle(p);
		
		EntityManager.addEntity(this);
	}

	@Override
	public void tick(float dt) {
		
	}

	@Override
	public void collisionWith(Collideable obj) {
		if(obj instanceof Projectile){
			float hpOld = nucleus.getStats().getStat(StatID.HP_NOW).getBase();
			Projectile p = (Projectile) obj;
			float newHP = hpOld - p.getDamage();
			if(newHP <= 0){
				nucleus.setLive(false);
				live = false;
				SoundLoader.sounds.get("death").play();
				
				Emitter emitter = new Emitter(nucleus.getPosition(), 5, 20, 15, "gas");
				emitter.setSpreadRadial(new Vector2(1, 0));
				emitter.setRandomness(20);
				
				return;
			}else
				nucleus.getStats().getStat(StatID.HP_NOW).setNewBase(newHP);
		}
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
