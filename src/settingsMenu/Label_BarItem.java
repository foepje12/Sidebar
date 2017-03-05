package settingsMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import main.Constants;

public class Label_BarItem extends JLabel
{
	private static final long serialVersionUID = 1L;
	
	public Label_BarItem(String barName, SettingsPanel settingsPanel)
	{
		super(barName);
		Dimension size = new Dimension((int) (Constants.leftScrollPaneWidth), 25);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);

		setBackground(Color.WHITE);
		setOpaque(true);
		
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent event)
			{
				settingsPanel.addBarItemOptions(barName);
			}

			@Override
			public void mouseEntered(MouseEvent event)
			{
				setBackground(new Color(203, 217, 235));
			}

			@Override
			public void mouseExited(MouseEvent event)
			{
				setBackground(Color.WHITE);
			}
		});
		
	}
}
