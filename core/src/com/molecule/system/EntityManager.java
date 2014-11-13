package com.molecule.system;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.Entity;
import com.molecule.entity.Renderable;
import com.molecule.entity.Tickable;
import com.molecule.entity.enemy.Enemy;
import com.molecule.entity.granule.Granule;
import com.molecule.entity.molecule.Nucleus.Type;
import com.molecule.entity.particle.Particle;
import com.molecule.entity.particle.offensive.Projectile;
import com.molecule.entity.particle.offensive.QuarkGun;
import com.molecule.entity.player.Player;
import com.molecule.system.util.EnemyLogic;
import com.molecule.system.util.GranuleBuffer;

public class EntityManager {
	
	private static List<Tickable> tickables = new ArrayList<Tickable>();
	private static List<Tickable> tToAdd = new ArrayList<Tickable>();
	private static List<Renderable> renderables = new ArrayList<Renderable>();
	private static List<Renderable> rToAdd = new ArrayList<Renderable>();
	
	private static Player player;
	
	public EntityManager(){
		player = new Player(new Vector2(100, 100));
		
		Particle p = new Particle(player.getNucleus());
		p.addParticleMod(new QuarkGun(p));
		player.getNucleus().addParticle(p);
		
		new EnemyLogic(player.getNucleus());
	}
	
	public static void addEntity(Entity e){
		if(e instanceof Tickable)
			tToAdd.add((Tickable) e);
		if(e instanceof Renderable)
			rToAdd.add((Renderable) e);
		if(e instanceof Enemy)
			CollisionManager.addEnemy((Enemy)e);
		if(e instanceof Projectile){
			Projectile p = (Projectile) e;
			if (p.getType() == Type.ENEMY)
				CollisionManager.addEnemyProjectile(p);
			if (p.getType() == Type.PLAYER)
				CollisionManager.addPlayerProjectile(p);
		}
	}
	
	public static void removeEntity(Entity e){
		if(e instanceof Tickable)
			tickables.remove((Tickable) e);
		if(e instanceof Renderable)
			renderables.remove((Renderable) e);
		if(e instanceof Enemy)
			CollisionManager.removeEnemy((Enemy)e);
		if(e instanceof Projectile){
			Projectile p = (Projectile) e;
			if (p.getType() == Type.ENEMY)
				CollisionManager.removeEnemyProjectile(p);
			if (p.getType() == Type.PLAYER)
				CollisionManager.removePlayerProjectile(p);
		}
	}
	
	public static void clear(){
		CollisionManager.clear();
		tickables.clear();
		tToAdd.clear();
		renderables.clear();
		rToAdd.clear();
	}
	
	public void removeDeadEntities(){
		for(int i = 0; i < tickables.size(); i++){
			Tickable t = tickables.get(i);
			Entity e = null;
			if(t instanceof Entity)
				e = (Entity) t;
			if(!e.isLive()){
				if(e instanceof Granule){
					GranuleBuffer.putGranule((Granule) e);
				}
				removeEntity(e);
			}
		}
	}

	public void tick(float dt){
		
		for(Tickable t : tToAdd)
			tickables.add(t);
		for(Renderable r : rToAdd)
			renderables.add(r);
		tToAdd.clear();
		rToAdd.clear();
		
		removeDeadEntities();
		
		CollisionManager.collisionCheck(player);
		
		if(player.isLive())
			player.tick(dt);
		
		for(Tickable t : tickables)
			t.tick(dt);
	}
	
	public void render(SpriteBatch batch){
		if(player.isLive())
			player.render(batch);
		for(Renderable r : renderables){
			r.render(batch);
		}
	}

	public static Player getPlayer() {
		return player;
	}
	
	

}
