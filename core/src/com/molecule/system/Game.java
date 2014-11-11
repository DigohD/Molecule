package com.molecule.system;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.player.Player;
import com.molecule.system.util.TextureLoader;

public class Game extends ApplicationAdapter{
	
	private SpriteBatch batch;
	private EntityManager eManager;
	private JoyStick joyStick;
	
	private OrthographicCamera cam;
	
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	
	public float scaleX, scaleY;
	
	@Override
	public void create () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		new TextureLoader();
		batch = new SpriteBatch();
		eManager = new EntityManager();
		
		joyStick = new JoyStick();
		
		Gdx.input.setInputProcessor(joyStick);
		
		float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        
        scaleX = w / (float)WIDTH;
        scaleY = h / (float)HEIGHT;
        
        cam = new OrthographicCamera(WIDTH, WIDTH * (h / w));
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
	}
	
	public void tick(float dt){
		eManager.tick(dt);
	}

	@Override
	public void render () {
		tick(Gdx.graphics.getDeltaTime()*10);
		
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		cam.update();                                                         
		batch.setProjectionMatrix(cam.combined);
		
		batch.begin();
		joyStick.render(batch);
		eManager.render(batch);
		batch.end();
	}

	 @Override
	 public void resize(int width, int height) {
	      cam.viewportWidth = WIDTH;                 // Viewport of 30 units!
	      cam.viewportHeight = WIDTH* height/width; // Lets keep things in proportion.
	      cam.update();
	 }
	
}
