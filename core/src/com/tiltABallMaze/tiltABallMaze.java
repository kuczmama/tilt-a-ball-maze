package com.tiltABallMaze;

import com.badlogic.gdx.Game;
import com.tiltABallMaze.Screens.SplashScreen;


public class tiltABallMaze extends Game {

	@Override
	public void create () {
		setScreen(new SplashScreen(this));
	}

}
