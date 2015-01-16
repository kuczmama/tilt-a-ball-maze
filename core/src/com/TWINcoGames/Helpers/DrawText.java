//**********************************************************
// Program: Dot Pop-core
//
// Author : Mark Kuczmarski
//
// Package: com.TWINcoGames.Helpers
// 
// File   : DrawText.java
//
// Date   : Jun 14, 2014
//
// Purpose: Draw text to the screen
//*********************************************************
package com.TWINcoGames.Helpers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * @author Mark
 *
 */
public class DrawText {
	public SpriteBatch batcher;

	public DrawText() {
		batcher = new SpriteBatch();
	}

	/**
	 * Method needed to start drawing text using default values
	 */
	private void beginDefaultDrawing() {
		batcher.begin();
		//Assets.font.setColor(Assets.backgroundColor2);
	}

	public void drawCenteredList(BitmapFont font, String[] messages, Color color) {
		if (messages.length <= 0) {
			System.err.println("String[] must be a positive length");
		}
		// the size of all of the messages
		float space = font.getBounds((String) "Test").height * 1.5f;
		float yPos = Gdx.graphics.getHeight() / 2
				+ font.getBounds((String) "test").height / 2 - space
				* messages.length / 2f;
		batcher.begin();
		font.setColor(color);
		for (String str : messages) {
			font.draw(batcher, str,
					Gdx.graphics.getWidth() / 2
							- font.getBounds((String) str).width / 2, yPos);
			yPos += space;
		}
		batcher.end();
	}
	
	/**
	 * Draw a centered list, which returns an array of rectangles where 
	 * each rectangle is the bounds of the list
	 * @param font
	 * @param messages
	 * @param color
	 * @return
	 */
	public Rectangle[] drawCenteredListWithBounds(BitmapFont font, String[] messages, Color color) {
		if (messages.length <= 0) {
			System.err.println("String[] must be a positive length");
		}
		ArrayList<Rectangle> result = new ArrayList<Rectangle>();
		// the size of all of the messages
		float space = font.getBounds((String) "Test").height * 1.5f;
		float yPos = Gdx.graphics.getHeight() / 2
				+ font.getBounds((String) "test").height / 2 - space
				* messages.length / 2f;
		batcher.begin();
		font.setColor(color);
		for (int i = messages.length -1; i >= 0; i--) {
			font.draw(batcher, messages[i],
					Gdx.graphics.getWidth() / 2
							- font.getBounds((String) messages[i]).width / 2, yPos);
			result.add(new Rectangle(Gdx.graphics.getWidth() / 2
					- font.getBounds((String) messages[i]).width / 2, yPos - Assets.font.getBounds(messages[i]).height,
					Assets.font.getBounds(messages[i]).width,
					Assets.font.getBounds(messages[i]).height));
			yPos += space;
			
		}
		batcher.end();
		Rectangle[] rects = new Rectangle[result.size()];
		return result.toArray(rects);
	}

	/**
	 * Draw the string arr centered on the string
	 * 
	 * @param objects
	 *            the array of strings to be drawn centered
	 * @param font
	 *            the type of font to be used
	 */
	public void drawCenteredText(BitmapFont font, Object[] objects, Color color) {
		float spacing = font.getBounds("test").height * 2;
		float space = -objects.length * spacing;
		// center the text
		batcher.begin();
		font.setColor(color);
		for (Object message : objects) {
			font.draw(
					batcher,
					(String) message,
					Gdx.graphics.getWidth() / 2
							- font.getBounds((String) message).width / 2,
					Gdx.graphics.getWidth() / 2 + 2
							* font.getBounds((String) message).height / 2
							+ space);
			space += spacing;
		}
		batcher.end();
	}// end drawCenteredText

	/**
	 * Draw the string str with the default game font and the default color
	 * found in the assets file
	 * 
	 * @param str
	 *            the string to be drawn centered
	 */
	public void drawCenteredText(String str) {
		beginDefaultDrawing();
		Assets.font.draw(batcher, str, Gdx.graphics.getWidth() / 2
				- Assets.font.getBounds(str).width / 2,
				Gdx.graphics.getHeight() / 2
						+ Assets.font.getBounds(str).height / 2);
		batcher.end();
		/*return new Rectangle(Gdx.graphics.getWidth() / 2
				- Assets.font.getBounds(str).width / 2,
				Gdx.graphics.getHeight() / 2
						+ Assets.font.getBounds(str).height / 2,
				Assets.font.getBounds(str).width,
				Assets.font.getBounds(str).height);*/
	}

	/**
	 * Draw centered text with a default font and a custom color
	 * 
	 * @param str
	 *            the string to be drawn
	 * @param color
	 *            the custom color
	 * @param scale
	 *            the scale of the font
	 * @return The bounds of the centered text
	 */
	public Rectangle drawCenteredText(String str, Color color, float scale) {
		batcher.begin();
		Assets.font.setColor(color);
		Assets.font.setScale(1);
		Assets.font.draw(batcher, str, Gdx.graphics.getWidth() / 2
				- Assets.font.getBounds(str).width / 2,
				Gdx.graphics.getHeight() / 2
						+ Assets.font.getBounds(str).height / 2);
		batcher.end();
		return new Rectangle(Gdx.graphics.getWidth() / 2
				- Assets.font.getBounds(str).width / 2,
				Gdx.graphics.getHeight() / 2
						+ Assets.font.getBounds(str).height / 2,
				Assets.font.getBounds(str).width,
				Assets.font.getBounds(str).height);
	}

	/**
	 * Draw text to the screen in the position x,y using the default text values
	 * 
	 * @param str
	 *            the string to be drawn
	 * @param x
	 *            the x position
	 * @param y
	 *            the y position
	 * @return the Rectangle containing the bounds of the text
	 */
	public Rectangle drawText(String str, float x, float y) {
		beginDefaultDrawing();
		Assets.font.draw(batcher, str, x, y);
		batcher.end();
		return new Rectangle(x, y - Assets.font.getBounds(str).height,
				Assets.font.getBounds(str).width,
				Assets.font.getBounds(str).height);
	}

	/**
	 * Draw string str to the screen
	 * 
	 * @param str
	 *            the string to be drawn
	 * @return A rectangle with the bounds of the text
	 */
	public Rectangle drawTextUpperLeft(String str) {
		beginDefaultDrawing();
		float x = Assets.font.getBounds(str).height;
		float space = Assets.font.getSpaceWidth();
		float y = Gdx.graphics.getHeight() - space;

		Assets.font.draw(batcher, str, x, y);

		batcher.end();
		return new Rectangle(x, y - Assets.font.getBounds(str).height,
				Assets.font.getBounds(str).width,
				Assets.font.getBounds(str).height);
	}
	
	/**
	 * Draw text in the upper left hand corner
	 * @param str the text to draw
	 * @param color the color of the text
	 * @return the Rectangle of the bounds of the text
	 */
	public Rectangle drawTextUpperLeft(String str,Color color) {
		batcher.begin();
		Assets.font.setColor(color);
		float x = Assets.font.getBounds(str).height;
		float space = Assets.font.getSpaceWidth();
		float y = Gdx.graphics.getHeight() - space;

		Assets.font.draw(batcher, str, x, y);

		batcher.end();
		return new Rectangle(x, y - Assets.font.getBounds(str).height,
				Assets.font.getBounds(str).width,
				Assets.font.getBounds(str).height);
	}

	/**
	 * Draw text in the upper left hand corner
	 * 
	 * @param arr
	 *            the array of text strings to be drawn
	 * @param font
	 *            the font to use
	 */
	public void drawTextUpperLeft(BitmapFont font, ArrayList<String> arr,
			Color color) {
		batcher.begin();
		font.setColor(color);
		float spacing = font.getSpaceWidth() + font.getBounds("test").height;
		float space = font.getSpaceWidth();
		for (String message : arr) {
			font.draw(batcher, message, font.getBounds("test").height,
					Gdx.graphics.getHeight() - space);
			space += spacing;
		}
		batcher.end();
	}
}
