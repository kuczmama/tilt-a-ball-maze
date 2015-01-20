package com.TWINcoGames.tiltABallMaze;

import com.TWINcoGames.tiltABallMaze.Screens.SplashScreen;
import com.badlogic.gdx.Game;


public class tiltABallMaze extends Game {

	@Override
	public void create () {
		setScreen(new SplashScreen(this));
	}

}
