package com.molecule.system.util;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureLoader{

	public static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	
	public TextureLoader(){
		//gui
		textures.put("playbutton", new Texture(Gdx.files.internal("textures/gui/playbutton.png")));
		textures.put("exitbutton", new Texture(Gdx.files.internal("textures/gui/exitbutton.png")));
		textures.put("buttonClicked", new Texture(Gdx.files.internal("textures/gui/buttonClicked.png")));
		textures.put("joystick", new Texture(Gdx.files.internal("textures/gui/joystick.png")));
		
		//start menu
		textures.put("buttonplay", new Texture(Gdx.files.internal("textures/gui/start/buttonplay.png")));
		textures.put("buttonoptions", new Texture(Gdx.files.internal("textures/gui/start/buttonoptions.png")));
		textures.put("buttonquit", new Texture(Gdx.files.internal("textures/gui/start/buttonquit.png")));
		
		textures.put("menubg", new Texture(Gdx.files.internal("textures/gui/start/menubg.png")));
		textures.put("menubgalpha", new Texture(Gdx.files.internal("textures/gui/start/menubgalpha.png")));
		textures.put("shine", new Texture(Gdx.files.internal("textures/gui/start/shine.png")));
		
		textures.put("menunucleus", new Texture(Gdx.files.internal("textures/gui/start/menunucleus.png")));
		textures.put("menuparticle", new Texture(Gdx.files.internal("textures/gui/start/menuparticle.png")));
		textures.put("menuparticleint", new Texture(Gdx.files.internal("textures/gui/start/menuparticleint.png")));
		
		
		
		
		textures.put("bg", new Texture(Gdx.files.internal("textures/bg.png")));
		textures.put("healthbar", new Texture(Gdx.files.internal("textures/healthbar.png")));
		textures.put("healthbarframe", new Texture(Gdx.files.internal("textures/healthbarframe.png")));
		
		textures.put("core", new Texture(Gdx.files.internal("textures/core.png")));
		textures.put("particle", new Texture(Gdx.files.internal("textures/particle.png")));
		textures.put("particleint", new Texture(Gdx.files.internal("textures/particleint.png")));

	

		textures.put("path", new Texture(Gdx.files.internal("textures/path.png")));
		
		textures.put("quark", new Texture(Gdx.files.internal("textures/quark.png")));
		
		textures.put("gas", new Texture(Gdx.files.internal("textures/gas.png")));
		
		textures.put("dot", new Texture(Gdx.files.internal("textures/dot.png")));
		textures.put("dot2", new Texture(Gdx.files.internal("textures/dot2.png")));

	}
	
}
