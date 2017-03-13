package arcSelector;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Piece
{
	public String[] webUrls;
	public String iconPath;
	
	public Piece(String iconPath)
	{
		this.iconPath = iconPath;
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

}
