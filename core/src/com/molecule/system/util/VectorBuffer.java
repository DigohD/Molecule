package com.molecule.system.util;

import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.granule.Granule;

public class VectorBuffer {

	public static LinkedList<Vector2> vectors = new LinkedList<Vector2>();
	
	public VectorBuffer(){
		for(int i = 0; i < 5000; i++)
			vectors.add(new Vector2());
	}
	
	public static Vector2 getVector(){
		if(vectors.size() == 0)
			vectors.add(new Vector2());
		return vectors.pollFirst();
	}
	
	public static void putVector(Vector2 granule){
		vectors.addFirst(granule);
	}
}
