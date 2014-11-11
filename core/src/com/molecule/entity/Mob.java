package com.molecule.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.molecule.system.util.PhysicsUtil;

public abstract class Mob extends Entity implements Tickable, Renderable{
	
	protected Vector2 velocity;
	protected Texture img;
	
	public Mob(Vector2 position){
		super(position);
	}
	
	protected void move(float dt){
		PhysicsUtil.move(position, velocity, dt);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(img, position.x, position.y);
	}

}
