package com.molecule.entity.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.Collideable;
import com.molecule.entity.DynamicEntity;
import com.molecule.entity.molecule.Nucleus;
import com.molecule.system.util.PhysicsUtil;

public class Player extends DynamicEntity implements Collideable{

	private Vector2 targetVel;
	private Nucleus nucleus;
	private boolean update;
	private Rectangle rect;
	
	public Player(Vector2 position){
		super(position);
		nucleus = new Nucleus();
		velocity = new Vector2(0,0);
		targetVel = new Vector2(0,0);
		
		rect = new Rectangle(position.x, position.y, nucleus.getImg().getWidth(), nucleus.getImg().getHeight());
	}

	@Override
	public void tick(float dt) {
		rect.setX(position.x);
		rect.setY(position.y);
		if(!update){
			targetVel.x = 0;
			targetVel.y = 0;
		}
		
		velocity.x = PhysicsUtil.approach(targetVel.x, velocity.x, dt * 5.0f);
		velocity.y = PhysicsUtil.approach(targetVel.y, velocity.y, dt * 5.0f);
		
		position = position.add(velocity);
		
	}
	
	public void render(SpriteBatch batch){
		nucleus.draw(batch, position.x, position.y);
	}
	
	@Override
	public void collisionWith(Collideable obj) {
		
		
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
		return rect;
	}
	
	
	
}
