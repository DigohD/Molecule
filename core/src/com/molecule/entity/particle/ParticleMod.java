package com.molecule.entity.particle;

import com.molecule.entity.Tickable;

public abstract class ParticleMod  implements Tickable{

	protected Particle parent;
	
	public ParticleMod(Particle parent){
		this.parent = parent;
	}
	
}
