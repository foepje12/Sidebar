package settingsMenu.scrollpane;

import handlers.CategoryHandler;
import settingsMenu.SettingsPanel;

public class Label_Category extends BaseScrollLabel
{
	private static final long serialVersionUID = 1L;
	private String catgName;
	private boolean isNew;
	private SettingsPanel settingsPanel;

	public Label_Category(String catgName)
	{
		super(catgName);
		this.catgName = catgName;
		settingsPanel = SettingsPanel.GetSettingsPanel();
	}

	public Label_Category(String catgName, boolean isNew)
	{
		this(catgName);
		this.isNew = isNew;
	}

	@Override
	public void doSingleClick()
	{
		if (isNew)
		{
			CategoryHandler.addCategory();
			settingsPanel.addScrollPane("CATEGORY");
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
