package settingsMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import handlers.CategoryHandler;
import main.Constants;
import main.ScreenProperties;
import main.SideBar;
import settingsMenu.menu.Panel_Menu;
import settingsMenu.optionsMenu.Panel_BarCat;
import settingsMenu.scrollpane.Label_BarItem;
import settingsMenu.scrollpane.Label_Category;

public class SettingsPanel extends JFrame
{
	private static final long serialVersionUID = 1L;

	public JPanel mainPanel = new JPanel();
	public JPanel panelScrollPane;
	private JScrollPane scrollPane;
	public String currentCategoryName;
	private static JFrame jframe;
	private String catgName;

	public SettingsPanel()
	{
		super();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		jframe = this;

		int barWidth = Constants.settingsWidth;
		int barHeight = Constants.settingsHeight;
		setBounds(ScreenProperties.getScreenWidth() - barWidth,
				(ScreenProperties.getScreenHeight() / 2) - (barHeight / 2) * 3, barWidth / 2, barHeight);
		setPreferredSize(new Dimension(barWidth, barHeight));

		setVisible(true);
		pack();

		JPanel mainPanel = new JPanel();

		mainPanel.setBounds(0, 0, Constants.settingsWidth, Constants.settingsHeight);
		mainPanel.setPreferredSize(new Dimension(Constants.settingsWidth, Constants.settingsHeight));
		mainPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.setLayout(new BorderLayout());

		mainPanel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent event)
			{
				if (SwingUtilities.isMiddleMouseButton(event))
				{
					SideBar.SwitchToArcSelector();
				}
			}
		});

		add(mainPanel);

		scrollPane = new JScrollPane(panelScrollPane);
		add(scrollPane, BorderLayout.LINE_START);

		addScrollPane("CATEGORY");

		JPanel panel_Menu = new Panel_Menu(this);
		add(panel_Menu, BorderLayout.NORTH);

		packFrame();
	}

	private void resetScrollPane()
	{
		if (panelScrollPane != null)
		{
			panelScrollPane.removeAll();
		}
	}

	public void addScrollPane(String type)
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

		packFrame();
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

		OpenPanelOptions(catgName, "CATEGORY");
		packFrame();
	}

	public void SetOptionsPanelBarItem(String catgName, String barName)
	{
		if (mainPanel.getComponentCount() > 0)
		{
			mainPanel.removeAll();
		}

		this.catgName = catgName;
		OpenPanelOptions(barName, "BAR_ITEM");
		packFrame();
	}

	private void OpenPanelOptions(String name, String type)
	{
		switch (type)
		{
		case "CATEGORY":
			add(new Panel_BarCat(name, this, type));
			packFrame();
			break;
		case "BAR_ITEM":
			add(new Panel_BarCat(catgName, name, this, type));
			packFrame();
			break;
		case "PROFILE":
			// OpenProfile(name, this);
			break;
		}
	}

	public static void packFrame()
	{
		jframe.revalidate();
		jframe.repaint();
		jframe.pack();
	}

	public static JFrame GetJFrame()
	{
		try
		{
			return jframe;
		}
		catch (NullPointerException ex)
		{
			return null;
		}
	}
}