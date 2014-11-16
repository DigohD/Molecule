package com.molecule.system.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.molecule.system.Camera;
import com.molecule.system.EntityManager;
import com.molecule.system.Game;
import com.molecule.system.GameStateManager;
import com.molecule.system.Renderer;
import com.molecule.system.gui.Button;

public class PauseState extends GameState implements InputProcessor{

	private Button play, exit;
	private Camera cam;
	private int timer = 0;
	
	private boolean pClicked = false, eClicked = false, click = false;

	public PauseState(GameStateManager gsm) {
		super(gsm);
		this.blockTicking = true;
		this.blockRendering = true;
		init();
	}

	@Override
	public void init() {
		cam = new Camera(false);
		
		play = new Button("playbutton", Game.WIDTH / 2 - (587/2), Game.HEIGHT / 2 + 100);
		exit = new Button("exitbutton", Game.WIDTH / 2 - (587/2), (Game.HEIGHT / 2) - 180 + 100);
		
	}

	@Override
	public void tick(float dt) {
		Gdx.input.setInputProcessor(this);
		Camera.getCam().position.set(Camera.getCam().viewportWidth / 2f, Camera.getCam().viewportHeight / 2f, 0);
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

	@Override
	public void render(Renderer renderer) {
		Renderer.enableShader(Renderer.getBasicShader());
		Renderer.getBasicShader().begin();
		Renderer.getBasicShader().setUniformMatrix("u_projTrans", renderer.getBatch().getProjectionMatrix());
		renderer.getBatch().begin();
		renderer.getBatch().setProjectionMatrix(Camera.getCam().combined);
		Camera.getCam().update();   
		
		play.render(renderer.getBatch());
		if(pClicked) renderer.getBatch().draw(play.getClickedSprite(), play.getX(), play.getY());
		exit.render(renderer.getBatch());
		if(eClicked) renderer.getBatch().draw(exit.getClickedSprite(), exit.getX(), exit.getY());
		
		renderer.getBatch().end();
		Renderer.getBasicShader().end();
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
