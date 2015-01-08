//**********************************************************
// Program: Dot Pop-core
//
// Author : Mark Kuczmarski
//
// Package: com.TWINcoGames.Helpers
// 
// File   : ScreenHelper.java
//
// Date   : Jun 14, 2014
//
// Purpose: provide generic functions to help drawing screen
//*********************************************************
package com.TWINcoGames.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * @author Mark
 *
 */
public class ScreenHelper {
	//to keep track of where the user touches'
	public Vector3 touchPoint;

	public ScreenHelper(){
		touchPoint = new Vector3();
	}

	/**
	 * Clear the screen with the default background color
	 */
	public void clearScreen(){
		Gdx.gl.glClearColor(Assets.backgroundColor.r,Assets.backgroundColor.g,Assets.backgroundColor.b,Assets.backgroundColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	/**
	 * Set the screen with the given background color
	 * @param color the color to set the screen
	 */
	public void setBackgroundColor(Color color){
		Gdx.gl.glClearColor(color.r,color.g,color.b,color.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	/**
	 * Determines if the mouse is currently in the given rectangle
	 * @param rect the rectangle to check touching
	 * @return true if touching false otherwise
	 */
	public boolean isTouching(Rectangle rect){
		if(Gdx.input.justTouched()){		
			touchPoint.set(Gdx.input.getX(),Gdx.graphics.getHeight() - Gdx.input.getY(), 0);
			return rect.contains(touchPoint.x,touchPoint.y);
		}
		return false;
	}
}
