package com.molecule.system.util;

import java.util.LinkedList;

import com.molecule.entity.granule.Granule;

public class GranuleBuffer {

	public static LinkedList<Granule> granuleBuffer = new LinkedList<Granule>();
	
	public GranuleBuffer(){
		for(int i = 0; i < 5000; i++)
			granuleBuffer.add(new Granule());
	}
	
	public static Granule getGranule(){
		if(granuleBuffer.size() == 0)
			granuleBuffer.add(new Granule());
		return granuleBuffer.pollFirst();
	}
	
	public static void putGranule(Granule granule){
		granuleBuffer.addFirst(granule);
	}
			
	
}
