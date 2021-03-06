package com.molecule.entity.particle;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.molecule.entity.granule.ParticleTrail;
import com.molecule.entity.molecule.Nucleus;
import com.molecule.system.util.GranuleBuffer;
import com.molecule.system.util.TextureLoader;
import com.molecule.system.Renderer;
import com.molecule.system.Util;


public class ExternalParticle extends Particle{

	public ExternalParticle(Nucleus parent){
		super(parent);
		
		img = new Sprite(TextureLoader.textures.get("particle" + (Util.rnd.nextInt(4) + 1)));
		
		tint = new Color(Util.getFloat(0.1f, 1f), Util.getFloat(0.1f, 1f), Util.getFloat(0.1f, 1f), 1);
		color.x = tint.r;
		color.y = tint.g;
		color.z = tint.b;
		
		trail = new ParticleTrail(this, 1, 20);
		
		drawOffsetX = img.getWidth() / 2;
		drawOffsetY = img.getHeight() / 2;
	}
	
	public ExternalParticle(Nucleus parent, float angleOffset){
		super(parent);
		
		img = new Sprite(TextureLoader.textures.get("particle" + (Util.rnd.nextInt(4) + 1)));
		
		tint = new Color(Util.getFloat(0.1f, 1f), Util.getFloat(0.1f, 1f), Util.getFloat(0.1f, 1f), 1);
		color.x = tint.r;
		color.y = tint.g;
		color.z = tint.b;
		
		trail = new ParticleTrail(this, 1, 20);
		
		drawOffsetX = img.getWidth() / 2;
		drawOffsetY = img.getHeight() / 2;
		
		this.angleOffset = angleOffset;
	}
	
	public ExternalParticle(Nucleus parent, float angleOffset, float sineOffset){
		super(parent);
		
		img = new Sprite(TextureLoader.textures.get("particle" + (Util.rnd.nextInt(4) + 1)));
		
		tint = new Color(Util.getFloat(0.1f, 1f), Util.getFloat(0.1f, 1f), Util.getFloat(0.1f, 1f), 1f);
		color.x = tint.r;
		color.y = tint.g;
		color.z = tint.b;
		
		trail = new ParticleTrail(this, 1, 20);
		
		drawOffsetX = img.getWidth() / 2;
		drawOffsetY = img.getHeight() / 2;
		
		this.angleOffset = angleOffset;
		this.sineOffset = sineOffset;
	}
	
	@Override
	public void draw(SpriteBatch batch){
		float x = centerX - drawOffsetX;
		float y = centerY - drawOffsetY;
		
		Renderer.getShader().setUniformf("color", color);
		
		img.setColor(tint);
		img.setPosition(x, y);
		img.draw(batch);
	}

	@Override
	public void tick(float dt) {
		float sineOffsetX = (float) ((75 * Math.cos(sineTime + sineOffset) * Math.cos(ellipseAngle + angleOffset)) - 
				(20 * Math.sin(sineTime + sineOffset) * Math.sin(ellipseAngle + angleOffset)));
		
		float sineOffsetY = (float) ((75 * Math.cos(sineTime + sineOffset) * Math.sin(ellipseAngle + angleOffset)) + 
				(20 * Math.sin(sineTime + sineOffset) * Math.cos(ellipseAngle + angleOffset)));
		
		ellipseAngle = ellipseAngle + 0.025f;
		
		sineTime += 0.1f;
		
		img.setRotation(sineTime * 30);
		
		if(boostStacks <= 0 && Util.rnd.nextInt(400) == 0)
			boostStacks = Util.rnd.nextInt(25) + 75;
		else if(boostStacks > 0){
			ellipseAngle = ellipseAngle + 0.025f;
			sineTime += 0.1f;
			boostStacks--;
		}
		
		GranuleBuffer.getGranule().spawn(30, "path", centerX - 1, centerY - 1, tint.r, tint.g, tint.b);
		
		centerX = parent.getCenterX() + sineOffsetX;
		centerY = parent.getCenterY() + sineOffsetY;
		
		centerV.set(centerX, centerY);
		
		for(ParticleMod pm : mods)
			pm.tick(1);
	}
}