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
import settingsMenu.scrollpane.Label_ScrollPane;

public class SettingsPanel extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static JFrame jframe;

	public JPanel panel_OptionsMenu;
	public JScrollPane panel_ScrollPane;
	public JPanel panel_TopMenu;

	public String currentCategoryName;

	public SettingsPanel()
	{
		super();
		jframe = this;
		int screenWidth = ScreenProperties.getScreenWidth();
		int screenHeight = ScreenProperties.getScreenHeight();
		int barWidth = Constants.settingsWidth;
		int barHeight = Constants.settingsHeight;

		setBounds(screenWidth - barWidth, (screenHeight / 2) - (barHeight / 2) * 3, barWidth / 2, barHeight);
		setPreferredSize(new Dimension(barWidth, barHeight));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setVisible(true);
		pack();

		addDefaultOptionsPane();
		addScrollPane("CATEGORY");
		addMenuPane();
	}

	private void addDefaultOptionsPane()
	{
		panel_OptionsMenu = new JPanel();
		add(panel_OptionsMenu, BorderLayout.CENTER);
		packFrame();
	}

	private void addMenuPane()
	{
		panel_TopMenu = new Panel_Menu(this);
		add(panel_TopMenu, BorderLayout.NORTH);
		packFrame();
	}

	public void addScrollPane(String type)
	{
		panel_ScrollPane = new Label_ScrollPane(type, this);
		add(panel_ScrollPane, BorderLayout.LINE_START);
		packFrame();
	}

	public void RefreshScrollPane(String type)
	{
		addScrollPane(type);
	}

	public void SetOptionsPanelCategory(String catgName)
	{
		if (panel_OptionsMenu.getComponentCount() > 0)
		{
			panel_OptionsMenu.removeAll();
		}

		OpenPanelOptions(catgName, "CATEGORY");
		packFrame();
	}

	public void SetOptionsPanelBarItem(String catgName, String barName)
	{
		if (panel_OptionsMenu.getComponentCount() > 0)
		{
			panel_OptionsMenu.removeAll();
		}

		this.currentCategoryName = catgName;
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
			add(new Panel_BarCat(currentCategoryName, name, this, type));
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