package com.molecule.system.util;

import java.util.ArrayList;

public class StatHashBuffer {

	private static int counter = Integer.MIN_VALUE;
//	private static ArrayList<Integer> freeHashes = new ArrayList<Integer>();
	
	public static int getHash(){
//		if(freeHashes.size() > 0){
//			int toReturn = freeHashes.get(0);
//			freeHashes.remove(0);
//			return toReturn;
//		}else{
			counter++;
			return counter;
//		}
	}
	
}
