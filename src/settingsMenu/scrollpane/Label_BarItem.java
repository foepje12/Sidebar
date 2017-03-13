package settingsMenu.scrollpane;

import handlers.BarItemHandler;
import settingsMenu.SettingsPanel;

public class Label_BarItem extends BaseScrollLabel
{
	private static final long serialVersionUID = 1L;
	private String catgName;
	private String barName;
	private SettingsPanel settingsPanel;
	private boolean isNew = false;

	public Label_BarItem(String catgName, String barName, SettingsPanel settingsPanel, boolean isNew)
	{
		this(catgName, barName, settingsPanel);
		this.isNew = isNew;
	}
	
	public Label_BarItem(String catgName, String barName, SettingsPanel settingsPanel)
	{
		super(barName);
		this.barName = barName;
		this.settingsPanel = settingsPanel;
		this.catgName = catgName;
	}

	@Override
	public void doSingleClick()
	{
		if (isNew)
		{
			BarItemHandler.addBarItem(catgName);
			settingsPanel.RefreshScrollPane("BAR_ITEM");
		}
		else
		{
			settingsPanel.SetOptionsPanelBarItem(catgName, barName);
		}
	}
}
