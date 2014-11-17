package com.molecule.entity.molecule;

import java.util.ArrayList;
import java.util.Vector;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.DynamicEntity;
import com.molecule.entity.granule.NucleusTrail;
import com.molecule.entity.particle.ExternalParticle;
import com.molecule.entity.particle.Particle;
import com.molecule.entity.stats.StatsSheet;
import com.molecule.entity.stats.StatsSheet.StatID;
import com.molecule.system.EntityManager;
import com.molecule.system.Game;
import com.molecule.system.util.GranuleBuffer;
import com.molecule.system.util.TextureLoader;

public class Nucleus extends DynamicEntity{

	public enum Type {
		PLAYER, ENEMY;
	}
	
	private Sprite img;
	private float centerOffsetX, centerOffsetY;
	private float sineX, sineY, sineTime;
	private Color tint;
	private Vector2 velocity, topLeftPos;
	
	private Rectangle rect;
	private Type ownerType;
	
//	private NucleusTrail trail;
	
	private StatsSheet stats = new StatsSheet();
	
	ArrayList<Particle> children = new ArrayList<Particle>();
	
	public Nucleus(Type ownerType){
		this("core", new Vector2(0, 0), ownerType);
	}
	
	public Nucleus(String image, Vector2 position, Type ownerType){
		super(position);
		this.ownerType = ownerType;
		
		topLeftPos = position.cpy();
		img = new Sprite(TextureLoader.textures.get(image));
		
		tint = new Color(1f, 1f, 1f, 1f);
		
		stats = new StatsSheet();
		
		centerOffsetX = img.getWidth() / 2;
		centerOffsetY = img.getHeight() / 2;
		
//		trail = new NucleusTrail(this, 3, 10);
		
		rect = new Rectangle(position.x, position.y, img.getWidth(), img.getHeight());
		velocity = new Vector2(0, 0);
		
		EntityManager.addEntity(this);
	}
	
	@Override
	public void tick(float dt) {
		sineX = (float) (Math.sin(sineTime) * 10);
		sineY = (float) (Math.sin(sineTime / 3) * 10);
		
		sineTime = sineTime + 0.1f;
		
		position.x = topLeftPos.x - centerOffsetX + sineX;
		position.y = topLeftPos.y - centerOffsetY + sineY;
		
		topLeftPos.add(velocity);

//		if(ownerType == Type.PLAYER)
//			System.out.println(stats.getStat(StatID.HP_NOW).getTotal());
		
//		GranuleBuffer.getGranule().spawn(10, "quark", getCenterX(), getCenterY(), 0, 0);
		
		rect.setX(position.x);
		rect.setY(position.y);
		
		for(Particle p : children)
			p.tick(dt);
		
		/* Stat ticking */
		
		regen();
	}
	
	@Override
	public void render(SpriteBatch batch){
		img.setColor(tint);
		img.setPosition(position.x, position.y);
		img.draw(batch);
	
		for(Particle p : children)
			p.draw(batch);
	}

	public void regen(){
		float hpOld = stats.getStat(StatID.HP_NOW).getBase();
		float newHP = hpOld + stats.getStat(StatID.HP_REGEN).getTotal();
		stats.getStat(StatID.HP_NOW).setNewBase(newHP);
		if(stats.getStat(StatID.HP_NOW).getTotal() > stats.getStat(StatID.HP_MAX).getTotal())
			stats.getStat(StatID.HP_NOW).setNewBase(stats.getStat(StatID.HP_MAX).getTotal());
	}
	
	@Override
	public Vector2 getPosition(){
		return position;
	}
	
	public void addParticle(Particle p){
		children.add(p);
		p.setParent(this);
		p.applyStatsChanges();
	}
	
	public void removeParticle(Particle p){
		children.remove(p);
		p.removeStatsChanges();
	}
	
	public float getCenterX(){
		return position.x + centerOffsetX + sineX;
	}
	
	public float getCenterY(){
		return position.y + centerOffsetY + sineY;
	}

	public void setTint(float r, float g, float b, float a) {
		this.tint = new Color(r, g, b, a);
	}

	public Sprite getImg() {
		return img;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public Type getOwnerType() {
		return ownerType;
	}

	public StatsSheet getStats() {
		return stats;
	}
	
	
}
