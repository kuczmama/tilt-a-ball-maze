package com.tiltABallMaze.Screens;

import java.util.ArrayList;

import com.TWINcoGames.Helpers.Assets;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.tiltABallMaze.TiltABallMaze;

public class MainScreen extends AbstractScreen{
	Rectangle[] levelButtons;
	Rectangle[] unlockedLevelButtons;
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
		Color unlocked = new Color(0.0f, 0.0f, 0.0f, 1.0f);
		Color locked = new Color(.5f,.5f,.5f,1.0f);
		String[] levelsArr = new String[Assets.levels.size()];
		String[] levelsList = new String[levelsArr.length];
		String[] unlockedLevelList = new String[levelsArr.length];
		//make everything gray first
		for(int i = levelsArr.length - 1; i >= 0; i--){
			levelsList[i] = "level " + Integer.toString(i + 1); 
		}
		//set some of the levels to be unlocked up to the highest level
		for(int i = levelsArr.length - 1; i >= 0; i--){
			if(i >= settings.getHighestUnlockedLevel()){
				unlockedLevelList[i] = ""; 
			} else {
				unlockedLevelList[i] = "level " + Integer.toString(i + 1);
			}
		}
		levelButtons = drawer.drawCenteredListWithBounds(Assets.font, levelsList, locked);
		unlockedLevelButtons = drawer.drawCenteredListWithBounds(Assets.font, unlockedLevelList, unlocked);
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
		
		for(int i = 0; i < unlockedLevelButtons.length; i++){
			//drawShape.drawRectangle(unlockedLevelButtons[i]);
			if(screenHelper.isTouching(unlockedLevelButtons[i])){
				game.setScreen(new LevelScreen(Assets.levels.get(Assets.levels.size() - i - 1),game));
			}
		}
	}
	
}
