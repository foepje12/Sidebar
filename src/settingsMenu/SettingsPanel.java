package settingsMenu;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.Constants;
import main.ScreenProperties;
import settingsMenu.menu.Panel_Menu;
import settingsMenu.optionsMenu.Panel_BarCat;
import settingsMenu.scrollpane.Label_ScrollPane;

public class SettingsPanel extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static JFrame jframe;

	// The different panels in the frame
	public JPanel panel_OptionsMenu;
	public JScrollPane panel_ScrollPane;
	public JPanel panel_TopMenu;

	public String currentCategoryName;

	public SettingsPanel()
	{
		super();
		jframe = this;

		// Setting up the frame size
		int screenWidth = ScreenProperties.getScreenWidth();
		int screenHeight = ScreenProperties.getScreenHeight();
		int barWidth = Constants.settingsWidth;
		int barHeight = Constants.settingsHeight;

		setBounds((screenWidth / 2) - (barWidth / 2), (screenHeight / 2) - (barHeight / 2), barWidth, barHeight);
		setPreferredSize(new Dimension(barWidth, barHeight));

		// Setting up the frame itself
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setVisible(true);
		pack();

		// Adding the panels
		addDefaultOptionsPane();
		addScrollPane("CATEGORY");
		addMenuPane();
	}

	/**
	 * Add the Options Panel
	 */
	private void addDefaultOptionsPane()
	{
		panel_OptionsMenu = new JPanel();
		add(panel_OptionsMenu, BorderLayout.CENTER);
		packFrame();
	}

	/**
	 * Add the Menu Panel
	 */
	private void addMenuPane()
	{
		panel_TopMenu = new Panel_Menu(this);
		add(panel_TopMenu, BorderLayout.NORTH);
		packFrame();
	}

	/**
	 * Add the ScrollPane Panel
	 * 
	 * @param type
	 */
	public void addScrollPane(String type)
	{
		if (panel_ScrollPane != null)
		{
			panel_ScrollPane.removeAll();
			remove(panel_ScrollPane);
			panel_ScrollPane = null;
		}

		panel_ScrollPane = new Label_ScrollPane(type, this);
		add(panel_ScrollPane, BorderLayout.LINE_START);
		packFrame();
	}

	/**
	 * Refresh the ScrollPane Panel
	 * 
	 * @param type
	 */
	public void RefreshScrollPane(String type)
	{
		addScrollPane(type);
	}

	/**
	 * Set the Options Panel to Category
	 * 
	 * @param catgName
	 */
	public void SetOptionsPanelCategory(String catgName)
	{
		DeleteOptionsPanel();
		OpenPanelOptions(catgName, "CATEGORY");
	}

	/**
	 * Set the Options Panel to BarItem
	 * 
	 * @param catgName
	 * @param barName
	 */
	public void SetOptionsPanelBarItem(String catgName, String barName)
	{
		DeleteOptionsPanel();
		this.currentCategoryName = catgName;
		OpenPanelOptions(barName, "BAR_ITEM");
	}

	private void DeleteOptionsPanel()
	{
		if (panel_OptionsMenu != null)
		{
			panel_OptionsMenu.removeAll();
			remove(panel_OptionsMenu);
			panel_OptionsMenu = null;
		}
	}

	private void OpenPanelOptions(String name, String type)
	{
		switch (type)
		{
		case "CATEGORY":
			panel_OptionsMenu = new Panel_BarCat(name, this, type);
			add(panel_OptionsMenu);
			packFrame();
			break;
		case "BAR_ITEM":
			panel_OptionsMenu = new Panel_BarCat(currentCategoryName, name, this, type);
			add(panel_OptionsMenu);
			packFrame();
			break;
		case "PROFILE":
			// OpenProfile(name, this);
			break;
		}
	}

	public static void packFrame()
	{
		jframe.repaint();
		jframe.pack();
		jframe.revalidate();
	}

	public static JFrame GetJFrame()
	{
		if (jframe != null)
		{
			return jframe;
		}
		return null;
	}

	public void ResetOptionsPanel()
	{
		DeleteOptionsPanel();
		panel_OptionsMenu = new JPanel();
		add(panel_OptionsMenu);
		packFrame();
	}
}