package com.molecule.entity.enemy;

import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.Collideable;
import com.molecule.entity.Mob;

public abstract class Enemy extends Mob implements Collideable{

	public Enemy(Vector2 position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

}
