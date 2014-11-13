package com.molecule.entity.particle;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.molecule.entity.granule.ParticleTrail;
import com.molecule.entity.molecule.Nucleus;
import com.molecule.system.Util;
import com.molecule.system.util.GranuleBuffer;
import com.molecule.system.util.TextureLoader;

public class InternalParticle extends Particle{

	public InternalParticle(Nucleus parent){
		super(parent);
		
		img = new Sprite(TextureLoader.textures.get("particleint"));
		
		drawOffsetX = img.getWidth() / 2;
		drawOffsetY = img.getHeight() / 2;
	}
	
	public InternalParticle(Nucleus parent, float angleOffset){
		super(parent);
		
		img = new Sprite(TextureLoader.textures.get("particleint"));

		drawOffsetX = img.getWidth() / 2;
		drawOffsetY = img.getHeight() / 2;
		
		this.angleOffset = angleOffset;
	}
	
	public InternalParticle(Nucleus parent, float angleOffset, float sineOffset){
		super(parent);
		
		img = new Sprite(TextureLoader.textures.get("particleint"));

		drawOffsetX = img.getWidth() / 2;
		drawOffsetY = img.getHeight() / 2;
		
		this.angleOffset = angleOffset;
		this.sineOffset = sineOffset;
	}
	
	public void draw(SpriteBatch batch){
//		sineOffsetX = (float) Math.sin(sineTime + sineOffset) * 75;
//		sineOffsetY = (float) Math.cos(sineTime + sineOffset) * 20;
		
		float sineOffsetX = (float) ((20 * Math.cos(sineTime + sineOffset) * Math.cos(ellipseAngle + angleOffset)) - 
				(3f * Math.sin(sineTime + sineOffset) * Math.sin(ellipseAngle + angleOffset)));
		
		float sineOffsetY = (float) ((20 * Math.cos(sineTime + sineOffset) * Math.sin(ellipseAngle + angleOffset)) + 
				(3f * Math.sin(sineTime + sineOffset) * Math.cos(ellipseAngle + angleOffset)));
		
		ellipseAngle = ellipseAngle + 0.025f;
		
		sineTime += 0.1f;
		
		if(boostStacks <= 0 && Util.rnd.nextInt(400) == 0)
			boostStacks = Util.rnd.nextInt(25) + 75;
		else if(boostStacks > 0){
			ellipseAngle = ellipseAngle + 0.025f;
			sineTime += 0.1f;
			boostStacks--;
		}
		
		centerX = parent.getCenterX() + sineOffsetX;
		centerY = parent.getCenterY() + sineOffsetY;
		
		centerV.set(centerX, centerY);
		
		float x = centerX - drawOffsetX;
		float y = centerY - drawOffsetY;
		
		for(ParticleMod pm : mods)
			pm.tick(1);
		
		img.setColor(tint);
		img.setPosition(x, y);
		img.draw(batch);
	}
	
}
