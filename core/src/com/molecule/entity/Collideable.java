package com.molecule.entity;

import com.badlogic.gdx.math.Rectangle;

public interface Collideable {
	
	public void collisionWith(Collideable obj);
	public Rectangle getRect();

}
