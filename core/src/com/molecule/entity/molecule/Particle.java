package com.molecule.entity.molecule;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.molecule.system.util.TextureLoader;


import com.molecule.system.Util;


public class Particle{

	private Nucleus parent;
	private Texture img, dot;
	
	private LinkedList<Path> trail = new LinkedList<Path>();
	
	private float sineTime, angleOffset, sineOffset, ellipseAngle;
	
	private int boostStacks;
	
	private float sineOffsetX, sineOffsetY, centerX, centerY, drawOffsetX, drawOffsetY; 
	
	public Particle(Nucleus parent){
		this.parent = parent;
		img = TextureLoader.textures.get("particle");
		dot = TextureLoader.textures.get("dot2");
		
		drawOffsetX = img.getWidth() / 2;
		drawOffsetY = img.getHeight() / 2;
	}
	
	public Particle(Nucleus parent, float angleOffset){
		this.parent = parent;
		img = TextureLoader.textures.get("particle");
		dot = TextureLoader.textures.get("dot2");
		
		drawOffsetX = img.getWidth() / 2;
		drawOffsetY = img.getHeight() / 2;
		
		this.angleOffset = angleOffset;
	}
	
	public Particle(Nucleus parent, float angleOffset, float sineOffset){
		this.parent = parent;
		img = TextureLoader.textures.get("particle");
		dot = TextureLoader.textures.get("dot2");
		
		drawOffsetX = img.getWidth() / 2;
		drawOffsetY = img.getHeight() / 2;
		
		this.angleOffset = angleOffset;
		this.sineOffset = sineOffset;
	}
	
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
			
		if(trail.size() >= 30){
			trail.removeFirst();
			trail.addLast(new Path(centerX - 1, centerY - 1));
		}else
			trail.addLast(new Path(centerX - 1, centerY - 1));
		
		centerX = parent.getCenterX() + sineOffsetX;
		centerY = parent.getCenterY() + sineOffsetY;
		
		float x = centerX - drawOffsetX;
		float y = centerY - drawOffsetY;
		
		for(Path p : trail){
			p.draw(batch);
		}
		
		batch.draw(img, x, y);
//		batch.draw(dot, x, y);
	}
	
}