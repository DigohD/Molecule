package com.molecule.entity.player;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.Collideable;
import com.molecule.entity.Entity;
import com.molecule.entity.Tickable;
import com.molecule.entity.granule.emitter.Emitter;
import com.molecule.entity.molecule.Nucleus;
import com.molecule.entity.molecule.Nucleus.Type;
import com.molecule.entity.particle.offensive.Projectile;
import com.molecule.entity.stats.StatsSheet.StatID;
import com.molecule.system.EntityManager;
import com.molecule.system.util.PhysicsUtil;
import com.molecule.system.util.SoundLoader;

public class Player extends Entity implements Tickable, Collideable{

	private Vector2 targetVel, velocity;
	private Nucleus nucleus;
	private boolean update;
	
	public Player(Vector2 position){
		super(position);
		nucleus = new Nucleus(Type.PLAYER);
		velocity = new Vector2(0,0);
		targetVel = new Vector2(0,0);
		
		EntityManager.addEntity(this);
	}

	@Override
	public void tick(float dt) {
		if(!update){
			targetVel.x = 0;
			targetVel.y = 0;
		}
		
		velocity.x = PhysicsUtil.approach(targetVel.x, velocity.x, dt * 5.0f);
		velocity.y = PhysicsUtil.approach(targetVel.y, velocity.y, dt * 5.0f);
		
		nucleus.setVelocity(velocity);
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
				
				Emitter emitter = new Emitter(nucleus.getPosition(), 25, 2, 5, "gas");
				emitter.setSpreadRadial(new Vector2(1, 0));
				emitter.setRandomness(20f);
				
				return;
			}else
				nucleus.getStats().getStat(StatID.HP_NOW).setNewBase(newHP);
		}
	}

	public Vector2 getTargetVel() {
		return targetVel;
	}

	public void setTargetVel(float x, float y) {
		targetVel.x = x;
		targetVel.y = y;
		update = true;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public Nucleus getNucleus() {
		return nucleus;
	}

	

	@Override
	public Rectangle getRect() {
		return nucleus.getRect();
	}
	
	@Override
	public Vector2 getPosition(){
		return nucleus.getPosition();
	}
	
}
