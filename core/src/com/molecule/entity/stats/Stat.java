package com.molecule.entity.stats;

import java.util.HashMap;

import com.molecule.entity.stats.StatsSheet.StatID;

public class Stat{

	protected String name;
	protected float base, total;
	protected HashMap<String, Float> addMods = new HashMap<String, Float>();
	protected HashMap<String, Float> mulMods = new HashMap<String, Float>();
	
	protected StatID id;
	
	public Stat(String name, float base){
		this.name = name;
		this.base = base;
	}
	
	public Stat(StatID id, String name, float base){
		this.id = id;
		this.name = name;
		this.base = base;
		calculateTotal();
	}
	
	public void calculateTotal(){
		total = base;
		
		for(String x : addMods.keySet()){
			total = total + addMods.get(x);
		}
		
		float mulTotal = 1f;
		for(String x : mulMods.keySet()){
			mulTotal = mulTotal + mulMods.get(x);
		}
		
		total = total * mulTotal;
	}
	
	public void addAddmod(String ID, float amount){
		addMods.put(ID, amount);
		calculateTotal();
	}
	
	public void addMulmod(String ID, float amount){
		mulMods.put(ID, amount);
		calculateTotal();
	}
	
	public void removeAddmod(String ID){
		addMods.remove(ID);
		calculateTotal();
	}
	
	public void removeMulmod(String ID){
		mulMods.remove(ID);
		calculateTotal();
	}
	
	public void setNewBase(float newBase){
		base = newBase;
		calculateTotal();
	}

	public float getTotal(){
		calculateTotal();
		return total;
	}

	public float getBase() {
		return base;
	}
}
