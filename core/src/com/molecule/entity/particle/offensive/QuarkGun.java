package com.molecule.entity.particle.offensive;

import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.granule.emitter.Emitter;
import com.molecule.entity.molecule.Nucleus.Type;
import com.molecule.entity.particle.ExternalParticle;
import com.molecule.entity.particle.ParticleMod;
import com.molecule.system.EntityManager;
import com.molecule.system.Renderer;
import com.molecule.system.Util;
import com.molecule.system.util.EnemyLogic;
import com.molecule.system.util.PlayerLogic;
import com.molecule.system.util.SoundLoader;

public class QuarkGun extends ParticleMod{
	
	private int CD, CDTimer, range;
	private float damage, lifetime;
	
	private class Quark extends Projectile{
		public Quark(Vector2 pos, Vector2 v){
			super(pos, v, "quark", 100, 1.5f, parent.getOwnerType());
			EntityManager.addEntity(this);
		}
	}
	
	public QuarkGun(ExternalParticle parent){
		super(parent);
		CD = 65;
		CDTimer = 0;
		range = 8;
		damage = 1.5f;
	}

	@Override
	public void tick(float dt) {
		if(Util.rnd.nextInt(10) == 0)
			return;
		if(CDTimer >= CD){
			if(parent.getOwnerType() == Type.ENEMY)
				new Quark(parent.getCenter(), EnemyLogic.getPlayerDir(parent.getCenter()).nor().scl(range));
			if(parent.getOwnerType() == Type.PLAYER){
				new Quark(parent.getCenter(), PlayerLogic.getEnemyDir(parent.getCenter(), PlayerLogic.findNearestEnemy(parent.getCenter())).nor().scl(range));
			}
			Emitter emitter = new Emitter(parent.getCenter(), 10, 3, 15, "quark");
			emitter.setSpreadRadial(new Vector2(1, 0));
			emitter.setRandomness(4f);
			CDTimer = -1;
			SoundLoader.sounds.get("quarkgun").play();
		}
		CDTimer++;
	}

	@Override
	public void drawMod(Renderer renderer, float x, float y) {
		font.setScale(1.3f);
		
		font.draw(renderer.getBatch(), getName(), x, y);
		
		font.setScale(1.1f);
		
		range = 8;
		damage = 1.5f;
		
		String CooldownS = String.format("%.1f", ((float) CD) / 60);
		
		font.draw(renderer.getBatch(), "Cooldown: ", x, y - 100);
		font.draw(renderer.getBatch(), CooldownS + "s", x + 300, y - 100);
		
		font.draw(renderer.getBatch(), "Damage: ", x, y - 160);
		font.draw(renderer.getBatch(), 1.5f + "", x + 300, y - 160);
		
		font.draw(renderer.getBatch(), "Range: ", x, y - 220);
		font.draw(renderer.getBatch(), range * 100 + "", x + 300, y - 220);
		
		font.draw(renderer.getBatch(), "Proj. Speed: ", x, y - 280);
		font.draw(renderer.getBatch(), 100 + "", x + 300, y - 280);
	}

	@Override
	public int getDrawHeight() {
		return 380;
	}

	@Override
	public String getName() {
		return "Quark Gun - Single Shot";
	}
	
	
	
}
