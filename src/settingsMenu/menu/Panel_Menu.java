package settingsMenu.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.Constants;
import settingsMenu.SettingsPanel;

public class Panel_Menu extends JPanel
{
	private static final long serialVersionUID = 1L;
	private int menuHeight = 25;
	private Panel_Menu_Button activeTab;

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

		JPanel menuButtons = new JPanel();
		menuButtons.setBackground(Color.GRAY);
		add(menuButtons, BorderLayout.CENTER);

		Panel_Menu_Button panel_Profiles = new Panel_Menu_Button("Profiles");
		panel_Profiles.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent event)
			{
				SetPanelMenuButtonActive(panel_Profiles);
				SettingsPanel.GetSettingsPanel().addScrollPane("PROFILE");
			}
		});

		Panel_Menu_Button panel_Categories = new Panel_Menu_Button("Categories");
		panel_Categories.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent event)
			{
				SetPanelMenuButtonActive(panel_Categories);
				SettingsPanel.GetSettingsPanel().addScrollPane("CATEGORY");
			}
		});

		// Menu
		FlowLayout fl_MenuButtons = new FlowLayout(FlowLayout.LEFT, 0, 0);
		menuButtons.setLayout(fl_MenuButtons);
		menuButtons.add(panel_Profiles);
		menuButtons.add(panel_Categories);
	}

	private void SetPanelMenuButtonActive(Panel_Menu_Button panel)
	{
		if (activeTab != null)
		{
			activeTab.setActive(false);
		}

		panel.setActive(true);
		activeTab = panel;
	}
}
