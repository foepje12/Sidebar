package settingsMenu.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.Constants;
import settingsMenu.Button_Menu_Style;
import settingsMenu.SettingsPanel;

public class Panel_Menu extends JPanel
{
	private static final long serialVersionUID = 1L;
	private int menuHeight = 25;

	public Panel_Menu(SettingsPanel settingsPanel)
	{
		super();
		Button_Menu_Style returnButton = new Button_Menu_Style();
		returnButton.getModel().addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent event)
			{
				if (returnButton.getModel().isPressed())
				{
					settingsPanel.addScrollPane("CATEGORY");
				}
			}
		});
		add(returnButton);

		setBackground(Color.GRAY);
		
		ImageIcon imageIcon = new ImageIcon(Constants.buttonIconsPath + "/leftarrow.png");
		setLayout(new BorderLayout(0, 0));
		JLabel lblBackButton = new JLabel(imageIcon);
		lblBackButton.setHorizontalAlignment(SwingConstants.LEFT);
		lblBackButton.setPreferredSize(new Dimension(50, menuHeight));
		add(lblBackButton, BorderLayout.WEST);

		JPanel MenuButtons = new JPanel();
		MenuButtons.setBackground(Color.GRAY);
		add(MenuButtons, BorderLayout.CENTER);

		JPanel panel_Profiles = new Panel_Menu_Button("Profiles");
		JPanel panel_Categories = new Panel_Menu_Button("Categories");

		// Menu
		FlowLayout fl_MenuButtons = new FlowLayout(FlowLayout.LEFT, 0, 0);
		MenuButtons.setLayout(fl_MenuButtons);
		MenuButtons.add(panel_Profiles);
		MenuButtons.add(panel_Categories);
	}
}
