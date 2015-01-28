package com.TWINcoGames.tiltABallMaze.Screens;

import java.util.ArrayList;

import com.TWINcoGames.Helpers.Assets;
import com.TWINcoGames.tiltABallMaze.tiltABallMaze;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

public class MainScreen extends AbstractScreen{
	Rectangle[] levelButtons;
	Rectangle[] unlockedLevelButtons;
	tiltABallMaze game;
	ArrayList<String> levels;
	private Table table;
	private ScrollPane scrollPane;
	
	public MainScreen(tiltABallMaze game) {
		this.game = game;
		table = new Table(skin);
	}
	
	@Override
	public void show() {
		List<String[]> list = new List<String[]>(skin);
		Array<String> data = new Array<String>();
		table.setFillParent(true);
		for(int i = 0; i < Assets.levels.size(); i++){
			data.add("level " + Integer.toString(i + 1));
		}
		list.setItems(data);
		scrollPane = new ScrollPane(list, skin);
		table.add(scrollPane);
		stage.addActor(table);
	}
	/**
	 * Show levels in a list on the screen
	 * with rectangle bounds so that they can be clicked
	 * on and selected
	 */
	private void showLevelsList(){
		List<String[]> list = new List<String[]>(skin);
		Array<String> data = new Array<String>();
		list.setItems(data);
		table.setFillParent(true);
		for(int i = 10; i >= 0; i--){
			data.add("level " + Integer.toString(i + 1));
		}
		scrollPane = new ScrollPane(list, skin);
		table.add(scrollPane);
		stage.addActor(table);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		showLevelsList();
		processLevelClicks();
	}

	/**
	 * Checks to see if a certain level is clicked, if it is
	 * change the view to that level
	 */
	private void processLevelClicks() {
		/*for(int i = 0; i < unlockedLevelButtons.length; i++){
			//drawShape.drawRectangle(unlockedLevelButtons[i]);
			if(screenHelper.isTouching(unlockedLevelButtons[i])){
				game.setScreen(new LevelScreen(Assets.levels.get(Assets.levels.size() - i - 1),game));
			}
		}*/
	}
	
}
