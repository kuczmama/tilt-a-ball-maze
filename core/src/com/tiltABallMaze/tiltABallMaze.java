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
	private float ballX = 50.0f;
	private float ballY = 50.0f;
	private float screenWidth;
	private float screenHeight;
	private final float speedConstant = 0.5f;
	public static Pixmap level1;
	private float boxSize = 20.0f;
	static Texture level1tex;
	private DrawText drawer;
	private int R, G,B,A;
	private boolean desktop = false;
	private BitmapFont font;
	private Pixmap pixmap;
	private static Texture test;

	@Override
	public void create () {
		batch = new SpriteBatch();
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		img = new Texture("badlogic.jpg");
		drawer = new DrawText();
		Assets.load();
		load();
		font = new BitmapFont();
		font.setColor(Color.RED);
		convertPixmap(level1);
		//pixmap = createPro

	}

	public static void load(){
		level1 = loadPixmap("levels/testlevel.bmp");
		test = new Texture(level1);
		//level1tex = loadTexture("levels/testlevel.bmp");
	}

	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}

	public static Pixmap loadPixmap (String file) {
		return new Pixmap(Gdx.files.internal(file));
	}



	private boolean isWhite(int value){
		//int value = pixmap.getPixel(x, y);
		int min = 230;
		R = ((value & 0xff000000) >>> 24);
		G = ((value & 0x00ff0000) >>> 16);
		B = ((value & 0x0000ff00) >>> 8);
		A = ((value & 0x000000ff));

		return R <= 255 && R > min  && G <= 255 && G > min && B <= 255 && B > min && A <= 255 && A > min;
	}


	//test the physics
	private void calculateImageLocation(){	
		float tmpX = ballX;
		float tmpY = ballY;
		float deltaY = -Gdx.input.getAccelerometerX() * speedConstant;
		float deltaX = Gdx.input.getAccelerometerY() * speedConstant;
		int value = level1.getPixel((int)ballX,(int)screenHeight - (int)ballY);
		R = ((value & 0xff000000) >>> 24);
		G = ((value & 0x00ff0000) >>> 16);
		B = ((value & 0x0000ff00) >>> 8);
		A = ((value & 0x000000ff));
		ballX += deltaY;
		ballY += deltaX;
		//int pixel = level1.getPixel((int)ballX,(int)ballY);
		if(isWhite(value)){
			ballX += deltaX;
			ballY += deltaY;
		}
		if(!isWhite(level1.getPixel((int)ballX + (int)deltaX,(int)screenHeight - (int)ballY + (int)deltaY))) {
			ballX = tmpX;
			ballY = tmpY;
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
	}

	private void convertPixmap(Pixmap original){
		Pixmap tmp = new Pixmap((int)screenWidth,(int)screenHeight,Format.RGBA8888);
		//tmp.drawPixmap(level1,0,0);
		tmp.drawPixmap(level1, 0, 0,0,0,(int)screenWidth,(int)screenHeight);
		level1.dispose();
		level1 = tmp;
		//level1tex = new Texture(level1);
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
		//level1tex.draw(level1,0,0);
		batch.draw(img, ballX, ballY,boxSize,boxSize);

		font.draw(batch, R + " " + B + " " + G + " " + A + " " + (int)ballX + " " + (int)ballY, 50, 50);

		batch.end();
	}
}
