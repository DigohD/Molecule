package com.molecule.entity.player;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.Collideable;
import com.molecule.entity.Entity;
import com.molecule.entity.Renderable;
import com.molecule.entity.Tickable;
import com.molecule.entity.enemy.Enemy;
import com.molecule.entity.granule.emitter.Emitter;
import com.molecule.entity.molecule.Nucleus;
import com.molecule.entity.molecule.Nucleus.Type;
import com.molecule.entity.particle.ExternalParticle;
import com.molecule.entity.particle.InternalParticle;
import com.molecule.entity.particle.Particle;
import com.molecule.entity.particle.defensive.Plasmatron;
import com.molecule.entity.particle.offensive.Projectile;
import com.molecule.entity.particle.offensive.QuarkGun;
import com.molecule.entity.stats.StatsSheet.StatID;
import com.molecule.system.Camera;
import com.molecule.system.EntityManager;
import com.molecule.system.Game;
import com.molecule.system.util.PhysicsUtil;
import com.molecule.system.util.SoundLoader;
import com.molecule.system.util.TextureLoader;

public class Player extends Entity implements Tickable, Collideable, Renderable{

	private Vector2 velocity;
	private Vector2 targetVel;
	private Vector2 targetPos;
	private Vector2 diff = new Vector2(0,0);
	private Vector2 pos = new Vector2(0,0);
	
	private Nucleus nucleus;
	
	private Sprite healthBar = new Sprite(TextureLoader.textures.get("healthbar"));
	private Sprite healthBarFrame = new Sprite(TextureLoader.textures.get("healthbarframe"));
	private BitmapFont healthFont;
	
	private ArrayList<Particle> inventory = new ArrayList<Particle>();
	
	private boolean collision = false;
	private boolean update;
	
	public Player(Vector2 position){
		super(position);
		nucleus = new Nucleus(Type.PLAYER);
		velocity = new Vector2(0,0);
		targetVel = new Vector2(0,0);
		targetPos = new Vector2(0,0);
		
		healthFont = new BitmapFont(Gdx.files.internal("data/font.fnt"),false);
		
		for(int i = 0; i < 2; i++){
			ExternalParticle p = new ExternalParticle(nucleus);
			p.addParticleMod(new QuarkGun(p));
			inventory.add(p);
		}
		
		for(int i = 0; i < 2; i++){
			InternalParticle p = new InternalParticle(nucleus);
			p.addParticleMod(new Plasmatron(p, StatID.HP_MAX, 10));
			p.addParticleMod(new Plasmatron(p, StatID.HP_REGEN, 0.25f));
			inventory.add(p);
		}
		
		EntityManager.addEntity(this);
	}

	int timer = 0; 
	@Override
	public void tick(float dt) {
		if(!update){
			targetVel.x = 0;
			targetVel.y = 0;
		}
		
//		if(collision){
//			timer++;
//			if(timer >= 60){
//				timer = 0;
//				collision = false;
//			}
//		}else{
			velocity.x = PhysicsUtil.approach(targetVel.x, velocity.x, dt * 5.0f);
			velocity.y = PhysicsUtil.approach(targetVel.y, velocity.y, dt * 5.0f);
//		}
		
		
		
		nucleus.setVelocity(velocity);
		
	}
	
	@Override
	public void collisionWith(Collideable obj) {
		if(obj instanceof Projectile){
			float hpOld = nucleus.getStats().getStat(StatID.HP_NOW).getBase();
			Projectile p = (Projectile) obj;
			float newHP = hpOld - p.getDamage();
			if(newHP <= 0){
				nucleus.setLive(false);
				live = false;
				SoundLoader.sounds.get("death").play();
				
				Emitter emitter = new Emitter(nucleus.getPosition(), 25, 2, 5, "gas");
				emitter.setSpreadRadial(new Vector2(1, 0));
				emitter.setRandomness(20f);
				
				return;
			}else
				nucleus.getStats().getStat(StatID.HP_NOW).setNewBase(newHP);
		}
		
		if(obj instanceof Enemy){
//			collision = true;
//			velocity.x = -velocity.x * 1.01f;
//			velocity.y = -velocity.y * 1.01f;
		}
	}

	public Vector2 getTargetVel() {
		return targetVel;
	}

	public void setTargetVel(float x, float y) {
		targetVel.x = x;
		targetVel.y = y;
		update = true;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public Nucleus getNucleus() {
		return nucleus;
	}

	@Override
	public Rectangle getRect() {
		return nucleus.getRect();
	}
	
	@Override
	public Vector2 getPosition(){
		return nucleus.getPosition();
	}

	float alertScale, alertTimer;
	
	@Override
	public void render(SpriteBatch batch) {
		float hp_now = nucleus.getStats().getStat(StatID.HP_NOW).getTotal();
		float hp_max = nucleus.getStats().getStat(StatID.HP_MAX).getTotal();
		float percent = hp_now / hp_max;
		
		if(percent < 0.275f){
			tickAlert();
		}else{
			alertScale = 0;
			alertTimer = 0;
		}
		
		float newR = 0.8f - (percent * 0.8f);
		float newG = 0.0f + (percent * 0.8f);
		
		healthBar.setColor(newR, newG, 0.0f, 1f);
		healthFont.setColor(newR, newG, 0, 1);
		
		healthBar.setRegion(0, 0, (int) (400 * percent), 100);
		healthBar.setBounds(50, 0, (int) (400 * percent), 100);
		healthBar.setScale(1.5f + alertScale);
		healthBar.setPosition(120 + (Camera.getCamX() - Game.WIDTH/2), 920 + (Camera.getCamY() - Game.HEIGHT/2));
		healthBarFrame.setScale(1.5f);
		healthBarFrame.setPosition(120 + (Camera.getCamX() - Game.WIDTH/2), 920 + (Camera.getCamY() - Game.HEIGHT/2));
		
		String hp_now_S = String.format("%.1f", hp_now);
		String hp_max_S = String.format("%.1f", hp_max);
		
		healthFont.setScale(1f + alertScale);
		healthFont.draw(batch, hp_now_S + "/" + hp_max_S, 
				340  + (Camera.getCamX() - Game.WIDTH/2), 940 + (Camera.getCamY() - Game.HEIGHT/2));
		
		healthBarFrame.draw(batch);
		healthBar.draw(batch);
	}
	
	private void tickAlert(){
		alertTimer += 0.2f;
		alertScale = (float) Math.sin(alertTimer) / 5f;
	}

	public boolean isCollision() {
		return collision;
	}
	
	public ArrayList<Particle> getEquippedParticles(){
		return nucleus.getChildren();
	}
	
	public void equip(Particle p){
		nucleus.addParticle(p);
		inventory.remove(p);
	}
	
	public void unequip(Particle p){
		if(nucleus.getChildren().contains(p))
			nucleus.removeParticle(p);
		inventory.add(p);
	}
	
	public void addToInventory(Particle p){
		inventory.add(p);
	}
	
	public void removeFromInventory(Particle p){
		inventory.remove(p);
	}

	public ArrayList<Particle> getInventory() {
		return inventory;
	}
	
	
}
