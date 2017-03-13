package settingsMenu.scrollpane;

import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import handlers.BarItemHandler;
import handlers.CategoryHandler;
import settingsMenu.SettingsPanel;

public class Label_ScrollPane extends JScrollPane
{
	private static final long serialVersionUID = 1L;
	private String currentCategoryName;
	private SettingsPanel settingsPanel;

	public Label_ScrollPane(String type, SettingsPanel settingsPanel)
	{
		super();
		this.settingsPanel = settingsPanel;

		JPanel panelScrollPane = new JPanel();
		panelScrollPane.setLayout(new BoxLayout(panelScrollPane, BoxLayout.Y_AXIS));

		switch (type)
		{
		case "CATEGORY":
			addCategoriesToScrollPane(panelScrollPane);
			break;
		case "BAR_ITEM":
			addBarItemToScrollPane(panelScrollPane, currentCategoryName);
			break;
		}

		setViewportView(panelScrollPane);
	}

	private void addCategoriesToScrollPane(JPanel panel)
	{
		Set<String> strings = CategoryHandler.getCategoryNames();

		for (String catgNames : strings)
		{
			JLabel label = new Label_Category(catgNames, settingsPanel);

			panel.add(label);
		}

		JLabel addNewCategoryLabel = new Label_Category("Add Category", settingsPanel, true);
		panel.add(addNewCategoryLabel);
	}

	private void addBarItemToScrollPane(JPanel panel, String catgName)
	{
		Set<String> strings = BarItemHandler.getBarItemNames(catgName);

		for (String set : strings)
		{
			Label_BarItem label = new Label_BarItem(catgName, set, settingsPanel);
			label.setAlignmentY(JLabel.CENTER_ALIGNMENT);
			panel.add(label);
		}

		JLabel addNewCategoryLabel = new Label_BarItem(catgName, "Add BarItem", settingsPanel, true);
		panel.add(addNewCategoryLabel);
	}

	private void resetScrollPane()
	{
		removeAll();
	}
}
