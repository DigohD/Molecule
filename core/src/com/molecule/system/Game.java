package com.molecule.system;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.molecule.entity.molecule.Nucleus;
import com.molecule.entity.player.Player;

public class Game extends ApplicationAdapter{
	
	private SpriteBatch batch;
	private Player player;
	
	@Override
	public void create () {
		new TextureLoader();
		batch = new SpriteBatch();
		player = new Player();
		Gdx.input.setInputProcessor(player);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		player.draw(batch);
		batch.end();
	}

	
}
