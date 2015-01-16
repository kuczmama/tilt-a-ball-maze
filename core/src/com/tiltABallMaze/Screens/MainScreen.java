package com.tiltABallMaze.Screens;

import java.util.ArrayList;

import com.TWINcoGames.Helpers.Assets;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.tiltABallMaze.TiltABallMaze;

public class MainScreen extends AbstractScreen{
	Rectangle[] levelButtons;
	TiltABallMaze game;
	ArrayList<String> levels;
	
	public MainScreen(TiltABallMaze game) {
		this.game = game;
	}
	
	/**
	 * Show levels in a list on the screen
	 * with rectangle bounds so that they can be clicked
	 * on and selected
	 */
	private void showLevelsList(){
		
		Color c = new Color(0.0f, 0.0f, 0.0f, 1.0f);
		String[] levelsArr = new String[Assets.levels.size()];
		String[] levelsList = new String[levelsArr.length];
		for(int i = levelsArr.length - 1; i >= 0; i--){
			levelsList[i] = "level " + Integer.toString(i + 1); 
		}
		levelButtons = drawer.drawCenteredListWithBounds(Assets.font, levelsList, c);
	}

	@Override
	public void render(float delta) {
		screenHelper.clearScreen();
		showLevelsList();
		processLevelClicks();	
	}

	/**
	 * Checks to see if a certain level is clicked, if it is
	 * change the view to that level
	 */
	private void processLevelClicks() {
		for(int i = 0; i < levelButtons.length; i++){
			if(screenHelper.isTouching(levelButtons[i])){
				game.setScreen(new LevelScreen(Assets.levels.get(Assets.levels.size() - i - 1),game));
			}
		}
	}
	
}
