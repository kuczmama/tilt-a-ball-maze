

//**********************************************************
//Program: Dot Pop-core
//
//Author : Mark Kuczmarski
//
//Package: com.TWINcoGames.Screens
//
//File   : SplashScreen.java
//
//Date   : Jun 19, 2014
//
//Purpose: The splash screen to display the logo
//*********************************************************

package com.TWINcoGames.tiltABallMaze.Screens;
import com.TWINcoGames.Helpers.Assets;
import com.TWINcoGames.tiltABallMaze.tiltABallMaze;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.utils.TimeUtils;


/**
 * @author Mark
 *
 */
public class SplashScreen extends AbstractScreen{
	private tiltABallMaze game;
	private Texture logo;
	private long time;

	public SplashScreen(tiltABallMaze game){
		this.game = game;
		Assets.load();
		//Settings.load();
		time = TimeUtils.millis();
		logo = new Texture(Gdx.files.internal("data/twinco_logo.png"));
		logo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
	
	private void drawLogo(){
		float width = Gdx.graphics.getWidth() ;
		float height = Gdx.graphics.getHeight();
		//batcher.disableBlending();
		batcher.begin();
		//change the perspective based on lanscape or portrait mode
		if(width < height){
			batcher.draw(logo,0,height/2f - width/2f,width,width);
		} else {
			batcher.draw(logo,width/2f - height/2f,0,height,height);
		}
		batcher.end();
	}

	/**
	 * Make a delay for the logo to be displayed on the splash
	 * screen for one second
	 */
	private void makeDelay(float delta){
			drawLogo();
			if( (int)((TimeUtils.millis() - time)) >= 2000 || Gdx.input.isTouched()){
				game.setScreen(new MainScreen(game));
			}
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		screenHelper.setBackgroundColor(Color.WHITE);
		makeDelay(delta);
	}

	@Override
	public void dispose(){
		super.dispose();
		logo.dispose();
	}
}
