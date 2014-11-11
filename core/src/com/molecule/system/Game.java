package com.molecule.system;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.molecule.entity.molecule.Nucleus;
import com.molecule.entity.player.Player;

public class Game extends ApplicationAdapter{
	
	private SpriteBatch batch;
	private Player player;
	
	private Texture img;
	
	@Override
	public void create () {
		new TextureLoader();
		batch = new SpriteBatch();
		player = new Player();
		Gdx.input.setInputProcessor(player);
		
		img = TextureLoader.textures.get("bg");
	}

	float r;
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		

		
		batch.begin();
		
		Sprite bgS = new Sprite(img);
		r = r + 0.01f;
		bgS.scale(4);
		bgS.rotate(r);
		bgS.setColor(0, 1, 1, 1);
		
		bgS.draw(batch);

		player.draw(batch);
		batch.end();
	}

	
}
