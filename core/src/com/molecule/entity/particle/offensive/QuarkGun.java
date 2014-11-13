package com.molecule.entity.particle.offensive;

import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.molecule.Nucleus.Type;
import com.molecule.entity.particle.Particle;
import com.molecule.entity.particle.ParticleMod;
import com.molecule.system.EntityManager;
import com.molecule.system.util.EnemyLogic;
import com.molecule.system.util.PlayerLogic;

public class QuarkGun extends ParticleMod{
	
	private class Quark extends Projectile{
		public Quark(Vector2 pos, Vector2 v){
			super(pos, v, "quark", 4500, 1.5f, parent.getOwnerType());
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
			if(parent.getOwnerType() == Type.ENEMY)
				new Quark(parent.getCenter(), EnemyLogic.getPlayerDir(parent.getCenter()).nor().scl(12));
			else if(parent.getOwnerType() == Type.PLAYER){
				new Quark(parent.getCenter(), PlayerLogic.getEnemyDir(parent.getCenter(), PlayerLogic.findNearestEnemy(parent.getCenter())).nor().scl(12));
				System.out.println(parent.getCenter().toString());
			}
			CDTimer = -1;
		}
		CDTimer++;
	}
	
	
	
}
