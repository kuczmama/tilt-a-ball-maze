package com.TWINcoGames.tiltABallMaze.Screens;

import java.util.ArrayList;

import com.TWINcoGames.Helpers.Assets;
import com.TWINcoGames.tiltABallMaze.tiltABallMaze;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;


public class MainScreen extends AbstractScreen{
	Rectangle[] levelButtons;
	Rectangle[] unlockedLevelButtons;
	tiltABallMaze game;
	ArrayList<String> levels;
	private Table table;
	private ScrollPane scrollPane;
	private TextButton playButton;

	public MainScreen(tiltABallMaze game) {
		this.game = game;
		table = new Table(skin);
		playButton = new TextButton("Play", skin);
	}

	/**
	 * Show all of the levels to the screen in a scrollable list
	 */
	@Override
	public void show() {
		final List<String[]> list = new List<String[]>(skin);
		Array<String> data = new Array<String>();
		table.setFillParent(true);
		//show unlocked levels to the screen
		for(int i = 0; i < settings.getHighestUnlockedLevel() && i < Assets.levels.size(); i++){
			data.add("level " + Integer.toString(i + 1));
		}
		list.setItems(data);
		//Go to the level when desired
		playButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game)Gdx.app.getApplicationListener()).setScreen(
						new LevelScreen(Assets.levels.get(list.getSelectedIndex()),game));
			}
		});
		//format the table
		scrollPane = new ScrollPane(list, skin);
		table.add(scrollPane).expandX().left().expandY();
		table.add(playButton).left().expandX();
		stage.addActor(table);
	}
}
