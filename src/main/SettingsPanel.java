package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SettingsPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	public SettingsPanel()
	{
		super();
		this.setBounds(0, 0, Constants.settingsWidth, Constants.settingsHeight);
		this.setPreferredSize(new Dimension(Constants.settingsWidth, Constants.settingsHeight));
		this.setBackground(Color.LIGHT_GRAY);

		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent event)
			{
				if (SwingUtilities.isMiddleMouseButton(event))
				{
					Sidebar.SwitchToArcSelector();
				}
			}
		});
	}
}
