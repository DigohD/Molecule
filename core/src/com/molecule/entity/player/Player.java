package com.molecule.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.molecule.entity.molecule.Nucleus;

public class Player implements InputProcessor{

	float targetX, targetY;
	private Nucleus nucleus;
	
	public Player(){
		nucleus = new Nucleus();
	}
	
	public void draw(SpriteBatch batch){
		nucleus.draw(batch);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		targetX = screenX;
		targetY = Gdx.graphics.getHeight() - screenY;
		
		nucleus.setTargetX(targetX);
		nucleus.setTargetY(targetY);
		
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

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
}
