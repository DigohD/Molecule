package com.molecule.entity.particle.offensive;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.Collideable;
import com.molecule.entity.DynamicEntity;
import com.molecule.system.util.TextureLoader;

public class Projectile extends DynamicEntity implements Collideable{
	
	public enum Type {
		PLAYER, ENEMY;
	}

	private Sprite img;
	private Vector2 v;
	private int lifetime;
	
	protected Type type;
	
	Projectile(Vector2 pos, Vector2 v, String imgName, int lifetime){
		super(pos);
		img = new Sprite(TextureLoader.textures.get(imgName));
		img.setPosition(pos.x, pos.y);
		position = pos.cpy();
		this.v = v;
		this.lifetime = lifetime;
	}

	@Override
	public void tick(float dt) {
		position.add(v);
		lifetime--;
		if(lifetime < 0)
			live = false;
	}

	@Override
	public void render(SpriteBatch batch) {
		img.setPosition(position.x, position.y);
		img.draw(batch);
	}

	@Override
	public void collisionWith(Collideable obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}

	public Type getType() {
		return type;
	}
	
	
}
