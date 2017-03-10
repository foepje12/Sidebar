package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import handlers.CategoryHandler;
import handlers.FileHandler;
import settingsMenu.SettingsPanel;

public class Sidebar extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static int screenHeight;
	private static int screenWidth;
	private static String boxType;
	private static int barHeight;
	private static int barWidth;
	private static JFrame jFrame;
	private JPanel mainPanel;

	public Sidebar()
	{
		super();

		screenWidth = ScreenProperties.getScreenWidth();
		screenHeight = ScreenProperties.getScreenHeight();
		jFrame = this;

		// Create the barButtons
		barWidth = 100;
		barHeight = 300;
		setBounds(screenWidth - barWidth, (screenHeight / 2) - (barHeight / 2), barWidth, barHeight);
		setPreferredSize(new Dimension(barWidth, barHeight));

		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage("assets/programIcon.png");
		setIconImage(img);

		boxType = "BarButtons";

		setAlwaysOnTop(true);
		setUndecorated(true);
		setBackground(new Color(1.0f, 1.0f, 1.0f, 0f));
		setVisible(true);
		pack();

		DecorateFrame();
	}

	void DecorateFrame()
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(0, 0, 0, 0));
		mainPanel.setOpaque(false);
		mainPanel.setBounds(0, 0, barWidth, barHeight);
		mainPanel.setPreferredSize(new Dimension(barWidth, barHeight));
		add(mainPanel);

		switch (boxType)
		{
		case "ArcSelector":
			ArcSelector selector = new ArcSelector();
			mainPanel.add(selector);
			break;
		case "BarButtons":
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
					SwitchToSettingsPanel();
				}
			}
			break;
		case "SettingsPanel":
			SettingsPanel settingsPanel = new SettingsPanel();
			mainPanel.add(settingsPanel);
			break;
		}
	}

	void EmptyFrame()
	{
		mainPanel.removeAll();
		pack();
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
				mainPanel.add(panel);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void SwitchToArcSelector()
	{
		((Sidebar) jFrame).EmptyFrame();
		barWidth = Constants.arcRadius;
		barHeight = barWidth * 2;
		jFrame.setBounds(screenWidth - (barWidth / 2), (screenHeight / 2) - (barHeight / 2) * 3, barWidth / 2,
				barHeight);
		jFrame.setPreferredSize(new Dimension(barWidth / 2, barHeight));

		boxType = "ArcSelector";
		((Sidebar) jFrame).DecorateFrame();
		jFrame.pack();
	}

	public static void SwitchToBarButtons()
	{
		((Sidebar) jFrame).EmptyFrame();
		barWidth = 100;
		barHeight = 300;
		jFrame.setBounds(screenWidth - barWidth, (screenHeight / 2) - (barHeight / 2), barWidth, barHeight);
		jFrame.setPreferredSize(new Dimension(barWidth, barHeight));

		boxType = "BarButtons";
		((Sidebar) jFrame).DecorateFrame();
		jFrame.pack();
	}

	public static void SwitchToSettingsPanel()
	{
		((Sidebar) jFrame).EmptyFrame();
		barWidth = Constants.settingsWidth;
		barHeight = Constants.settingsHeight;
		jFrame.setBounds(screenWidth - barWidth, (screenHeight / 2) - (barHeight / 2) * 3, barWidth / 2, barHeight);
		jFrame.setPreferredSize(new Dimension(barWidth, barHeight));

		boxType = "SettingsPanel";
		((Sidebar) jFrame).DecorateFrame();
		jFrame.pack();
	}

	public static JFrame getJframe()
	{
		return jFrame;
	}

	public static void packJFrame()
	{
		jFrame.repaint();
		jFrame.revalidate();
		jFrame.pack();
	}
}
