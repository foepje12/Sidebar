package main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class ScreenProperties
{
	private static GraphicsDevice gd;
	private static int screenWidth;
	private static int screenHeight;

	static int tileSize = 124;

	public static int getScreenWidth()
	{
		gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		return gd.getDisplayMode().getWidth();
	}

	public static int getScreenHeight()
	{
		gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		return gd.getDisplayMode().getHeight();
	}

	public static int getTileSize()
	{
		return tileSize;
	}

	public static int[] getThirdsOfScreen()
	{
		return getThirdsOfWidth(getScreenWidth());
	}

	public static int[] getThirdsOfWidth(int width)
	{
		int[] screenSizes = new int[3];

		int screenWidth = getScreenWidth();

		int mainSize = Math.round(screenWidth / 3);

		while ((screenWidth - mainSize) % 2 != 0)
		{
			mainSize += 1;
		}

		int sides = ((screenWidth - mainSize) / 2);

		screenSizes[0] = sides;
		screenSizes[1] = mainSize;
		screenSizes[2] = sides;

		return screenSizes;
	}
	
}
