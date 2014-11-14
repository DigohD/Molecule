package com.molecule.system.util;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.enemy.Enemy;
import com.molecule.system.CollisionManager;

public class PlayerLogic {
	
	private static Vector2 enemyDir = new Vector2();
	private static Vector2 pointRef = new Vector2();
	
	public static Enemy findNearestEnemy(Vector2 point){
		ArrayList<Enemy> enemies = (ArrayList<Enemy>) CollisionManager.getEnemies();
		Enemy toReturn = null;
		
		float distance = 10000000;
		for(Enemy e : enemies){
			pointRef.x = point.x;
			pointRef.y = point.y;
			float newDistance = pointRef.sub(e.getPosition()).len();
			if(newDistance < distance){
				distance = newDistance;
				toReturn = e;
			}
		}
		
		return toReturn;
	}
	
	public static Vector2 getEnemyDir(Vector2 pos, Enemy enemy){
		float enemyX = 0;
		float enemyY = 0;
		
		if(enemy != null){
			enemyX = enemy.getNucleus().getCenterX();
			enemyY = enemy.getNucleus().getCenterY();
		}
		
		
		float dirX = enemyX - pos.x;
		float dirY = enemyY - pos.y;
		
		enemyDir.set(dirX, dirY);
		
		return enemyDir;
	}
	
}