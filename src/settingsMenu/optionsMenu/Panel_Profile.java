package settingsMenu.optionsMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import handlers.ProfileHandler;
import main.Constants;
import settingsMenu.SettingsPanel;

public class Panel_Profile extends JPanel
{
	private static final long serialVersionUID = 1L;

	public static Panel_Profile profilePanel;
	public static ArcChooseScrollPane scrollPane;
	public static String currentProfile;

	public Panel_Profile(String name)
	{
		profilePanel = this;
		currentProfile = name;

		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(Constants.settingsMainWidth, Constants.settingsMainHeight));
		setLayout(new BorderLayout(0, 0));

		PaintPanel paintPanel = new PaintPanel();
		paintPanel.setPreferredSize(new Dimension(304, 304));
		add(paintPanel, BorderLayout.CENTER);

		JPanel eastPane = new JPanel();
		eastPane.setBackground(Color.GRAY);
		add(eastPane, BorderLayout.EAST);
		eastPane.setLayout(new BorderLayout(0, 0));

		scrollPane = new ArcChooseScrollPane();
		eastPane.add(scrollPane, BorderLayout.CENTER);

		JPanel NameChangePanel = new JPanel();
		eastPane.add(NameChangePanel, BorderLayout.EAST);
		NameChangePanel.setBackground(Color.GRAY);

		JTextField txt_ChangeName = new JTextField();
		txt_ChangeName.setPreferredSize(new Dimension(130, 25));

		Button_Option_Style button = new Button_Option_Style("Change Name");
		button.getModel().addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				if (button.getModel().isPressed() && txt_ChangeName.getText().length() > 0)
				{
					ProfileHandler.renameProfile(name, txt_ChangeName.getText());
					SettingsPanel.GetSettingsPanel().addScrollPane("PROFILE");
				}
			}
		});

		GroupLayout gl_NameChangePanel = new GroupLayout(NameChangePanel);
		gl_NameChangePanel.setHorizontalGroup(gl_NameChangePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_NameChangePanel.createSequentialGroup().addContainerGap(58, Short.MAX_VALUE)
						.addGroup(gl_NameChangePanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txt_ChangeName, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 126,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(button, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		gl_NameChangePanel.setVerticalGroup(gl_NameChangePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_NameChangePanel.createSequentialGroup().addContainerGap()
						.addComponent(txt_ChangeName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(button, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(80)));
		NameChangePanel.setLayout(gl_NameChangePanel);
	}
}
