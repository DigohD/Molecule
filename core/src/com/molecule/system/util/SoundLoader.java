package com.molecule.system.util;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class SoundLoader{

	public static HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	public static HashMap<String, Music> music = new HashMap<String, Music>();
	
	public SoundLoader(){
		sounds.put("buttonclick", Gdx.audio.newSound(Gdx.files.internal("sound/buttonclick.wav")));
		
		sounds.put("quarkgun", Gdx.audio.newSound(Gdx.files.internal("sound/quarkgun.wav")));
		
		sounds.put("death", Gdx.audio.newSound(Gdx.files.internal("sound/death.wav")));
		
		// UI
		sounds.put("pdelete", Gdx.audio.newSound(Gdx.files.internal("sound/pdelete.wav")));
		sounds.put("pequip", Gdx.audio.newSound(Gdx.files.internal("sound/pequip.wav")));
		sounds.put("punequip", Gdx.audio.newSound(Gdx.files.internal("sound/punequip.wav")));
		sounds.put("pselect", Gdx.audio.newSound(Gdx.files.internal("sound/pselect.wav")));
		
		music.put("menum", Gdx.audio.newMusic(Gdx.files.internal("sound/menum.mp3")));
		music.put("menum2", Gdx.audio.newMusic(Gdx.files.internal("sound/menum2.mp3")));
		music.put("gamem1", Gdx.audio.newMusic(Gdx.files.internal("sound/gamem1.mp3")));
	}
	
}
