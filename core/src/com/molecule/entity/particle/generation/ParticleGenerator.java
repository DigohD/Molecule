package com.molecule.entity.particle.generation;

import com.molecule.entity.particle.Particle;
import com.molecule.entity.particle.ParticleMod;
import com.molecule.system.Util;

public class ParticleGenerator {

	private 
	
	public ParticleGenerator(){
		
	}
	
	public static Particle getNewParticle(int powerLevel){
		powerLevel = powerLevel * 10;
		int currency = powerLevel + Util.rnd.nextInt(powerLevel);
		
		int binRnd = Util.rnd.nextInt(2);
		if(binRnd == 0){
			return getNewExtParticle(currency);
		}else{
			return getNewIntParticle(currency);
		}
	}
	
	private static Particle getNewExtParticle(int powerLevel){
		return null;
	}
	
	private static Particle getNewIntParticle(int powerLevel){
		return null;
	}
	
	private ParticleMod extShop(int amount){
		return null;
	}
}
