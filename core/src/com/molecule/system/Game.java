package com.molecule.system;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.enemy.Enemy;
import com.molecule.system.util.GranuleBuffer;
import com.molecule.system.util.TextureLoader;

public class Game extends ApplicationAdapter{
	
	private SpriteBatch batch;
	private EntityManager eManager;
	private JoyStick joyStick;
	private ShaderProgram shader;
	
	private Texture img;
	private static OrthographicCamera cam;
	
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	
	private float amplitudeWave = 8f;
	private float angleWave = 2.86f;
	private float angleWaveSpeed = 0.2f;
	private static final float PI2 = 3.1415926535897932384626433832795f * 2.0f;
	
	private Sprite bgS;
	

	public float scaleX, scaleY;
	
	@Override
	public void create () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		
		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(Gdx.files.internal("shaders/vertShader.vert").readString(), 
				Gdx.files.internal("shaders/fragShader.frag").readString());
		
		if(!shader.isCompiled())
			 Gdx.app.log("Problem loading shader:", shader.getLog());
		
		batch = new SpriteBatch();
		batch.setShader(shader);
		
		
		new TextureLoader();
		new GranuleBuffer();
	
		img = TextureLoader.textures.get("bg");
		bgS = new Sprite(img);
		bgS.scale(4);
		eManager = new EntityManager();
		bgS = new Sprite(img);
		
		for(int i = 0; i < 6; i++){
			new Enemy();
		}
		
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
		
		angleWave += dt * angleWaveSpeed;
		while(angleWave > PI2)
			angleWave -= PI2;
	}

	float r;
	
	@Override
	public void render () {
		tick(Gdx.graphics.getDeltaTime()*10);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
//		shader.begin();
//		shader.setUniformMatrix("transform", batch.getProjectionMatrix());
	
		bgS.setScale(10);

		shader.begin();
		shader.setUniformMatrix("u_projTrans", batch.getProjectionMatrix());
		shader.setUniformf("waveDataX", angleWave);
		shader.setUniformf("waveDataY", amplitudeWave);
		
		batch.begin();
		
		batch.setProjectionMatrix(cam.combined);
		
		bgS.rotate(0.01f);
		bgS.setColor(0, 1, 1, 1);
		bgS.draw(batch);

		cam.update();    

		eManager.render(batch);
		joyStick.render(batch);
		
		batch.end();
		shader.end();
	}

	@Override
	public void resize(int width, int height) {
	    cam.viewportWidth = WIDTH;                 // Viewport of 30 units!
	    cam.viewportHeight = WIDTH* height/width; // Lets keep things in proportion.
	    cam.update();
	}
	 
	@Override
	public void dispose(){
		batch.dispose();
		EntityManager.clear();
	}

	public static OrthographicCamera getCam() {
		return cam;
	}
	
	
	
}
