package com.molecule.entity.particle.offensive;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.Collideable;
import com.molecule.entity.DynamicEntity;
import com.molecule.entity.enemy.Enemy;
import com.molecule.entity.molecule.Nucleus.Type;
import com.molecule.entity.player.Player;
import com.molecule.system.util.TextureLoader;

public class Projectile extends DynamicEntity implements Collideable{
	
	private Sprite img;
	private Vector2 v;
	private int lifetime;
	protected Rectangle rect;
	
	private float damage;
	private Type ownerType;
	
	Projectile(Vector2 pos, Vector2 v, String imgName, int lifetime, float damage, Type ownerType){
		super(pos);
		img = new Sprite(TextureLoader.textures.get(imgName));
		img.setPosition(pos.x, pos.y);
		position = pos.cpy();
		this.v = v.cpy();
		this.lifetime = lifetime;
		this.damage = damage;
		this.ownerType = ownerType;
		
		rect = new Rectangle(position.x, position.y, img.getWidth(), img.getHeight());
	}

	@Override
	public void tick(float dt) {
		rect.setX(position.x);
		rect.setY(position.y);
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
		if(obj instanceof Player){
			live = false;
		}
		if(obj instanceof Enemy){
			live = false;
		}
	}

	@Override
	public Rectangle getRect() {
		return rect;
	}

	public Type getType() {
		return ownerType;
	}
	
	
}
