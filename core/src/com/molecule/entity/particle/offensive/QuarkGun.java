package com.molecule.entity.particle.offensive;

import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.granule.emitter.Emitter;
import com.molecule.entity.molecule.Nucleus.Type;
import com.molecule.entity.particle.ExternalParticle;
import com.molecule.entity.particle.ParticleMod;
import com.molecule.system.EntityManager;
import com.molecule.system.Util;
import com.molecule.system.util.EnemyLogic;
import com.molecule.system.util.PlayerLogic;
import com.molecule.system.util.SoundLoader;

public class QuarkGun extends ParticleMod{
	
	private class Quark extends Projectile{
		public Quark(Vector2 pos, Vector2 v){
			super(pos, v, "quark", 100, 1.5f, parent.getOwnerType());
			EntityManager.addEntity(this);
		}
	}

	private int CD, CDTimer;
	
	public QuarkGun(ExternalParticle parent){
		super(parent);
		CD = 10;
		CDTimer = 0;
	}

	@Override
	public void tick(float dt) {
		if(Util.rnd.nextInt(10) == 0)
			return;
		if(CDTimer >= CD){
//			if(parent.getOwnerType() == Type.ENEMY)
//				new Quark(parent.getCenter(), EnemyLogic.getPlayerDir(parent.getCenter()).nor().scl(12));
			if(parent.getOwnerType() == Type.PLAYER){
				new Quark(parent.getCenter(), PlayerLogic.getEnemyDir(parent.getCenter(), PlayerLogic.findNearestEnemy(parent.getCenter())).nor().scl(12));
				System.out.println(parent.getCenter().toString());
			}
			Emitter emitter = new Emitter(parent.getCenter(), 10, 3, 15, "quark");
			emitter.setSpreadRadial(new Vector2(1, 0));
			emitter.setRandomness(4f);
			CDTimer = -1;
			SoundLoader.sounds.get("quarkgun").play();
		}
		CDTimer++;
	}
	
	
	
}
