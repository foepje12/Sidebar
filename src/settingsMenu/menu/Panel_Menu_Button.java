package settingsMenu.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Panel_Menu_Button extends JPanel
{
	private static final long serialVersionUID = 1L;
	private int menuButtonWidth = 100;
	private int menuButtonLineHeight = 5;
	private boolean isActive = false;

	public Panel_Menu_Button(String name)
	{
		setBackground(Color.GRAY);
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(menuButtonWidth, 25));
		setMaximumSize(new Dimension(menuButtonWidth, 25));

		//Name label
		JLabel lblProfiles = new JLabel(name);
		lblProfiles.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProfiles.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfiles.setForeground(Color.WHITE);
		lblProfiles.setPreferredSize(new Dimension(menuButtonWidth, 20));
		add(lblProfiles, BorderLayout.NORTH);
		
		//Background Color
		JPanel panel_Profiles_Background = new JPanel();
		panel_Profiles_Background.setBackground(Color.GRAY);
		panel_Profiles_Background.setPreferredSize(new Dimension(menuButtonWidth / 2, menuButtonLineHeight));
		add(panel_Profiles_Background, BorderLayout.SOUTH);

		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent event)
			{
				setActive(true);
			}
			
			@Override
			public void mouseEntered(MouseEvent event)
			{
				panel_Profiles_Background.setBackground(new Color(178, 41, 43));
			}

			@Override
			public void mouseExited(MouseEvent event)
			{
				if(!isActive)
				{
					panel_Profiles_Background.setBackground(Color.GRAY);
				}				
			}
		});
	}
	
	public void setActive(boolean active)
	{
		isActive = active;
	}
}
