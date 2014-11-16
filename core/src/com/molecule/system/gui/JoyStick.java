package com.molecule.system.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.Renderable;
import com.molecule.system.Camera;
import com.molecule.system.EntityManager;
import com.molecule.system.Game;
import com.molecule.system.GameStateManager;
import com.molecule.system.states.PauseState;
import com.molecule.system.util.TextureLoader;

public class JoyStick implements InputProcessor, Renderable{
	
	private Vector2 position;
	private Texture texture;
	
	private GameStateManager gsm;
	
	public JoyStick(GameStateManager gsm){
		this.gsm = gsm;
		position = new Vector2(20, 20);
		texture = TextureLoader.textures.get("joystick");
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(texture, position.x + (Camera.getCamX() - Game.WIDTH/2) , position.y + (Camera.getCamY() - Game.HEIGHT/2));
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		float x = (float)screenX * ((float)Game.WIDTH / Gdx.graphics.getWidth());
		float y = (Gdx.graphics.getHeight() - screenY) * ((float)Game.HEIGHT / Gdx.graphics.getHeight());
		
		if (x <= 340 && x >= 0 && y <= 340 && y >= 0) {
			
			float dX = x - 170;
			float dY = y - 170;
			dX = dX / 8;
			dY = dY / 8;
			EntityManager.getPlayer().setTargetVel(dX, dY);
		}else{
			EntityManager.getPlayer().setUpdate(false);
		}
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		EntityManager.getPlayer().setUpdate(false);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		float x = (float)screenX * ((float)Game.WIDTH / Gdx.graphics.getWidth());
		float y = (Gdx.graphics.getHeight() - screenY) * ((float)Game.HEIGHT / Gdx.graphics.getHeight());
		
		if (x <= 340 && x >= 0 && y <= 340 && y >= 0) {
			
			float dX = x - 170;
			float dY = y - 170;
			dX = dX / 8;
			dY = dY / 8;
			EntityManager.getPlayer().setTargetVel(dX, dY);
		}else{
			EntityManager.getPlayer().setUpdate(false);
		}
		
		return true;
	}
	
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK){
			//gsm.pop();
			//EntityManager.clear();
			gsm.push(new PauseState(gsm));
		}
		return true;
	}

	public boolean mouseMoved(int screenX, int screenY) {return false;}
	public boolean scrolled(int amount) {return false;}
	
	public boolean keyUp(int keycode) {return false;}
	public boolean keyTyped(char character) {return false;}

}
