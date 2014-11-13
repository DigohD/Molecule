package com.molecule.entity.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.Collideable;
import com.molecule.entity.DynamicEntity;
import com.molecule.entity.Entity;
import com.molecule.entity.Tickable;
import com.molecule.entity.molecule.Nucleus;
import com.molecule.entity.molecule.Nucleus.Type;
import com.molecule.system.EntityManager;
import com.molecule.system.util.PhysicsUtil;

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
//		position = position.add(velocity);
	}
	
	public void render(SpriteBatch batch){
		nucleus.render(batch);
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
	public void collisionWith(Collideable obj) {
		
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
