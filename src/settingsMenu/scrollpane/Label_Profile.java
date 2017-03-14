package settingsMenu.scrollpane;

import handlers.ProfileHandler;
import settingsMenu.SettingsPanel;

public class Label_Profile extends BaseScrollLabel
{
	private static final long serialVersionUID = 1L;
	private SettingsPanel settingsPanel;
	private String profName;
	private boolean isNew;

	public Label_Profile(String profName, SettingsPanel settingsPanel)
	{
		super(profName);
		this.profName = profName;
		this.settingsPanel = settingsPanel;
	}

	public Label_Profile(String profName, SettingsPanel settingsPanel, boolean isNew)
	{
		this(profName, settingsPanel);
		this.isNew = isNew;
	}

	@Override
	public void doSingleClick()
	{
		if (isNew)
		{
			ProfileHandler.addProfile();
			settingsPanel.addScrollPane("PROFILE");
		}
		else
		{
			settingsPanel.currentPofileName = profName;
			settingsPanel.SetOptionsPanelProfile(profName);
		}
	}

	@Override
	public void doDoubleClick()
	{
		settingsPanel.currentPofileName = profName;
		settingsPanel.addScrollPane("CATEGORY");
	}

}
