package com.tiltABallMaze;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Settings {
	private int highestUnlockedLevel = 1;
	private final static String file = "settings.txt";

	public int getHighestUnlockedLevel() {
		load();
		return highestUnlockedLevel;
	}

	public void setHighestUnlockedLevel(int highestUnlockedLevel) {
		this.highestUnlockedLevel = highestUnlockedLevel;
		save();
	}

	/**
	 * Load the setting from file
	 */
	public void load () {
		try {
			FileHandle filehandle = Gdx.files.local(file);

			String[] strings = filehandle.readString().split("\n");
			highestUnlockedLevel = Integer.parseInt(strings[0]);

			/*for (int i = 0; i < 5; i++) {
				highscores[i] = Integer.parseInt(strings[i+1]);
			}
			 */
		} catch (Throwable e) {
			// :( It's ok we have defaults

		}
	}

	/**
	 * Write the settings to a file
	 */
	public void save () {
		try {
			FileHandle filehandle = Gdx.files.local(file);
			filehandle.writeString(Integer.toString(highestUnlockedLevel), false);
			/*filehandle.writeString("<highestUnlockedLevel>" + 
					Integer.toString(highestUnlockedLevel) +
					"</highestUnlockedLevel>\n",false);*/
			/*filehandle.writeString(Boolean.toString(soundEnabled)+"\n", false);
			for (int i = 0; i < 5; i++) {
				filehandle.writeString(Integer.toString(highscores[i])+"\n", true);
			}*/
			System.out.println("File created");
		} catch (Throwable e) {
			System.err.println("Error creating file ");
			e.printStackTrace();
		}
	}

}
