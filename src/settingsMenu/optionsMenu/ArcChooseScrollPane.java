package settingsMenu.optionsMenu;

import java.awt.Color;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import handlers.CategoryHandler;
import settingsMenu.SettingsPanel;

public class ArcChooseScrollPane extends JScrollPane
{
	private static final long serialVersionUID = 1L;
	private JPanel panel;

	public ArcChooseScrollPane()
	{
		super();
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));

		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	}

	public void AddLabels()
	{
		Set<String> strings = CategoryHandler.getCategoryNames();

		for (String string : strings)
		{
			Label_ScrollPane label = new Label_ScrollPane(string);
			panel.add(label);
		}
		SettingsPanel.packFrame();
	}

}
