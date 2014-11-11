package com.molecule.system.util;

import com.badlogic.gdx.math.Vector2;

public class PhysicsUtil {

	/**
	 * Method used to simulate acceleration. You set a target velocity and then
	 * you pass in the current velocity and then the method will increment the
	 * current velocity every frame with dt until it reaches the target.
	 * 
	 * @param target
	 *            the target velocity
	 * @param current
	 *            the current velocity
	 * @param dt
	 *            the timestep
	 * @return
	 */
	public static float approach(float target, float current, float dt) {
		float diff = target - current;
		if (diff > dt)
			return current + dt;
		if (diff < -dt)
			return current - dt;
		return target;
	}
	
	public static void move(Vector2 position, Vector2 vel, float dt){
		position = position.mulAdd(vel, dt);
	}
}
