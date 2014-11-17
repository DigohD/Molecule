package com.molecule.entity.stats;

import java.util.HashMap;

public class StatsSheet {
	
	protected HashMap<StatID, Stat> sheet = new HashMap<StatID, Stat>();

	public enum StatID {
						/*Primary Stats*/
						LEVEL, HP_MAX, HP_NOW, HP_REGEN
	}
	
	public StatsSheet(){
		super();
		
		sheet.put(StatID.LEVEL, new Stat(StatID.LEVEL, "Level", 1));
		
		sheet.put(StatID.HP_MAX, new Stat(StatID.HP_MAX, "HP", 20));
		sheet.put(StatID.HP_NOW, new Stat(StatID.HP_NOW, "Current HP", sheet.get(StatID.HP_MAX).getTotal()));
		
		sheet.put(StatID.HP_REGEN, new Stat(StatID.HP_REGEN, "HP Regeneration", 0));
	}
	
	public void addMod(StatMod mod){
		Stat affected = sheet.get(mod.getAffectedStat());
		if(mod.isAddMod())
			affected.addAddmod(mod.getSourceID(), mod.getAmount());
		else
			affected.addMulmod(mod.getSourceID(), mod.getAmount());
	}
	
	public void removeMod(StatMod mod){
		Stat affected = sheet.get(mod.getAffectedStat());
		if(mod.isAddMod())
			affected.removeAddmod(mod.getSourceID());
		else
			affected.removeMulmod(mod.getSourceID());
	}
	
	public Stat getStat(StatID ID){
		return sheet.get(ID);
	}
	
	public static String getStatString(StatID id){
		switch(id){
			case LEVEL:
				return "Level";
			case HP_MAX:
				return "HP";
			case HP_NOW:
				return "Current HP";
			case HP_REGEN:
				return "HP Regeneration";
			default:
				break;
		}
		return null;
	}
}
