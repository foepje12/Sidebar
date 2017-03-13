package sideButton;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import handlers.CategoryHandler;
import handlers.FileHandler;
import main.ScreenProperties;
import main.SideBar;

public class SideButton extends JFrame
{
	private static final long serialVersionUID = 1L;

	public SideButton()
	{
		super();

		int barWidth = 100;
		int barHeight = 300;
		setBounds(ScreenProperties.getScreenWidth() - barWidth,
				(ScreenProperties.getScreenHeight() / 2) - (barHeight / 2), barWidth, barHeight);
		setPreferredSize(new Dimension(barWidth, barHeight));

		String propertyName = "currentCategory";

		FileHandler.WriteToConfig(propertyName, "test");

		if (FileHandler.GetConfigProperty(propertyName) != null)
		{
			String currentCategoryName = FileHandler.GetConfigProperty(propertyName);
			Set<String> barItemNames = CategoryHandler.getBarItemNames(currentCategoryName);

			if (barItemNames != null)
			{
				for (int i = 0; i < barItemNames.size(); i++)
				{
					String[] barArray = (String[]) barItemNames.toArray();
					String[] barItemInfo = CategoryHandler.getBarItemInfo(currentCategoryName, barArray[i]);
					System.out.println(barItemInfo[0]);
					CreateButton(i * 100, barItemInfo[0], barItemInfo[1]);
				}
			}
			else
			{
				SideBar.SwitchToSettingsPanel();
			}
		}
	}
	
	void CreateButton(int height, String webUrl, String iconUrl)
	{
		File file = new File("assets/icons/" + iconUrl);
		Image image;
		if (file.exists())
		{
			try
			{
				image = ImageIO.read(file);
				Image dimg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(dimg);

				JPanel panel = new BarButton(height, webUrl, imageIcon);
				add(panel);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
