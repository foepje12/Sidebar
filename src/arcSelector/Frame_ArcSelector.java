package arcSelector;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import main.Constants;
import main.ScreenProperties;

public class Frame_ArcSelector extends JFrame
{
	private static final long serialVersionUID = 1L;

	public Frame_ArcSelector()
	{
		super();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(0, 0, 0, 0));		
		setVisible(true);
		pack();

		int barWidth = Constants.arcRadius;
		int barHeight = barWidth * 2;
		setBounds(ScreenProperties.getScreenWidth() - (barWidth / 2),
				(ScreenProperties.getScreenHeight() / 2) - (barHeight / 2) * 3, barWidth / 2, barHeight);
		setPreferredSize(new Dimension(barWidth / 2, barHeight));

		add(new ArcSelector());

	}
}
