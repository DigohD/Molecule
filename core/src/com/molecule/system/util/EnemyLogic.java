package com.molecule.system.util;

import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.molecule.Nucleus;

public class EnemyLogic {

	private static Nucleus player;
	private static Vector2 playerDir;
	
	public EnemyLogic(Nucleus player){
		EnemyLogic.player = player;
		playerDir = new Vector2(0, 0);
	}
	
	public static Vector2 getPlayerDir(Vector2 pos){
		float playerX = player.getCenterX();
		float playerY = player.getCenterY();
		
		float dirX = playerX - pos.x;
		float dirY = playerY - pos.y;
		
		playerDir.set(dirX, dirY);
		
		return playerDir;
	}
	
}
