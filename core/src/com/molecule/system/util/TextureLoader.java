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
		
		textures.put("bg", new Texture(Gdx.files.internal("textures/bg.png")));
		
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
