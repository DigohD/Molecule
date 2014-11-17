package com.molecule.system.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.molecule.system.Camera;
import com.molecule.system.EntityManager;
import com.molecule.system.Game;
import com.molecule.system.GameStateManager;
import com.molecule.system.Renderer;
import com.molecule.system.gui.Button;
import com.molecule.system.util.TextureLoader;

public class PauseState extends GameState implements InputProcessor{

	private Button play, exit;
	private Camera cam;
	private int timer = 0;
	
	private Texture bg, bgalpha, shine;
	private boolean pClicked = false, eClicked = false, click = false;

	public PauseState(GameStateManager gsm) {
		super(gsm);
		this.blockTicking = true;
		this.blockRendering = false;
		init();
	}

	@Override
	public void init() {
		cam = new Camera(false);
		
		play = new Button("buttonplay", 100, 570);
		exit = new Button("buttonquit", 100, 330);
	
		bg = TextureLoader.textures.get("menubg");
		bgalpha = TextureLoader.textures.get("menubgalpha");
		shine = TextureLoader.textures.get("shine");
	}

	@Override
	public void tick(float dt) {
		Gdx.input.setInputProcessor(this);
		Camera.getCam().position.set(Camera.getCamX(), Camera.getCamY(), 0);
		if(pClicked || timer > 0 && !click){
			click = true;
			timer++;
			if(timer >= 30){
				timer = 0;
				pClicked = false;
				click = false;
				gsm.pop();
			}
		}
		
		if(eClicked || timer > 0 && !click){
			click = true;
			timer++;
			if(timer >= 30){
				timer = 0;
				pClicked = false;
				click = false;
				EntityManager.clear();
				gsm.pop();
				gsm.pop();
			}
		}
	}

	int shineTimer;
	
	@Override
	public void render(Renderer renderer) {
//		Renderer.enableShader(Renderer.getBasicShader());
//		Renderer.getBasicShader().begin();
//		Renderer.getBasicShader().setUniformMatrix("u_projTrans", renderer.getBatch().getProjectionMatrix());
		renderer.getBatch().begin();
		renderer.getBatch().setProjectionMatrix(Camera.getCam().combined);
		Camera.getCam().update();   
		
//		renderer.getBatch().draw(bg, 0, 0);
//		renderer.getBatch().draw(shine, -1000 + shineTimer, 0);
//		renderer.getBatch().draw(bgalpha, 0, 0);
//		
//		shineTimer += 12;
//		if(shineTimer > 3000)
//			shineTimer = 0;
		
		play.render(renderer.getBatch());
		if(pClicked) renderer.getBatch().draw(play.getClickedSprite(), play.getX(), play.getY());
		exit.render(renderer.getBatch());
		if(eClicked) renderer.getBatch().draw(exit.getClickedSprite(), exit.getX(), exit.getY());
		
		renderer.getBatch().end();
//		Renderer.getBasicShader().end();
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		float x = (float)screenX * ((float)Game.WIDTH / Gdx.graphics.getWidth());
		float y = (Gdx.graphics.getHeight() - screenY) * ((float)Game.HEIGHT / Gdx.graphics.getHeight());
		
		if(play.getRect().contains(x, y))
			pClicked = true;
		if(exit.getRect().contains(x, y))
			eClicked = true;
		
		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		return false;
	}


	public boolean mouseMoved(int screenX, int screenY) {return false;}
	public boolean scrolled(int amount) {return false;}
	public boolean keyDown(int keycode) {return false;}
	public boolean keyUp(int keycode) {return false;}
	public boolean keyTyped(char character) {return false;}

}
