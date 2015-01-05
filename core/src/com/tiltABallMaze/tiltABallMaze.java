package com.tiltABallMaze;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class tiltABallMaze extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private float ballX = 0.0f;
	private float ballY = 0.0f;
	private float screenWidth;
	private float screenHeight;
	private final float speedConstant = 1.5f;

	@Override
	public void create () {
		batch = new SpriteBatch();
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		img = new Texture("badlogic.jpg");
	}

	//test the physics
	private void calculateImageLocation(){		
		ballX += Gdx.input.getAccelerometerY() * speedConstant;
		ballY += -Gdx.input.getAccelerometerX() * speedConstant;
		if(ballX <= 0){
			ballX = 0;
		} else if(ballX >= (screenWidth - img.getWidth())){
			ballX = screenWidth - img.getWidth();
		}
		if(ballY <= 0 ){
			ballY = 0;
		} else if(ballY >= (screenHeight - img.getHeight())){
			ballY = screenHeight - img.getHeight();
		}
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		calculateImageLocation();
		batch.begin();
		batch.draw(img, ballX, ballY);
		batch.end();
	}
}
