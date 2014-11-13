package com.molecule.system.util;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class SoundLoader{

	public static HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	
	public SoundLoader(){
		sounds.put("quarkgun", Gdx.audio.newSound(Gdx.files.internal("sound/quarkgun.wav")));
		
		sounds.put("death", Gdx.audio.newSound(Gdx.files.internal("sound/death.wav")));
	}
	
}
