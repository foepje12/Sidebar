package settingsMenu;

import handlers.CategoryHandler;

public class Label_Category extends BaseScrollLabel
{
	private static final long serialVersionUID = 1L;
	private SettingsPanel settingsPanel;
	private String catgName;
	private boolean isNew;

	public Label_Category(String catgName, SettingsPanel settingsPanel)
	{
		super(catgName);
		this.catgName = catgName;
		this.settingsPanel = settingsPanel;
	}

	public Label_Category(String catgName, SettingsPanel settingsPanel, boolean isNew)
	{
		this(catgName, settingsPanel);
		this.isNew = isNew;
	}

	@Override
	public void doSingleClick()
	{
		if (isNew)
		{
			CategoryHandler.addCategory();
			settingsPanel.RefreshScrollPane("CATEGORY");
		}
		else
		{
			settingsPanel.currentCategoryName = catgName;
			settingsPanel.SetOptionsPanelCategory(catgName);
		}
	}

	@Override
	public void doDoubleClick()
	{
		settingsPanel.currentCategoryName = catgName;
		settingsPanel.addScrollPane("BAR_ITEM");
	}
}
