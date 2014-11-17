package com.molecule.entity.particle.defensive;

import com.molecule.entity.particle.ExternalParticle;
import com.molecule.entity.particle.Particle;
import com.molecule.entity.particle.ParticleMod;
import com.molecule.entity.stats.StatMod;
import com.molecule.entity.stats.StatsSheet;
import com.molecule.entity.stats.StatsSheet.StatID;
import com.molecule.system.Renderer;
import com.molecule.system.util.StatHashBuffer;

public class Plasmatron extends ParticleMod{

	private StatID stat;
	private float amount;
	private StatMod mod;
	
	public Plasmatron(Particle parent, StatID stat, float amount) {
		super(parent);
		this.stat = stat;
		this.amount = amount;
		mod = new StatMod(amount, stat, "" + StatHashBuffer.getHash(), true);
	}

	public void applyStatChanges(){
		parent.getParent().getStats().addMod(mod);
	}
	
	public void removeStatChanges(){
		parent.getParent().getStats().removeMod(mod);
	}
	
	@Override
	public void tick(float dt) {
		
	}

	@Override
	public void drawMod(Renderer renderer, float x, float y) {
		font.setScale(1.3f);
		
		font.draw(renderer.getBatch(), getName(), x, y);
		
		fontLight.setScale(1.1f);
		
		String statS = null;
		if(mod.getAffectedStat() == StatID.HP_REGEN)
			statS = String.format("%.1f", mod.getAmount() * 60);
		else
			statS = String.format("%.1f", mod.getAmount());
		
		fontLight.draw(renderer.getBatch(), StatsSheet.getStatString(mod.getAffectedStat()), x, y - 60);
		fontLight.draw(renderer.getBatch(), statS, x + 500, y - 60);
	}

	@Override
	public int getDrawHeight() {
		return 160;
	}

	@Override
	public String getName() {
		switch(mod.getAffectedStat()){
			case HP_MAX:
				return "Solidron";
			case HP_REGEN:
				return "Repatron";
		}
		return "Plasmatron";
	}

}
