package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class SettingsPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	int mainWidth;
	int mainHeight;
	int topWidth;
	int topHeight;
	int leftWidth;
	int leftHeight;

	public SettingsPanel()
	{
		super();

		mainWidth = Constants.settingsMainWidth;
		mainHeight = Constants.settingsMainHeight;

		setBounds(0, 0, Constants.settingsWidth, Constants.settingsHeight);
		setPreferredSize(new Dimension(Constants.settingsWidth, Constants.settingsHeight));
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout());

		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent event)
			{
				if (SwingUtilities.isMiddleMouseButton(event))
				{
					Sidebar.SwitchToArcSelector();
				}
				
				if(SwingUtilities.isLeftMouseButton(event))
				{			
				}
			}
		});

		addScrollPane();

	}

	private void addScrollPane()
	{
		
		JList list = new JList();
		
		JScrollPane scrollPane = new JScrollPane();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.LIGHT_GRAY);

		Polygon poly = new Polygon();
		poly.addPoint(0, 0);
		poly.addPoint(this.mainWidth, 0);
		poly.addPoint(this.mainWidth, this.mainHeight);
		poly.addPoint(0, this.mainHeight);

		g2d.fill(poly);
	}
}
