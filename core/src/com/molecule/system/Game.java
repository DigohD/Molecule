package com.molecule.system;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.enemy.Enemy;
import com.molecule.entity.particle.Particle;
import com.molecule.entity.player.Player;
import com.molecule.system.util.GranuleBuffer;
import com.molecule.system.util.TextureLoader;

public class Game extends ApplicationAdapter{
	
	private SpriteBatch batch;
	private EntityManager eManager;
	private JoyStick joyStick;
	
	private Texture img;
	private static OrthographicCamera cam;
	
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	public float scaleX, scaleY;
	
	@Override
	public void create () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		new TextureLoader();
		new GranuleBuffer();
		batch = new SpriteBatch();
		img = TextureLoader.textures.get("bg");
		eManager = new EntityManager();
		
		enemies.add(new Enemy());
		enemies.add(new Enemy());
		enemies.add(new Enemy());
		enemies.add(new Enemy());
		enemies.add(new Enemy());
		
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
	
	Vector2 diff = new Vector2(0,0);
	Vector2 pos = new Vector2(0,0);
	
	public void tick(float dt){
		eManager.tick(dt);
		
		diff.x = EntityManager.getPlayer().getPosition().x - cam.position.x;
		diff.y = EntityManager.getPlayer().getPosition().y - cam.position.y;
		
		pos.x = cam.position.x + (diff.x / 15.0f); 
		pos.y = cam.position.y + (diff.y / 15.0f); 
	
		cam.position.set(pos.x, pos.y, 0);
	}

	float r;
	Sprite bgS = new Sprite(img);
	
	@Override
	public void render () {
		tick(Gdx.graphics.getDeltaTime()*10);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		r = r + 0.01f;
		bgS.scale(4);
		bgS.rotate(r);
		bgS.setColor(0, 1, 1, 1);
		
		bgS.draw(batch);

		cam.update();    
		
		batch.setProjectionMatrix(cam.combined);

		for(Enemy e : enemies)
			e.draw(batch);

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
	 
	@Override
	public void dispose(){
		EntityManager.clear();
	}

	public static OrthographicCamera getCam() {
		return cam;
	}
	
	
	
}
