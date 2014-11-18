package com.molecule.entity.particle.offensive.gun;

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

public class QuarkGun extends Gun{
	
	private class Quark extends Projectile{
		public Quark(Vector2 pos, Vector2 v, Gun source){
			super(pos, v, "quark", source.getLifetime(), source.getDamage(), parent.getOwnerType());
			EntityManager.addEntity(this);
		}
	}
	
	public QuarkGun(ExternalParticle parent){
		super(parent);
		CD = 65;
		CDTimer = 0;
		range = 8;
		damage = 1.5f;
		lifetime = 80;
	}

	@Override
	public void drawMod(Renderer renderer, float x, float y) {
		font.setScale(1.3f);
		
		font.draw(renderer.getBatch(), getName(), x, y);
		
		fontLight.setScale(1.1f);
		
		range = 8;
		damage = 1.5f;
		
		String CooldownS = String.format("%.1f", ((float) CD) / 60);
		
		fontLight.draw(renderer.getBatch(), "Cooldown: ", x, y - 60);
		fontLight.draw(renderer.getBatch(), CooldownS + "s", x + 500, y - 60);
		
		fontLight.draw(renderer.getBatch(), "Damage: ", x, y - 120);
		fontLight.draw(renderer.getBatch(), 1.5f + "", x + 500, y - 120);
		
		fontLight.draw(renderer.getBatch(), "Range: ", x, y - 180);
		fontLight.draw(renderer.getBatch(), range * lifetime + "", x + 500, y - 180);
		
		fontLight.draw(renderer.getBatch(), "Proj. Speed: ", x, y - 240);
		fontLight.draw(renderer.getBatch(), 100 + "", x + 500, y - 240);
	}

	@Override
	public int getDrawHeight() {
		return 340;
	}

	@Override
	public String getName() {
		return "Quark Gun - Single Shot";
	}

	@Override
	public void playerFire() {
		new Quark(parent.getCenter(), PlayerLogic.getEnemyDir(parent.getCenter(), PlayerLogic.findNearestEnemy(parent.getCenter())).nor().scl(range), this);
	}

	@Override
	public void enemyFire() {
		new Quark(parent.getCenter(), EnemyLogic.getPlayerDir(parent.getCenter()).nor().scl(range), this);
	}

	@Override
	public void fireEmitter() {
		Emitter emitter = new Emitter(parent.getCenter(), 10, 3, 15, "quark");
		emitter.setSpreadRadial(new Vector2(1, 0));
		emitter.setRandomness(4f);
	}

	@Override
	public void playFireSound() {
		SoundLoader.sounds.get("quarkgun").play();
	}
	
	
	
}
