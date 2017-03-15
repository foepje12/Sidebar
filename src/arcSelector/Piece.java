package arcSelector;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Piece
{
	private String[] webUrls;
	private String iconPath;
	private Color color;
	
	public Piece(Color color)
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

	public Image getIconFile()
	{
		ImageIcon icon = new ImageIcon(iconPath);
		return icon.getImage();
	}

	public void setIconPath(String iconPath)
	{
		this.iconPath = iconPath;
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color borderColor)
	{
		this.color = borderColor;
	}
	
	

}
