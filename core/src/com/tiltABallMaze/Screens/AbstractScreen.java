//**********************************************************
// Program: Dot Pop-core
//
// Author : Mark Kuczmarski
//
// Package: Screens
// 
// File   : AbstractScreen.java
//
// Date   : January 15, 2015
//
// Purpose: Abstract class to avoid reusing screen components
//*********************************************************
package com.tiltABallMaze.Screens;

import com.TWINcoGames.Helpers.DrawShapes;
import com.TWINcoGames.Helpers.DrawText;
import com.TWINcoGames.Helpers.ScreenHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * @author Mark
 *
 */
public abstract class AbstractScreen  extends ScreenAdapter {
	protected ScreenHelper screenHelper;
	protected DrawText drawer;
	protected SpriteBatch batcher;
	protected DrawShapes drawShape;
	protected ShapeRenderer shapeRenderer;
	
	
	protected AbstractScreen() {
		Gdx.input.setCatchBackKey(true);
		screenHelper = new ScreenHelper();
		drawer = new DrawText();
		batcher = new SpriteBatch();
		drawShape = new DrawShapes();
		shapeRenderer = new ShapeRenderer();
	}
	
	
	@Override
	public abstract void render(float delta);
}
