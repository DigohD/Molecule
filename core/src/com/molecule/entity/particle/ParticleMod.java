package com.molecule.entity.particle;

import com.molecule.entity.Tickable;

public abstract class ParticleMod  implements Tickable{

	protected ExternalParticle parent;
	
	public ParticleMod(ExternalParticle parent){
		this.parent = parent;
	}
	
}
