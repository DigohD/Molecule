package com.molecule.system.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.molecule.entity.Renderable;
import com.molecule.system.Game;
import com.molecule.system.util.TextureLoader;

public class Button implements Renderable{

	private float x,y;
	private Sprite sprite, clickedSprite;
	private Rectangle rect;
	private boolean clicked = false;
	
	public Button(String name, float x, float y){
		this.x = x;
		this.y = y;
		sprite = new Sprite(TextureLoader.textures.get(name));
		clickedSprite = new Sprite(TextureLoader.textures.get("buttonClicked"));
		
		rect = new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
		
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(sprite, x, y);
//		if(clicked){
//			batch.draw(clickedSprite, x, y);
//		}
	}

	public Rectangle getRect() {
		return rect;
	}

	public Sprite getClickedSprite() {
		return clickedSprite;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}


}
