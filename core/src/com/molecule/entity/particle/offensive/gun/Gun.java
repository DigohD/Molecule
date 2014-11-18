package com.molecule.entity.particle.offensive.gun;

import com.molecule.entity.molecule.Nucleus.Type;
import com.molecule.entity.particle.Particle;
import com.molecule.entity.particle.ParticleMod;
import com.molecule.system.Util;

public abstract class Gun extends ParticleMod{

	protected int CD, CDTimer, range, lifetime;
	protected float damage;
	
	public Gun(Particle parent) {
		super(parent);
	}
	
	@Override
	public void tick(float dt){
		if(Util.rnd.nextInt(10) == 0)
			return;
		if(CDTimer >= CD){
			if(parent.getOwnerType() == Type.ENEMY)
				enemyFire();
			if(parent.getOwnerType() == Type.PLAYER){
				playerFire();
			}
			fireEmitter();
			CDTimer = -1;
			playFireSound();
		}
		CDTimer++;
	}
	
	public abstract void playerFire();
	public abstract void enemyFire();
	public abstract void fireEmitter();
	public abstract void playFireSound();

	public int getCD() {
		return CD;
	}

	public int getRange() {
		return range;
	}

	public float getDamage() {
		return damage;
	}

	public int getLifetime() {
		return lifetime;
	}

	
}
