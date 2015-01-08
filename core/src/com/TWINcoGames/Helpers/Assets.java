package com.TWINcoGames.Helpers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static BitmapFont font;

	public static TextureRegion ready;
	public static TextureRegion pause;
	public static TextureRegion pauseMenu;
	public static TextureRegion mainMenu;
	public static Texture items;
	public static TextureRegion gameOver;
	public static Color fontColor;
	public static Color backgroundColor;
	public static Texture rulesImage;
	public static Texture pauseButton;
	public static Sound redDotPop;
	public static Sound greenDotPop;
	public static Texture playButton;
	public static Texture rulesButton;
	public static Texture soundOnButton;
	public static Texture highScoreButton;
	public static Texture soundOffButton;
	public static Texture resumeButton;
	public static Color backgroundColor2;
	public static String AppKey = "2500ecd8e8aa6df96561e83609460690e653912695da18e90c78b9ddc8d67d27";
	public static String SecretKey = "e1db7ff88cdcaf9573d2802adba81b3ccfac89751a6d7eb7573796fef150a1b6";
	
	
	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}
	
	public static void load(){
		rulesImage = loadTexture("data/rulesImageWhite.png");
		items = loadTexture("data/items.png");
		gameOver = new TextureRegion(items, 352, 256, 160, 96);
		//font = new BitmapFont(Gdx.files.internal("data/font.fnt"), Gdx.files.internal("data/font.png"), false);
		font = new BitmapFont(Gdx.files.internal("data/AmericanTypeWriter.fnt"),Gdx.files.internal("data/AmericanTypeWriter.png"),false);
		ready = new TextureRegion(items, 320, 224, 192, 32);
		pause = new TextureRegion(items, 64, 64, 64, 64);
		mainMenu = new TextureRegion(items, 0, 224, 300, 110);
		pauseMenu = new TextureRegion(items, 224, 128, 192, 96);
		//fontColor = new Color(0, 0, 0, 1);
		fontColor = new Color(1f,1f,1f,1f);
		//backgroundColor = new Color(255,255,255,1);
		backgroundColor =  new Color(0.145098039f,0.717647059f,0.917647059f,1f);
		//backgroundColor = new Color(.8f,.8f,.8f,1);
		pauseButton = loadTexture("data/pauseButton.png");
		redDotPop = Gdx.audio.newSound(Gdx.files.internal("data/RedDotPop.mp3"));
		greenDotPop = Gdx.audio.newSound(Gdx.files.internal("data/GreenDotPop.mp3"));
		playButton = loadTexture("data/playButton.png");
		rulesButton = loadTexture("data/rulesButton.png");
		soundOnButton = loadTexture("data/soundOnButton.png");
		highScoreButton = loadTexture("data/highScoreButton.png");
		soundOnButton = loadTexture("data/soundOnButton.png");
		soundOffButton = loadTexture("data/soundOffButton.png");
		resumeButton = loadTexture("data/resumeButton.png");
		backgroundColor2 = new Color(0.145098039f,0.717647059f,0.917647059f,1f);
		
	}
	
}
