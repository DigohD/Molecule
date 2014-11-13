package com.molecule.system;

import java.util.Random;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Util {

	public static Random rnd = new Random();
	public static ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	/**
	 * Returns a float value between min and max.
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static float getFloat(float min, float max){
		return rnd.nextFloat() * (max - min) + min;
	}
}
