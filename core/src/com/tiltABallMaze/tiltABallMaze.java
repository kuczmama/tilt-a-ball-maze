package com.tiltABallMaze;



import com.TWINcoGames.Helpers.Assets;
import com.TWINcoGames.Helpers.DrawText;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
	private float ballX = 350.0f;
	private float ballY = 400.0f;
	private float screenWidth;
	private float screenHeight;
	private final float speedConstant = 0.5f;
	public static Pixmap level1;
	private float boxSize = 5.0f;
	static Texture level1tex;
	private int R, G,B,A;
	private BitmapFont font;
	private boolean isWhite = false;
	private float prevX, prevY;

	@Override
	public void create () {
		batch = new SpriteBatch();
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		img = new Texture("badlogic.jpg");
		Assets.load();
		load();
		font = new BitmapFont();
		font.setColor(Color.RED);
		convertPixmap(level1);
		prevX = ballX;
		prevY = ballY;
	}

	public static void load(){
		level1 = loadPixmap("levels/squarelevel.png");
	}

	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}

	public static Pixmap loadPixmap (String file) {
		return new Pixmap(Gdx.files.internal(file));
	}


	private boolean isWhite(int value){
		R = ((value & 0xff000000) >>> 24);
		G = ((value & 0x00ff0000) >>> 16);
		B = ((value & 0x0000ff00) >>> 8);
		A = ((value & 0x000000ff));

		return R == 255 && G == 255 && B == 255 && A == 255;
	}
	
	//test the physics
	private void calculateImageLocation(){	
		int deltaY = (int)(-Gdx.input.getAccelerometerX() * speedConstant);
		int deltaX = (int)(Gdx.input.getAccelerometerY() * speedConstant);
		int value = level1.getPixel((int)ballX,(int)screenHeight - (int)ballY);
		
		//for debugging
		R = ((value & 0xff000000) >>> 24);
		G = ((value & 0x00ff0000) >>> 16);
		B = ((value & 0x0000ff00) >>> 8);
		A = ((value & 0x000000ff));
		
		if(!isWhite(level1.getPixel((int)ballX,(int)(screenHeight - ballY)))){
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
		/**********
		 * end check screen bounds
		 */
	}

	private void convertPixmap(Pixmap original){
		Pixmap tmp = new Pixmap((int)screenWidth,(int)screenHeight,Format.RGBA8888);
		tmp.drawPixmap(level1, 0, 0,0,0,(int)screenWidth,(int)screenHeight);
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
		batch.draw(level1tex,0,0);
		batch.draw(img, ballX, ballY,boxSize,boxSize);

		font.draw(batch, R + " " + B + " " + G + " " + A + " " + (int)ballX + " " + (int)ballY + " " + isWhite, 50, 50);

		batch.end();
	}
}
