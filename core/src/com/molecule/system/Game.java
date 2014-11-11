package com.molecule.system;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.molecule.system.util.TextureLoader;

public class Game extends ApplicationAdapter{
	
	private SpriteBatch batch;
	private EntityManager eManager;
	private JoyStick joyStick;
	
	private Texture img;
	
	@Override
	public void create () {
		new TextureLoader();
		batch = new SpriteBatch();
		img = TextureLoader.textures.get("bg");
		eManager = new EntityManager();
		joyStick = new JoyStick();
	}
	
	public void tick(float dt){
		eManager.tick(dt);
	}

	float r;
	
	@Override
	public void render () {
		tick(Gdx.graphics.getDeltaTime()*10);
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		Sprite bgS = new Sprite(img);
		r = r + 0.01f;
		bgS.scale(4);
		bgS.rotate(r);
		bgS.setColor(0, 1, 1, 1);
		
		bgS.draw(batch);
		
		batch.begin();
		joyStick.render(batch);
		//eManager.render(batch);
		batch.end();
	}

	
}
