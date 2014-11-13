package com.molecule.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.molecule.system.util.PhysicsUtil;

public abstract class DynamicEntity extends Entity implements Tickable, Renderable{
	
	protected Vector2 velocity;
	protected Sprite sprite;
	
	public DynamicEntity(Vector2 position){
		super(position);
	}
	
	protected void move(float dt){
		PhysicsUtil.move(position, velocity, dt);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(sprite, position.x, position.y);
	}

}
