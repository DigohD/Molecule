package com.molecule.entity.particle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.molecule.entity.Tickable;
import com.molecule.system.Renderer;

public abstract class ParticleMod  implements Tickable{

	protected Particle parent;
	protected float drawHeight;
	protected static BitmapFont font = new BitmapFont(Gdx.files.internal("data/font.fnt"),false);
	protected static BitmapFont fontLight = new BitmapFont(Gdx.files.internal("data/fontlight.fnt"),false);
	
	public ParticleMod(Particle parent){
		this.parent = parent;
	}
	
	public abstract void drawMod(Renderer renderer, float x, float y);
	public abstract int getDrawHeight();
	public abstract String getName();
}
