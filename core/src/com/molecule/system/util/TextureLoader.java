package com.molecule.system.util;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureLoader{

	public static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	
	public TextureLoader(){
		textures.put("core", new Texture(Gdx.files.internal("core.png")));
		textures.put("particle", new Texture(Gdx.files.internal("particle.png")));

		textures.put("joystick", new Texture(Gdx.files.internal("joystick.png")));

		textures.put("path", new Texture(Gdx.files.internal("path.png")));
		
		textures.put("dot", new Texture(Gdx.files.internal("dot.png")));
		textures.put("dot2", new Texture(Gdx.files.internal("dot2.png")));

	}
	
}
