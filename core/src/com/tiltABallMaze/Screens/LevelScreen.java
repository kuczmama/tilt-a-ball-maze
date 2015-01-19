//**********************************************************
// Program: Tilt A ball maze
//
// Author : Mark Kuczmarski
//
// Package: com.tiltABallMaze
// 
// File   : Level.java
//
// Date   : Jan 15, 2015
//
// Purpose: make it trivial to add a level to the game
//
//
// Note   : Colors are very important in this game
//          White - Where the ball is allowed to go
//          Black - Where the ball is NOT allowed to go
//          Blue  - The ball's starting position
//          Green - The ball's ending position
//          Red   - A hazard to avoid
//*********************************************************

package com.tiltABallMaze.Screens;
import com.TWINcoGames.Helpers.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.tiltABallMaze.TiltABallMaze;

public class LevelScreen extends AbstractScreen{
	private boolean debug = true;
	SpriteBatch batch;
	Texture img;
	private int ballX = 770;
	private int ballY = 250;
	private int screenWidth;
	private int screenHeight;
	private final int speedConstant = 1;
	public static Pixmap level1;
	private final int boxSize = 5;
	static Texture level1tex;
	private int R, G,B,A;
	private BitmapFont font;
	private boolean isWhite = true;
	private int prevX, prevY;
	private TiltABallMaze game;
	private String levelmap;

	
	public LevelScreen(String levelmap,TiltABallMaze game){
		this.game = game;
		this.levelmap = levelmap;
		batch = new SpriteBatch();
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		img = new Texture("badlogic.jpg");
		level1 = loadPixmap(levelmap);
		font = new BitmapFont();
		font.setColor(Color.RED);
		convertPixmap();
		try {
			findStartPosition();
		} catch (Exception e) {
			e.printStackTrace();
		}
		prevX = ballX;
		prevY = ballY;
	}

	public static Pixmap loadPixmap (String file) {
		return new Pixmap(Gdx.files.internal(file));
	}

	/**
	 * set the initial start position at the first blue value, by setting the start
	 * position set set the  ballX, ballY equal to the start position
	 * @throws Exception can't find start position Exception
	 */
	private void findStartPosition() throws Exception{
		for(int i = 0; i < level1.getWidth(); i++){
			for(int j = 0; j < level1.getHeight(); j++){
				int value = level1.getPixel(i, screenHeight - j);
				if(isColor(value,0,0,255,255)){
					ballX = i;
					ballY = j;
					return;
				}
			}
		}
		throw new Exception("Cannot find start position");
	}

	/**
	 * Determine if a certain value is a given color
	 * @param value 
	 * @param R
	 * @param G
	 * @param B
	 * @param A
	 * @return a boolean value seeing if the value contains the given color
	 */
	private boolean isColor(int value, int _R, int _G, int _B, int _A){
		R = ((value & 0xff000000) >>> 24);
		G = ((value & 0x00ff0000) >>> 16);
		B = ((value & 0x0000ff00) >>> 8);
		A = ((value & 0x000000ff));
		return _R == R && _G == G && _B == B && _A == A;
	}


	//test the physics
	private void calculateImageLocation(){	

		int deltaY =  (int) (-Gdx.input.getAccelerometerX() * speedConstant);
		int deltaX =  (int) (Gdx.input.getAccelerometerY() * speedConstant);
		if(debug){
			if(Gdx.input.isKeyPressed(Keys.UP)){
				deltaY += 2;
			}
			if(Gdx.input.isKeyPressed(Keys.DOWN)){
				deltaY -= 2;
			}
			if(Gdx.input.isKeyPressed(Keys.LEFT)){
				deltaX -= 2;
			}
			if(Gdx.input.isKeyPressed(Keys.RIGHT)){
				deltaX  += 2;
			}
		}

		if(isColor(level1.getPixel(ballX, screenHeight - ballY),0,0,0,255)){
			isWhite = false;
			ballX = prevX;
			ballY = prevY;
		} else {
			isWhite = true;
			prevX = ballX;
			prevY = ballY;
			ballX += deltaX;
			ballY += deltaY;
		}
		/******
		 * Check screen bounds
		 */
		if(ballX <= 0){
			ballX = 0;
		} else if(ballX >= (screenWidth - boxSize)){
			ballX = screenWidth - boxSize;
		}
		if(ballY <= 0 ){
			ballY = 0;
		} else if(ballY >= (screenHeight - boxSize)){
			ballY = screenHeight - boxSize;
		}
	}

	private void handleFallInHole() throws Exception{
		if(isColor(level1.getPixel(ballX, screenHeight - ballY),255,0,0,255)){
			findStartPosition();
		}
	}

	private void convertPixmap(){
		Pixmap tmp = new Pixmap(screenWidth,screenHeight,Format.RGBA8888);
		tmp.drawPixmap(level1, 0, 0, level1.getWidth(), level1.getHeight(), 0, 0, screenWidth, screenHeight);
		level1.dispose();
		level1 = tmp;
		level1tex = new Texture(level1);
		level1tex.draw(level1, 0, 0);
	}

	private void drawBackButton(){
		Color color = new Color(1.0f,1.0f,1.0f,1.0f);
		Rectangle backBounds = drawer.drawTextUpperLeft("Back",color);
		if(screenHelper.isTouching(backBounds) || Gdx.input.isKeyPressed(Keys.BACK)){
			game.setScreen(new MainScreen(game));
		}
	}

	/**
	 * Check the current location to 
	 *  - see if it is green this means game is won
	 *  - update settings for available levels
	 *  - display a message to the user
	 *  - show time
	 *  - set screen to main menu
	 */
	private void checkForWin(){
		//if win actually happens
		if(isColor(level1.getPixel(ballX, screenHeight - ballY),0,255,0,255)){
			drawer.drawCenteredText("Level Completed!", Assets.fontColor, 1.0f);
			incrementHighestLevelIfNeeeded();
			game.setScreen(new MainScreen(game));
		}
	}
	
	/**
	 * Sets the current highest level in the settings, it finds what the current
	 * level is and then when it finally finds it, if the current level is greater than
	 * or equal to the highest level in the settings, it will increment the highest level
	 * to this level + 1
	 */
	private void incrementHighestLevelIfNeeeded(){
		int currLevel = 1;
		for(String level : Assets.levels){
			if(level.equals(levelmap)){
				if((currLevel) >= settings.getHighestUnlockedLevel()){
					settings.setHighestUnlockedLevel(1 + currLevel);
					return;
				}
				return;
			}
			currLevel++;
		}

	}

	@Override
	public void render (float delta) {
		super.render(delta);
		calculateImageLocation();
		try{
			handleFallInHole();
		} catch (Exception e){
			e.printStackTrace();
		}
		batch.begin();
		batch.draw(level1tex,0,0,screenWidth,screenHeight);
		batch.draw(img, ballX, ballY,boxSize,boxSize);
		font.draw(batch, R + " " + G + " " + B + " " + A + " " + ballX + " " + ballY + " " + isWhite, 50, 50);
		batch.end();
		drawBackButton();
		checkForWin();

	}
}
