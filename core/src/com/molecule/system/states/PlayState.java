package com.molecule.system.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.molecule.entity.enemy.Enemy;
import com.molecule.system.Camera;
import com.molecule.system.EntityManager;
import com.molecule.system.GameStateManager;
import com.molecule.system.Renderer;
import com.molecule.system.gui.JoyStick;
import com.molecule.system.util.GranuleBuffer;
import com.molecule.system.util.SoundLoader;
import com.molecule.system.util.TextureLoader;

public class PlayState extends GameState{
	
	private JoyStick joyStick;
	private Camera camera;
	private Sprite bgS;

	private float amplitudeWave = 8f;
	private float angleWave = 2.86f;
	private float angleWaveSpeed = 0.2f;
	private static final float PI2 = 3.1415926535897932384626433832795f * 2.0f;
	private float r;
	
	private int timer = 0;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		Gdx.input.setCatchBackKey(true);
		new GranuleBuffer();
		
		bgS = new Sprite(TextureLoader.textures.get("bg"));
		bgS.scale(4);
		bgS.setScale(10);
		camera = new Camera(true);
		joyStick = new JoyStick(gsm);
		
		new SoundLoader();
		
		Music m = Gdx.audio.newMusic(Gdx.files.internal("sound/gamem1.mp3"));
		m.setLooping(true);
		m.play();
		
		for(int i = 0; i < 6; i++){
			new Enemy();
		}
		
	}

	@Override
	public void tick(float dt) {
		Gdx.input.setInputProcessor(joyStick);
		
		if(!EntityManager.getPlayer().isLive()){
			timer++;
			if(timer >= 60*3){
				timer = 0;
				EntityManager.clear();
				gsm.pop();
			}
		}
		
		EntityManager.tick(dt);

		camera.tick(dt);
		
		angleWave += dt * angleWaveSpeed;
		while(angleWave > PI2)
			angleWave -= PI2;
	}

	@Override
	public void render(Renderer renderer) {
//		Renderer.enableShader(Renderer.getShader());
//		Renderer.getShader().begin();
//		
//		Renderer.getShader().setUniformMatrix("u_projTrans", renderer.getBatch().getProjectionMatrix());
//		Renderer.getShader().setUniformf("waveDataX", angleWave);
//		Renderer.getShader().setUniformf("waveDataY", amplitudeWave);
		
		renderer.getBatch().begin();
		renderer.getBatch().setProjectionMatrix(Camera.getCam().combined);
		
		bgS.rotate(0.01f);
		bgS.setColor(0, 1, 1, 1);
		bgS.draw(renderer.getBatch());
		
		Camera.getCam().update();    
		EntityManager.render(renderer.getBatch());
		joyStick.render(renderer.getBatch());
		
		renderer.getBatch().end();
//		Renderer.getShader().end();
	}

}
