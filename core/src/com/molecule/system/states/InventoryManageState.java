package com.molecule.system.states;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.molecule.entity.particle.ExternalParticle;
import com.molecule.entity.particle.InternalParticle;
import com.molecule.entity.particle.Particle;
import com.molecule.entity.player.Player;
import com.molecule.system.Camera;
import com.molecule.system.EntityManager;
import com.molecule.system.Game;
import com.molecule.system.GameStateManager;
import com.molecule.system.Renderer;
import com.molecule.system.gui.Button;
import com.molecule.system.gui.ParticleSlot;
import com.molecule.system.util.TextureLoader;

public class InventoryManageState extends GameState implements InputProcessor{

	public enum invType{
		EXTERNALS, INTERNALS
	}
	
	private Texture bg;
	private Button back, deleteButton, equipButton, unequipButton;
	private Player player = EntityManager.getPlayer();
	private ArrayList<ParticleSlot> particleSlots = new ArrayList<ParticleSlot>();
	private Camera cam;
	private int timer = 0, touchTimer = 0;
	private float scrollOffset;
	private boolean click = false, backClicked = false, deleteClicked = false, equipClicked = false, unequipClicked;
	
	private static BitmapFont nameFont = new BitmapFont(Gdx.files.internal("data/font.fnt"),false);
	
	private ParticleSlot selected;

	private invType type;
	
	public InventoryManageState(GameStateManager gsm, invType type) {
		super(gsm);
		this.type = type;
		init();
	}

	@Override
	public void init(){
		bg = TextureLoader.textures.get("inventorybg");
		
		back = new Button("buttonback", 30, 15);
		deleteButton = new Button("delete", "deletep", 1020, 30);
		equipButton = new Button("equip", "equipp", 1250, 30);
		unequipButton = new Button("unequip", "unequipp", 1250, 30);
		
		particleSlots = new ArrayList<ParticleSlot>();
		
		if(type == invType.EXTERNALS){
			for(Particle p : player.getEquippedParticles()){
				if(p instanceof ExternalParticle){
					ParticleSlot eq = new ParticleSlot(p);
					eq.setEquipped(true);
					particleSlots.add(eq);
				}
			}
			
			for(Particle p : player.getInventory())
				if(p instanceof ExternalParticle)
					particleSlots.add(new ParticleSlot(p));
		}else if(type == invType.INTERNALS){
			for(Particle p : player.getEquippedParticles()){
				if(p instanceof InternalParticle){
					ParticleSlot eq = new ParticleSlot(p);
					eq.setEquipped(true);
					particleSlots.add(eq);
				}
			}
			
			for(Particle p : player.getInventory())
				if(p instanceof InternalParticle)
					particleSlots.add(new ParticleSlot(p));
		}
		
		
		nameFont.setScale(1.8f);
	}

	@Override
	public void tick(float dt) {
		Gdx.input.setInputProcessor(this);
		Camera.getCam().position.set(Camera.getCam().viewportWidth / 2f, Camera.getCam().viewportHeight / 2f, 0);
		touchTimer++;
		
		if(backClicked || timer > 0 && !click){
			click = true;

			timer++;
			if(timer >= 30){
				timer = 0;
				backClicked = false;
				click = false;
				gsm.pop();
			}
		}
		else if(deleteClicked || timer > 0 && !click){
			click = true;

			timer++;
			if(timer >= 30){
				timer = 0;
				deleteClicked = false;
				click = false;
				
				player.unequip(selected.getContained());
				player.removeFromInventory(selected.getContained());
				selected.setContained(null);
				selected = null;
						
				ParticleSlot toRemove = null;
				for(ParticleSlot p : particleSlots)
					if(p.getContained() == null)
						toRemove = p;
				particleSlots.remove(toRemove);	
			}
		}
		else if(equipClicked || timer > 0 && !click){
			click = true;

			timer++;
			if(timer >= 30){
				timer = 0;
				equipClicked = false;
				click = false;
				
				player.equip(selected.getContained());
				selected.setEquipped(true);
			}
		}
		else if(unequipClicked || timer > 0 && !click){
			click = true;

			timer++;
			if(timer >= 30){
				timer = 0;
				unequipClicked = false;
				click = false;
				
				player.unequip(selected.getContained());
				selected.setEquipped(false);
			}
		}
	}

	@Override
	public void render(Renderer renderer) {
		renderer.getBatch().begin();
		renderer.getBatch().setProjectionMatrix(Camera.getCam().combined);
		Camera.getCam().update();   
		
		renderer.getBatch().draw(bg, 0, 0);
		
		int counter = 0;
		for(ParticleSlot ps : particleSlots){
			ps.draw(renderer, 30, 800 - (counter++ * 234) + scrollOffset);
		}
		
		if(selected != null)
			selected.drawMods(renderer, 1020, 900);
		
		renderer.getBatch().draw(bg, 0, 0);
		
		back.render(renderer.getBatch());
		if(backClicked) renderer.getBatch().draw(back.getClickedSprite(), back.getX(), back.getY());
		
		if(selected != null){
			nameFont.draw(renderer.getBatch(), selected.getContained().getName(), 1020, 1030);
			
			deleteButton.render(renderer.getBatch());
			if(deleteClicked) renderer.getBatch().draw(deleteButton.getClickedSprite(), deleteButton.getX(), deleteButton.getY());
			
			if(!selected.isEquipped()){
				equipButton.render(renderer.getBatch());
				if(equipClicked) renderer.getBatch().draw(equipButton.getClickedSprite(), equipButton.getX(), equipButton.getY());
			}else{
				unequipButton.render(renderer.getBatch());
				if(unequipClicked) renderer.getBatch().draw(unequipButton.getClickedSprite(), unequipButton.getX(), unequipButton.getY());
			}
		}
		renderer.getBatch().end();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		float x = (float)screenX * ((float)Game.WIDTH / Gdx.graphics.getWidth());
		float y = (Gdx.graphics.getHeight() - screenY) * ((float)Game.HEIGHT / Gdx.graphics.getHeight());
		
		touchTimer = 0;
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		float x = (float)screenX * ((float)Game.WIDTH / Gdx.graphics.getWidth());
		float y = (Gdx.graphics.getHeight() - screenY) * ((float)Game.HEIGHT / Gdx.graphics.getHeight());
		
		if(touchTimer < 10){
			if(back.getRect().contains(x, y)){
				backClicked = true;
				return true;
			}else if(deleteButton.getRect().contains(x, y)){
				deleteClicked = true;
				return true;
			}else if(selected != null && !selected.isEquipped() && equipButton.getRect().contains(x, y)){
				equipClicked = true;
				return true;
			}else if(selected != null && selected.isEquipped() && unequipButton.getRect().contains(x, y)){
				unequipClicked = true;
				return true;
			}
			
			
			for(ParticleSlot ps : particleSlots){
				if(ps.getRect().contains(x, y)){
					selected = ps;
					selected.setScrollOffset(0);
				}
			}	
		}
		
		return false;
	}

	private float prevX, prevY;
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		float x = (float)screenX * ((float)Game.WIDTH / Gdx.graphics.getWidth());
		float y = (Gdx.graphics.getHeight() - screenY) * ((float)Game.HEIGHT / Gdx.graphics.getHeight());
		
		float dX = x - prevX;
		float dY = y - prevY;
		
		if (dY > 75 || dY < -75) {
			prevY = y;
			return true;
		}
		
		if(x < Game.WIDTH / 2){
			prevY = y;
			scrollOffset += dY;
		}else if(x > Game.WIDTH / 2 && selected != null){
			prevY = y;
			selected.setScrollOffset(selected.getScrollOffset() + dY);
		}
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
