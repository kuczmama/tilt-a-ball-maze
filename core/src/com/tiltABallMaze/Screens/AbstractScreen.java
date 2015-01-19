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
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tiltABallMaze.Settings;

/**
 * @author Mark
 *
 */
public abstract class AbstractScreen  extends ScreenAdapter implements Screen{
	protected ScreenHelper screenHelper;
	protected DrawText drawer;
	protected SpriteBatch batcher;
	protected DrawShapes drawShape;
	protected ShapeRenderer shapeRenderer;
	protected Settings settings;
	protected final Stage stage;
	
	protected AbstractScreen() {
		Gdx.input.setCatchBackKey(true);
		screenHelper = new ScreenHelper();
		drawer = new DrawText();
		batcher = new SpriteBatch();
		drawShape = new DrawShapes();
		shapeRenderer = new ShapeRenderer();
		settings = new Settings();
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	 public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void render(float delta){
		screenHelper.clearScreen();
		stage.act(delta);
		stage.draw();
	}
}
