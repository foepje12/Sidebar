package settingsMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import handlers.CategoryHandler;
import main.Constants;
import main.Sidebar;

public class SettingsPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	public JPanel mainPanel = new JPanel();
	public JPanel panelScrollPane;
	private JScrollPane scrollPane;
	public String currentCategoryName;

	public SettingsPanel()
	{
		super();

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
			}
		});

		add(mainPanel);

		scrollPane = new JScrollPane(panelScrollPane);
		add(scrollPane, BorderLayout.LINE_START);

		addScrollPane("CATEGORY");

		JLabel lblNewLabel = new JLabel("New label");
		add(lblNewLabel, BorderLayout.NORTH);

		Sidebar.getJframe().pack();
	}

	private void resetScrollPane()
	{
		if (panelScrollPane != null)
		{
			panelScrollPane.removeAll();
		}
	}

	void addScrollPane(String type)
	{
		resetScrollPane();

		panelScrollPane = new JPanel();

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

		scrollPane.setViewportView(panelScrollPane);
		Sidebar.packJFrame();
	}

	private void addCategoriesToScrollPane(JPanel panel)
	{
		Set<String> strings = CategoryHandler.getCategoryNames();

		for (String catgNames : strings)
		{
			JLabel label = new Label_Category(catgNames, this);

			panel.add(label);
		}

		JLabel addNewCategoryLabel = new Label_Category("Add Category", this, true);
		panel.add(addNewCategoryLabel);
	}

	private void addBarItemToScrollPane(JPanel panel, String catgName)
	{
		Set<String> strings = CategoryHandler.getBarItemNames(catgName);

		for (String set : strings)
		{
			Label_BarItem label = new Label_BarItem(catgName, set, this);
			label.setAlignmentY(JLabel.CENTER_ALIGNMENT);
			panel.add(label);
		}

		JLabel addNewCategoryLabel = new Label_BarItem(catgName, "Add BarItem", this, true);
		panel.add(addNewCategoryLabel);

	}

	public void RefreshScrollPane(String type)
	{
		addScrollPane(type);
	}

	public void SetOptionsPanelCategory(String catgName)
	{
		if (mainPanel.getComponentCount() > 0)
		{
			mainPanel.removeAll();
		}

		Panel_Options panelOptions = new Panel_Options(catgName, this, "CATEGORY");
		mainPanel.add(panelOptions);
		Sidebar.packJFrame();
	}

	public void SetOptionsPanelBarItem(String catgName, String barName)
	{
		if (mainPanel.getComponentCount() > 0)
		{
			mainPanel.removeAll();
		}

		Panel_Options panelOptions = new Panel_Options(catgName, barName, this, "BAR_ITEM");
		mainPanel.add(panelOptions);
		Sidebar.packJFrame();
	}
}