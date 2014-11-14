package com.molecule.system;

import com.badlogic.gdx.Screen;
import com.molecule.system.states.GameState;
import com.molecule.system.util.LinkedStack;
import com.molecule.system.util.Node;

public class GameStateManager {
	
private LinkedStack<GameState> states;
	
	public GameStateManager(){
		states = new LinkedStack<GameState>();
	}

	/**
	 * Adds a state at the top of the state stack
	 * @param state the game state that will be added to the top of the state stack
	 */
	public void push(GameState state){
		states.push(state);
	}
	
	/**
	 * Deletes the state at the top of the state stack
	 */
	public void pop(){
		states.pop();
	}
	
	public GameState next(){
		return states.top.next.data;
	}
	
	/**
	 * Returns the current state
	 * @return
	 */
	public GameState peek(){
		return states.peek();
	}
	
	public void tick(float dt){
		tick(states.top, dt);
	}
	
	private void tick(Node<GameState> state, float dt){
		state.data.tick(dt);
		if(!state.data.isTickingBlocked())
			tick(state.next, dt);
	}
	
	public void render(Renderer renderer){
		render(states.top, renderer);
	}
	
	private void render(Node<GameState> state, Renderer renderer){
		if(!state.data.isRenderingBlocked())
			render(state.next, renderer);
		state.data.render(renderer);
	}

}
