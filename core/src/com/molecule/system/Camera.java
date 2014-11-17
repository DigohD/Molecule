package com.molecule.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class Camera {
	
	private static OrthographicCamera cam = new OrthographicCamera(Game.WIDTH, Game.WIDTH * (Gdx.graphics.getHeight() / Gdx.graphics.getWidth()));;
	private Vector2 diff = new Vector2(0,0);
	private Vector2 pos = new Vector2(0,0);
	private boolean dynamic;
	
	public Camera(boolean dynamic){
		this.dynamic = dynamic;
		
		
		if(dynamic)
			cam.position.set(EntityManager.getPlayer().getPosition().x, EntityManager.getPlayer().getPosition().y, 0);
		else
			cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
	    cam.update();
	}
	
	public void tick(float dt){
		if(dynamic){
			diff.x = EntityManager.getPlayer().getPosition().x - cam.position.x;
			diff.y = EntityManager.getPlayer().getPosition().y - cam.position.y;
			
			pos.x = cam.position.x + (diff.x / 15.0f); 
			pos.y = cam.position.y + (diff.y / 15.0f); 
		
			cam.position.set(pos.x, pos.y, 0);
		}
	}
	
	public static float getCamX(){
		return cam.position.x;
	}
	
	public static float getCamY(){
		return cam.position.y;
	}
	
	public static OrthographicCamera getCam() {
		return cam;
	}

}
