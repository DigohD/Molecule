package com.molecule.system.util;

import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.molecule.Nucleus;

public class EnemyLogic {

	private static Nucleus player;
	
	public EnemyLogic(Nucleus player){
		this.player = player;
	}
	
	public static Vector2 getPlayerDir(Vector2 pos){
		Vector2 playerPos = new Vector2(player.getCenterX(), player.getCenterY());
		return playerPos.sub(pos);
	}
	
}
