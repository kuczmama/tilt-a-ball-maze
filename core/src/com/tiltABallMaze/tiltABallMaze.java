package com.tiltABallMaze;



import com.TWINcoGames.Helpers.Assets;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class tiltABallMaze extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private int ballX;
	private int ballY;
	private int screenWidth;
	private int screenHeight;
	private final int speedConstant = 1;
	public static Pixmap level1;
	private final int boxSize = 5;
	static Texture level1tex;
	private int R, G,B,A;
	private BitmapFont font;
	private boolean isWhite = false;
	private int prevX, prevY;

	@Override
	public void create () {
		batch = new SpriteBatch();
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		img = new Texture("badlogic.jpg");
		Assets.load();
		level1 = loadPixmap("levels/squarelevel.png");
		font = new BitmapFont();
		font.setColor(Color.RED);
		convertPixmap();
		try {
			findStartPosition();
		} catch (Exception e) {
			// unable to find start position
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
				int value = level1.getPixel(i, j);
				if(isColor(value,0,0,255,255,240)){
					ballX = i;
					ballY = j;
					return;
				}
			}
		}
		throw new Exception("Cannot find start position");
	}

	/**
	 * Determine if the given value is the desired color
	 * @param value the value to check
	 * @param R desired red value
	 * @param G desired green value
	 * @param B desired blue value
	 * @param A desired alpha value
	 * @param min desired range of min value
	 * @return boolean value determining if value is wanted color
	 */
	private boolean isColor(int value, int R, int G, int B, int A, int min){
		int _R = ((value & 0xff000000) >>> 24);
		int _G = ((value & 0x00ff0000) >>> 16);
		int _B = ((value & 0x0000ff00) >>> 8);
		int _A = ((value & 0x000000ff));
		return (_R <= R && _R > min || R == 0) && 
				(_G <= G && _G > min || G == 0) &&
				(_B <= B && _B > min || B == 0) &&
				(_A <= A && _A > min || A == 0);
	}

	private boolean isWhite(int value){
		int min = 240;
		R = ((value & 0xff000000) >>> 24);
		G = ((value & 0x00ff0000) >>> 16);
		B = ((value & 0x0000ff00) >>> 8);
		A = ((value & 0x000000ff));

		return R <= 255 && R > min && G <= 255 && G > min &&  B <= 255 && B > min &&  A <= 255 && A > min;
	}

	//test the physics
	private void calculateImageLocation(){	
		int deltaY =  (int) (-Gdx.input.getAccelerometerX() * speedConstant);
		int deltaX =  (int) (Gdx.input.getAccelerometerY() * speedConstant);
		if(!isWhite(level1.getPixel(ballX,screenHeight - ballY))){
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

	private void convertPixmap(){
		Pixmap tmp = new Pixmap(screenWidth,screenHeight,Format.RGBA8888);
		tmp.drawPixmap(level1, 0, 0, level1.getWidth(), level1.getHeight(), 0, 0, screenWidth, screenHeight);
		level1.dispose();
		level1 = tmp;
		level1tex = new Texture(level1);
		level1tex.draw(level1, 0, 0);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		calculateImageLocation();
		batch.begin();
		batch.draw(level1tex,0,0,screenWidth,screenHeight);
		batch.draw(img, ballX, ballY,boxSize,boxSize);
		font.draw(batch, R + " " + B + " " + G + " " + A + " " + ballX + " " + ballY + " " + isWhite, 50, 50);
		batch.end();
	}
}
