package com.molecule.entity.particle.offensive;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.Entity;
import com.molecule.entity.Renderable;
import com.molecule.entity.Tickable;
import com.molecule.system.util.TextureLoader;

public class Projectile  extends Entity implements Tickable, Renderable{

	private Sprite img;
	private Vector2 v;
	private int lifetime;
	
	Projectile(Vector2 pos, Vector2 v, String imgName, int lifetime){
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
	
	
}
