package com.molecule.entity.particle.offensive;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.Entity;
import com.molecule.entity.Tickable;
import com.molecule.entity.particle.Particle;
import com.molecule.entity.particle.ParticleMod;
import com.molecule.system.EntityManager;
import com.molecule.system.util.EnemyLogic;

public class QuarkGun extends ParticleMod{
	
	private class Quark extends Projectile{
		public Quark(Vector2 pos, Vector2 v){
			super(pos, v, "quark", 45);
			type = Type.PLAYER;
			EntityManager.addEntity(this);
		}
	}

	private int CD, CDTimer;
	
	public QuarkGun(Particle parent){
		super(parent);
		CD = 60;
		CDTimer = 0;
	}

	@Override
	public void tick(float dt) {
		if(CDTimer >= CD){
			new Quark(parent.getCenter(), EnemyLogic.getPlayerDir(parent.getCenter()).nor().scl(12));
			CDTimer = -1;
		}
		CDTimer++;
	}
	
	
	
}
