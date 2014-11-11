package com.molecule.entity.stats;

import com.molecule.entity.stats.StatsSheet.StatID;

public class StatMod {

	private float amount;
	private String sourceID;
	private StatID affectedStat;
	private boolean isAddMod;
	
	public StatMod(float amount, StatID affectedStat, String sourceID, boolean isAddMod) {
		this.amount = amount;
		this.affectedStat = affectedStat;
		this.sourceID = sourceID;
		this.isAddMod = isAddMod;
	}

	public void addAmount(float amount){
		this.amount = this.amount + amount;
	}
	
	public void mulAmount(float amount){
		this.amount = this.amount * amount;
	}
	
	public float getAmount() {
		return amount;
	}

	public StatID getAffectedStat() {
		return affectedStat;
	}

	public String getSourceID() {
		return sourceID;
	}

	public boolean isAddMod() {
		return isAddMod;
	}

	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}
}
