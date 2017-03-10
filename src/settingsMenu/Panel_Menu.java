package settingsMenu;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Panel_Menu extends JPanel
{
	private static final long serialVersionUID = 1L;

	public Panel_Menu(SettingsPanel settingsPanel)
	{
		super();
		setBackground(Color.pink);

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
	}
}
