package com.molecule.system;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.molecule.entity.enemy.Enemy;
import com.molecule.entity.particle.offensive.Projectile;
import com.molecule.entity.player.Player;

public class CollisionManager {
	
	private static List<Enemy> enemies = new ArrayList<Enemy>();
	private static List<Projectile> playerProjectiles = new ArrayList<Projectile>();
	private static List<Projectile> enemyProjectiles = new ArrayList<Projectile>();
	
	public static void addEnemy(Enemy e) {
		enemies.add(e);
	}
	
	public static void addEnemyProjectile(Projectile proj) {
		enemyProjectiles.add(proj);
	}
	
	public static void addPlayerProjectile(Projectile proj) {
		playerProjectiles.add(proj);
	}
	
	public static void removeEnemy(Enemy e) {
		enemies.remove(e);
	}
	
	public static void removeEnemyProjectile(Projectile proj) {
		enemyProjectiles.remove(proj);
	}
	
	public static void removePlayerProjectile(Projectile proj) {
		playerProjectiles.remove(proj);
	}
	
	public static void clear() {
		enemies.clear();
		playerProjectiles.clear();
		enemyProjectiles.clear();
	}
	
	private static boolean collisionBetween(Rectangle r1, Rectangle r2) {
		if (r1.overlaps(r2) || r1.contains(r2))
			return true;
		return false;
	}
	
	public static void collisionCheck(Player player) {
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			if (player != null && e != null && player.isLive())
				if (collisionBetween(player.getRect(), e.getRect())) {
					e.collisionWith(player);
					player.collisionWith(e);
				}
		}
		
		for (int i = 0; i < playerProjectiles.size(); i++)
			for (int j = 0; j < enemies.size(); j++) {
				Projectile p = playerProjectiles.get(i);
				Enemy e = enemies.get(j);
				if (p != null && e != null)
					if (collisionBetween(p.getRect(), e.getRect())) {
						e.collisionWith(p);
						p.collisionWith(e);
					}
			}
		
		for (int i = 0; i < enemyProjectiles.size(); i++) {
			Projectile p = enemyProjectiles.get(i);
			if (p != null && player != null && player.isLive())
				if (collisionBetween(p.getRect(), player.getRect())) {
					player.collisionWith(p);
					p.collisionWith(player);
				}
		}
	}
	
	public static List<Enemy> getEnemies() {
		return enemies;
	}
	
	public static List<Projectile> getEnemyProjectiles() {
		return enemyProjectiles;
	}
	
	public static List<Projectile> getPlayerProjectiles() {
		return playerProjectiles;
	}

}
