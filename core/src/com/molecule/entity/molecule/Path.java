package com.molecule.entity.molecule;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.molecule.system.util.TextureLoader;

public class Path {
	
	private static Texture img = TextureLoader.textures.get("path");
	private int lifeTime = 30;
	private float x, y;
	
	public Path(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(SpriteBatch batch){
		lifeTime--;
		
		Sprite sprite = new Sprite(img);
		float alpha = ((float)lifeTime * 3.00f) / 100.00f;
		
		if(lifeTime > 0){
			batch.setColor(1.0f, 1.0f, 1.0f, alpha);
			batch.draw(sprite, x, y);
			batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		}
	}
}
