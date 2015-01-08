//**********************************************************
// Program: Dot Pop-core
//
// Author : Mark Kuczmarski
//
// Package: com.TWINcoGames.Helpers
// 
// File   : DrawShapes.java
//
// Date   : Jun 14, 2014
//
// Purpose:  Draw a given shape
//*********************************************************
package com.TWINcoGames.Helpers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

/**
 * @author Mark
 *
 */
public class DrawShapes {
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batcher;

	public DrawShapes(){
		shapeRenderer = new ShapeRenderer();
		batcher = new SpriteBatch();
	}

	/**
	 * Draw a rectangle to the screen
	 * @param rect the rectangle to be drawn
	 */
	public void drawRectangle(Rectangle rect){
		batcher.begin();
		shapeRenderer.begin(ShapeType.Filled);
		//draw the inside of the bubble
		shapeRenderer.setColor(new Color(255, 0, 0, 1));
		shapeRenderer.rect(rect.x,rect.y,rect.width,rect.height);
		shapeRenderer.end();
		batcher.end();
	}

	public void drawImageWithBounds(Texture image,Rectangle rect){
		batcher.begin();
		batcher.draw(image,rect.x,rect.y,rect.width,rect.height);
		batcher.end();
	}
	
	public void centerImage(Texture image){
		batcher.begin();
		batcher.draw(image,Gdx.graphics.getWidth()/2f - image.getWidth()/2f,
				Gdx.graphics.getHeight()/2f - image.getHeight()/2,
				image.getWidth(),
				image.getHeight());
		batcher.end();
	}
}
