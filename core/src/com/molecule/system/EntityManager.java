package com.molecule.system;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.Entity;
import com.molecule.entity.Renderable;
import com.molecule.entity.Tickable;
import com.molecule.entity.player.Player;

public class EntityManager {
	
	private static List<Tickable> tickables = new ArrayList<Tickable>();
	private static List<Tickable> tToAdd = new ArrayList<Tickable>();
	private static List<Renderable> renderables = new ArrayList<Renderable>();
	private static List<Renderable> rToAdd = new ArrayList<Renderable>();
	
	private static Player player;
	
	public EntityManager(){
		player = new Player(new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2));
	}
	
	public static void addEntity(Entity e){
		if(e instanceof Tickable)
			tToAdd.add((Tickable) e);
		if(e instanceof Renderable)
			rToAdd.add((Renderable) e);
	}
	
	public static void removeEntity(Entity e){
		if(e instanceof Tickable)
			tickables.remove((Tickable) e);
		if(e instanceof Renderable)
			renderables.remove((Renderable) e);
	}
	
	public static void clear(){
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
			if(!e.isLive())
				removeEntity(e);
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
		
		for(Tickable t : tickables)
			t.tick(dt);
	}
	
	public void render(SpriteBatch batch){
		for(Renderable r : renderables)
			r.render(batch);
	}

	public static Player getPlayer() {
		return player;
	}
	
	

}
