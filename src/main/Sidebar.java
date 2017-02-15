package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Sidebar extends JFrame
{
	private static final long serialVersionUID = 1L;
	int barWidth = 100;
	int barHeight = 300;
	JPanel mainPanel;

	public Sidebar()
	{
		super();

		int screenWidth = ScreenProperties.getScreenWidth();
		int screenHeight = ScreenProperties.getScreenHeight();

		setBounds(screenWidth - barWidth, (screenHeight / 2) - (barHeight / 2), (int) (barWidth * 1.5), barHeight);
		setPreferredSize(new Dimension((int) (barWidth * 1.5), barHeight));
		setAlwaysOnTop(true);

		DecorateFrame();

		setUndecorated(true);
		setBackground(new Color(1.0f, 1.0f, 1.0f, 0f));
		setVisible(true);
		pack();
	}

	void DecorateFrame()
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(0, 0, 0));
		mainPanel.setOpaque(false);
		mainPanel.setBounds(0, 0, (int) (barWidth * 1.5), barHeight);
		mainPanel.setPreferredSize(new Dimension((int) (barWidth * 1.5), barHeight));
		add(mainPanel);

		CreateButton(Color.ORANGE, 0, "https://startpagina.windesheim.nl/default.aspx", "windesheim.png");
		CreateButton(Color.BLUE, 100, "https://github.com/", "github.png");
		CreateButton(Color.RED, 200, "https://trello.com/", "trello.png");
	}

	void CreateButton(Color color, int height, String webUrl, String iconUrl)
	{
		File file = new File("assets/icons/" + iconUrl);
		Image image;
		try
		{
			if (file.exists())
			{
				image = ImageIO.read(file);
				Image dimg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(dimg);

				JButton panel = new BarButton(color, height, webUrl, imageIcon);
				mainPanel.add(panel);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
