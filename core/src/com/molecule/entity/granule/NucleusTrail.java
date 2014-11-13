package com.molecule.entity.granule;

import java.util.ArrayList;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.molecule.entity.Renderable;
import com.molecule.entity.Tickable;
import com.molecule.entity.molecule.Nucleus;
import com.molecule.entity.particle.Particle;
import com.molecule.system.Game;
import com.molecule.system.Util;

public class NucleusTrail implements Tickable, Renderable{
	private int interval, timer;
	private Nucleus parent;
	private float baseX, baseY;
	
	private LinkedList<Vector2> trails = new LinkedList<Vector2>();
	
	public NucleusTrail(Nucleus parent, int interval, int length){
		this.parent = parent;
		this.interval = interval;
		
		for(int i = 0; i < length; i++)
			trails.add(new Vector2(0, 0));
	}
	
	@Override
	public void tick(float dt) {
		timer++;
		if(timer > interval){
			timer = 0;
			Vector2 v = trails.pollFirst();
			baseX = parent.getCenterX();
			baseY = parent.getCenterY();
			v.set(parent.getCenterX(), parent.getCenterY());
			trails.addLast(v);
		}
	}
	float alpha;
	Color c = new Color(0, 1, 0, alpha);
	@Override
	public void render(SpriteBatch batch) {
		batch.end();
		
		alpha = 0.0f;
		
		Util.shapeRenderer.setProjectionMatrix(Game.getCam().combined);
		Gdx.gl.glEnable(Gdx.graphics.getGL20().GL_BLEND);
		Gdx.gl.glBlendFunc(Gdx.graphics.getGL20().GL_SRC_ALPHA, Gdx.graphics.getGL20().GL_ONE_MINUS_SRC_ALPHA);
		for(int i = 0; i < trails.size(); i++){
		
			if(i == trails.size() - 1){
				Vector2 v = trails.get(i);
				Util.shapeRenderer.begin(ShapeType.Line);
				Util.shapeRenderer.setColor(c);
				Util.shapeRenderer.line(v.x, v.y, baseX, baseY);
				Util.shapeRenderer.end();
			}else{
				Vector2 v = trails.get(i);
				Vector2 v2 = trails.get(i + 1);
				Util.shapeRenderer.begin(ShapeType.Line);
				Util.shapeRenderer.setColor(c);
				Util.shapeRenderer.line(v.x, v.y, v2.x, v2.y);
				Util.shapeRenderer.end();
			}
			alpha = alpha + (1f / ((float) trails.size() * 2));
			
		}
		
		Gdx.gl.glDisable(Gdx.graphics.getGL20().GL_BLEND);
		
		batch.begin();
	}

}
