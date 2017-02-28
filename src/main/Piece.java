package main;

import java.awt.Color;

public class Piece
{
	private Color color;
	public String[] webUrls;

	public Piece(Color color)
	{
		this.color = color;

	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public String[] getWebUrls()
	{
		return webUrls;
	}

	public void setWebUrl(String webUrl, int index)
	{
		webUrls[index] = webUrl;
	}

}
