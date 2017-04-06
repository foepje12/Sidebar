package settingsMenu.optionsMenu;

import settingsMenu.scrollpane.Label_Category;

public class Label_ScrollPane extends Label_Category
{
	private static final long serialVersionUID = 1L;
	private String catgName;

	public Label_ScrollPane(String catgName)
	{
		super(catgName);
		this.catgName = catgName;
	}

	@Override
	public void doSingleClick()
	{
		PaintPanel.SetCurrentSelectedPiece(catgName);
	}
}
