package settingsMenu;

public class Label_BarItem extends BaseScrollLabel
{
	private static final long serialVersionUID = 1L;
	private String barName;

	public Label_BarItem(String barName, SettingsPanel settingsPanel, boolean isNew)
	{
		super(barName);
		this.barName = barName;
	}

	@Override
	public void doSingleClick()
	{
		addBarItemOptions(barName);
	}

	void addBarItemOptions(String BarItemName)
	{

	}
}
