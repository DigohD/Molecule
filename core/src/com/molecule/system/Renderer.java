package com.molecule.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Renderer {
	
	private static SpriteBatch batch;
	private static ShaderProgram shader;
	
	public Renderer(int width, int height){
		Gdx.gl.glClearColor(0, 0, 0, 0);
		
		batch = new SpriteBatch();
		
		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(Gdx.files.internal("shaders/vertShader.vert").readString(), 
				Gdx.files.internal("shaders/fragShader.frag").readString());
		
		if(!shader.isCompiled())
			 Gdx.app.log("Problem loading shader:", shader.getLog());
		
		
	}
	
	public static void enableCustomShader(){
		batch.setShader(shader);
	}
	
	public void render(){
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	public static void enableWaveEffect(){
		shader.end();
		shader.begin();
		shader.setUniformf("waveEffect", 1.0f);
		shader.end();
		shader.begin();
	}
	
	public static void disableWaveEffect(){
		shader.end();
		shader.begin();
		shader.setUniformf("waveEffect", 0.0f);
		shader.end();
		shader.begin();
	}
	
	public void dispose(){
		batch.dispose();
		shader.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public static ShaderProgram getShader() {
		return shader;
	}

}
