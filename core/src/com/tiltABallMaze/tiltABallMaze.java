package com.tiltABallMaze;

import com.badlogic.gdx.Game;
import com.tiltABallMaze.Screens.SplashScreen;


public class TiltABallMaze extends Game {

	@Override
	public void create () {
		setScreen(new SplashScreen(this));
	}

}
