package com.molecule.system;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.molecule.system.states.PlayState;
import com.molecule.system.util.SoundLoader;
import com.molecule.system.util.TextureLoader;

public class Game extends ApplicationAdapter{
	
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	
	private GameStateManager gsm;
	private Renderer renderer;
	
	@Override
	public void create () {
		renderer = new Renderer(WIDTH, HEIGHT);
		new TextureLoader();
		new SoundLoader();
		gsm = new GameStateManager();
		gsm.push(new PlayState(gsm));
	}
	
	public void tick(float dt){
		gsm.tick(dt);
	}
	
	@Override
	public void render () {
		tick(Gdx.graphics.getDeltaTime()*10);
		renderer.render();
		gsm.render(renderer);
	}
	
	@Override
	public void resize(int width, int height) {
	    Camera.getCam().viewportWidth = WIDTH;                 // Viewport of 30 units!
	    Camera.getCam().viewportHeight = WIDTH * height/width; // Lets keep things in proportion.
	    Camera.getCam().update();
	}
	 
	@Override
	public void dispose(){
		renderer.dispose();
		EntityManager.clear();
	}
	
}
