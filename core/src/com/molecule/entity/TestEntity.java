package com.molecule.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.molecule.system.EntityManager;
import com.molecule.system.util.TextureLoader;

public class TestEntity extends Mob implements InputProcessor{
	
	private Vector2 target;
	boolean update = false;

	public TestEntity(Vector2 position) {
		super(position);
		img = TextureLoader.textures.get("core");
		velocity = new Vector2(5,0);
		target = new Vector2(0,0);
		Gdx.input.setInputProcessor(this);
		EntityManager.addEntity(this);
	}

	@Override
	public void tick(float dt) {
		
		
		
		
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
		target.x = screenX;
		target.y = Gdx.graphics.getHeight() - screenY;
		update = true;
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		update = false;
		return true;
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
