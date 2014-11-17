package com.molecule.system.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.molecule.system.Camera;
import com.molecule.system.EntityManager;
import com.molecule.system.Game;
import com.molecule.system.GameStateManager;
import com.molecule.system.Renderer;
import com.molecule.system.Util;
import com.molecule.system.gui.Button;
import com.molecule.system.util.SoundLoader;
import com.molecule.system.util.TextureLoader;

public class MenuState extends GameState implements InputProcessor{
	
	private Button play, options, exit;
	private Texture bg, bgalpha, shine, nucleus, particle, particleint;
	private Camera cam;
	private int timer = 0;
	private int nucleusCX, nucleusCY;
	private boolean soundPlayed = false;
	
	private boolean pClicked = false, eClicked = false, click = false;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init(){
		new EntityManager();

		cam = new Camera(false);
		
		soundPlayed = false;
		
		play = new Button("buttonplay", 100, 570);
		options = new Button("buttonoptions", 100, 330);
		exit = new Button("buttonquit", 100, 90);
	
		bg = TextureLoader.textures.get("menubg");
		bgalpha = TextureLoader.textures.get("menubgalpha");
		shine = TextureLoader.textures.get("shine");
		
		nucleus = TextureLoader.textures.get("menunucleus");
		particle = TextureLoader.textures.get("menuparticle");
		particleint = TextureLoader.textures.get("menuparticleint");
		
		SoundLoader.music.get("menum2").setLooping(true);
		SoundLoader.music.get("menum2").play();
	}

	@Override
	public void tick(float dt) {
		Gdx.input.setInputProcessor(this);
		Camera.getCam().position.set(Camera.getCam().viewportWidth / 2f, Camera.getCam().viewportHeight / 2f, 0);
		if(pClicked || timer > 0 && !click){
			click = true;
			
			if(!soundPlayed){
				SoundLoader.sounds.get("buttonclick").play();
				soundPlayed = true;
			}
			if(1 - ((float) timer / 18) < 0)
				SoundLoader.music.get("menum2").setVolume(0);
			else
				SoundLoader.music.get("menum2").setVolume(1 - ((float) timer / 18));
			
			timer++;
			if(timer >= 30){
				timer = 0;
				pClicked = false;
				click = false;
				SoundLoader.music.get("menum2").stop();
				SoundLoader.music.get("menum2").dispose();

				gsm.push(new PlayerSetupState(gsm));
			}
		}
		
		if(eClicked || timer > 0 && !click){
			click = true;
			
			if(!soundPlayed){
				SoundLoader.sounds.get("buttonclick").play();
				soundPlayed = true;
			}
			if(1 - ((float) timer / 18) < 0)
				SoundLoader.music.get("menum2").setVolume(0);
			else
				SoundLoader.music.get("menum2").setVolume(1 - ((float) timer / 18));
			
			timer++;
			if(timer >= 30){
				timer = 0;
				eClicked = false;
				click = false;
				SoundLoader.music.get("menum2").stop();
				SoundLoader.music.get("menum2").dispose();
				Gdx.app.exit();
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
		
		renderer.getBatch().draw(bg, 0, 0);
		renderer.getBatch().draw(shine, -1000 + shineTimer, 0);
		renderer.getBatch().draw(bgalpha, 0, 0);
		
		shineTimer += 12;
		if(shineTimer > 3000)
			shineTimer = 0;
		
		renderer.getBatch().draw(nucleus, 1100, -300);
		nucleusCX = 1100 + 498;
		nucleusCY = -300 + 498;
		
		tickIntParticle();
		renderer.getBatch().draw(particleint, iPX, iPY);
		tickExtParticle();
		renderer.getBatch().draw(particle, pX, pY);
		
		
		
		play.render(renderer.getBatch());
		if(pClicked) renderer.getBatch().draw(play.getClickedSprite(), play.getX(), play.getY());
		
		options.render(renderer.getBatch());
		
		exit.render(renderer.getBatch());
		if(eClicked) renderer.getBatch().draw(exit.getClickedSprite(), exit.getX(), exit.getY());
		
		renderer.getBatch().end();
		Renderer.getBasicShader().end();
	}
	
	float sineTime, sineTime2, ellipseAngle, ellipseAngle2, pX, pY, iPX, iPY;
	
	private void tickExtParticle(){
		
		sineTime += 0.02f;
		
		ellipseAngle = ellipseAngle + 0.0025f;
		
		float sineOffsetX = (float) ((800 * Math.cos(sineTime) * Math.cos(ellipseAngle + 4.18f)) - 
				(175 * Math.sin(sineTime) * Math.sin(ellipseAngle + 4.18f)));
		float sineOffsetY = (float) ((800 * Math.cos(sineTime) * Math.sin(ellipseAngle + 4.18f)) + 
				(175 * Math.sin(sineTime) * Math.cos(ellipseAngle + 4.18f)));
		
		float pCX, pCY;
		
		pCX = 1100 + 498 + sineOffsetX;
		pCY = -300 + 498 + sineOffsetY;
		
		pX = pCX - 157;
		pY = pCY - 157;
	}
	
	private void tickIntParticle(){
		sineTime2 += 0.066f;
		
		ellipseAngle2 = ellipseAngle2 + 0.007f;
		
		float sineOffsetX = (float) ((280 * Math.cos(sineTime2) * Math.cos(ellipseAngle2 + 1f)) - 
				(55 * Math.sin(sineTime2) * Math.sin(ellipseAngle2 + 1f)));
		float sineOffsetY = (float) ((280 * Math.cos(sineTime2) * Math.sin(ellipseAngle2 + 1f)) + 
				(55 * Math.sin(sineTime2) * Math.cos(ellipseAngle2 + 1f)));
		
		float pCX, pCY;
		
		pCX = 1100 + 498 + sineOffsetX;
		pCY = -300 + 498 + sineOffsetY;
		
		iPX = pCX - 89;
		iPY = pCY - 89;
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

	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK){
			Gdx.app.exit();
		}
		return true;
	}

	public boolean mouseMoved(int screenX, int screenY) {return false;}
	public boolean scrolled(int amount) {return false;}
	public boolean keyUp(int keycode) {return false;}
	public boolean keyTyped(char character) {return false;}

}
