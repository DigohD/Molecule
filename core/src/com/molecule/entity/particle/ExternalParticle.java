package com.molecule.entity.particle;

import java.util.ArrayList;
import java.util.LinkedList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.granule.ParticleTrail;
import com.molecule.entity.molecule.Nucleus;
import com.molecule.entity.molecule.Nucleus.Type;
import com.molecule.system.util.GranuleBuffer;
import com.molecule.system.util.TextureLoader;


import com.molecule.system.Util;


public class ExternalParticle extends Particle{

	public ExternalParticle(Nucleus parent){
		super(parent);
		
		img = new Sprite(TextureLoader.textures.get("particle"));
		
		trail = new ParticleTrail(this, 1, 20);
		
		drawOffsetX = img.getWidth() / 2;
		drawOffsetY = img.getHeight() / 2;
	}
	
	public ExternalParticle(Nucleus parent, float angleOffset){
		super(parent);
		
		img = new Sprite(TextureLoader.textures.get("particle"));
		
		trail = new ParticleTrail(this, 1, 20);
		
		drawOffsetX = img.getWidth() / 2;
		drawOffsetY = img.getHeight() / 2;
		
		this.angleOffset = angleOffset;
	}
	
	public ExternalParticle(Nucleus parent, float angleOffset, float sineOffset){
		super(parent);
		
		img = new Sprite(TextureLoader.textures.get("particle"));
		
		trail = new ParticleTrail(this, 1, 20);
		
		drawOffsetX = img.getWidth() / 2;
		drawOffsetY = img.getHeight() / 2;
		
		this.angleOffset = angleOffset;
		this.sineOffset = sineOffset;
	}
	
	@Override
	public void draw(SpriteBatch batch){
//		sineOffsetX = (float) Math.sin(sineTime + sineOffset) * 75;
//		sineOffsetY = (float) Math.cos(sineTime + sineOffset) * 20;
		
		float sineOffsetX = (float) ((75 * Math.cos(sineTime + sineOffset) * Math.cos(ellipseAngle + angleOffset)) - 
				(20 * Math.sin(sineTime + sineOffset) * Math.sin(ellipseAngle + angleOffset)));
		
		float sineOffsetY = (float) ((75 * Math.cos(sineTime + sineOffset) * Math.sin(ellipseAngle + angleOffset)) + 
				(20 * Math.sin(sineTime + sineOffset) * Math.cos(ellipseAngle + angleOffset)));
		
		ellipseAngle = ellipseAngle + 0.025f;
		
		sineTime += 0.1f;
		
		if(boostStacks <= 0 && Util.rnd.nextInt(400) == 0)
			boostStacks = Util.rnd.nextInt(25) + 75;
		else if(boostStacks > 0){
			ellipseAngle = ellipseAngle + 0.025f;
			sineTime += 0.1f;
			boostStacks--;
		}
			
		GranuleBuffer.getGranule().spawn(30, "path", centerX - 1, centerY - 1);
		
//		trail.tick(1f);
//		trail.render(batch);
		
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