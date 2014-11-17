package com.molecule.system.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.molecule.entity.Renderable;
import com.molecule.entity.particle.Particle;
import com.molecule.entity.particle.ParticleMod;
import com.molecule.entity.player.Player;
import com.molecule.system.EntityManager;
import com.molecule.system.Renderer;
import com.molecule.system.util.TextureLoader;

public class ParticleSlot{

	private Particle contained;
	private static Texture button = TextureLoader.textures.get("inventoryitem");
	private static Texture buttonOn = TextureLoader.textures.get("inventoryitemon");
	private static BitmapFont nameFont = new BitmapFont(Gdx.files.internal("data/font.fnt"),false);
	
	private Rectangle rect = new Rectangle();
	private float scrollOffset;
	private boolean isEquipped = false;
	
	public ParticleSlot(Particle contained){
		this.contained = contained;
		nameFont.setScale(1.8f);
	}
	
	public void draw(Renderer renderer, float x, float y){
		if(isEquipped)
			renderer.getBatch().draw(buttonOn, x, y);
		else
			renderer.getBatch().draw(button, x, y);
		renderer.getBatch().draw(contained.getImg().getTexture(), x + 50, y + 50, 100, 100);
		
		nameFont.draw(renderer.getBatch(), contained.getName(), x + 280, y + 136);
		
		rect = new Rectangle(x, y, button.getWidth(), button.getHeight());
	}

	public void drawMods(Renderer renderer, float x, float y){
		if(this.scrollOffset < 0)
			scrollOffset = 0;
		int yOffset = (int) (0 + scrollOffset);
		
		for(ParticleMod pm : contained.getMods()){
			pm.drawMod(renderer, x, y + yOffset);
			yOffset -= pm.getDrawHeight();
		}
	}
	
	public Rectangle getRect() {
		return rect;
	}

	public Particle getContained() {
		return contained;
	}

	public void setContained(Particle contained) {
		this.contained = contained;
	}

	public float getScrollOffset() {
		return scrollOffset;
	}

	public void setScrollOffset(float scrollOffset) {
		this.scrollOffset = scrollOffset;
	}

	public boolean isEquipped() {
		return isEquipped;
	}

	public void setEquipped(boolean isEquipped) {
		this.isEquipped = isEquipped;
	}
	
	
	
}
