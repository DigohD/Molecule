package com.molecule.entity;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	
	protected Vector2 position;
	protected boolean live = true;
	protected int width, height;
	
	public Entity(){
		position = new Vector2(0,0);
	}
	
	public Entity(Vector2 position){
		this.position = position;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public void setPosition(float x, float y){
		position.x = x;
		position.y = y;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
