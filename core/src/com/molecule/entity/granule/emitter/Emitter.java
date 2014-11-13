package com.molecule.entity.granule.emitter;

import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.Entity;
import com.molecule.entity.Tickable;
import com.molecule.system.EntityManager;
import com.molecule.system.Util;
import com.molecule.system.util.GranuleBuffer;

public class Emitter extends Entity implements Tickable{

	public enum SpreadType{
		IMPACT, REVIMPACT, RADIAL
	}
	
	private int granulesPerTick, granuleLifetime, emitterLifetime;
	private Vector2 refVector;
	private float randomness;
	String imageName;
	SpreadType type;
	
	public Emitter(Vector2 pos, int emitterLifetime, int granulesPerTick, int granuleLifetime, String imageName){
		super(pos.cpy());
		this.granulesPerTick = granulesPerTick;
		this.emitterLifetime = emitterLifetime;
		this.granuleLifetime = granuleLifetime;
		this.imageName = imageName;
		
		refVector = new Vector2(5, 5);
		type = SpreadType.RADIAL;
		
		randomness = 1;
		
		EntityManager.addEntity(this);
	}

	@Override
	public void tick(float dt) {
		emitterLifetime--;
		if(emitterLifetime < 0){
			live = false;
			return;
		}
		switch(type){
			case RADIAL:
				for(int i = 0; i < granulesPerTick; i++)
					radialEmit();
				break;
			case REVIMPACT:
				for(int i = 0; i < granulesPerTick; i++)
					impactEmit();
				break;
			case IMPACT:
				for(int i = 0; i < granulesPerTick; i++)
					reverseImpactEmit();
				break;
		}
	}
	
	private void radialEmit(){
		Vector2 granuleVelocity = refVector.cpy();
		granuleVelocity.rotate(Util.rnd.nextInt(360));
		if(randomness != 1)
			granuleVelocity.scl((Util.getFloat(1, 1 + randomness)));
		GranuleBuffer.getGranule().spawn(granuleLifetime, imageName, position.x, position.y, granuleVelocity.x, granuleVelocity.y);
	}
	
	private void impactEmit(){
		Vector2 granuleVelocity = refVector.cpy();
		granuleVelocity.rotate(-90);
		granuleVelocity.rotate(Util.rnd.nextInt(180));
		granuleVelocity.scl(0.5f);
		if(randomness != 1)
			granuleVelocity.scl((Util.getFloat(1, 1 + randomness)));
		GranuleBuffer.getGranule().spawn(granuleLifetime, imageName, position.x, position.y, granuleVelocity.x, granuleVelocity.y);
	}
	
	private void reverseImpactEmit(){
		Vector2 granuleVelocity = refVector.cpy();
		granuleVelocity.rotate(-270);
		granuleVelocity.rotate(Util.rnd.nextInt(180));
		granuleVelocity.scl(0.5f);
		if(randomness != 1)
			granuleVelocity.scl((Util.getFloat(1, 1 + randomness)));
		GranuleBuffer.getGranule().spawn(granuleLifetime, imageName, position.x, position.y, granuleVelocity.x, granuleVelocity.y);
	}
	
	
	public void setSpreadImpact(Vector2 originVelocity){
		this.refVector = originVelocity.cpy();
		type = SpreadType.IMPACT;
	}
	
	public void setSpreadReverseImpact(Vector2 originVelocity){
		this.refVector = originVelocity.cpy();
		type = SpreadType.REVIMPACT;
	}
	
	public void setSpreadRadial(Vector2 granuleVelocity){
		this.refVector = granuleVelocity.cpy();
		type = SpreadType.RADIAL;
	}

	public float getRandomness() {
		return randomness;
	}

	public void setRandomness(float randomness) {
		this.randomness = randomness;
	}
	
	
}
