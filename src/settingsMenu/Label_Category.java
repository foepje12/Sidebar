package settingsMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import main.ClickListener;
import main.Constants;

public class Label_Category extends JLabel
{
	private static final long serialVersionUID = 1L;

	public Label_Category(String catgName, SettingsPanel settingsPanel)
	{
		super(catgName);
		setAlignmentY(JLabel.CENTER_ALIGNMENT);
		Dimension size = new Dimension(Constants.leftScrollPaneWidth, 25);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);

		setBackground(Color.WHITE);
		setOpaque(true);

		addMouseListener(new ClickListener(300)
		{
			@Override
			public void singleClick(MouseEvent e)
			{
				settingsPanel.addCategoryOptions(catgName);
			}

			@Override
			public void doubleClick(MouseEvent e)
			{
				settingsPanel.currentCategoryName = catgName;
				settingsPanel.addScrollPane("BAR_ITEM");
			}

			@Override
			public void mouseEntered(MouseEvent event)
			{
				setBackground(new Color(203, 217, 235));
			}

			@Override
			public void mouseExited(MouseEvent event)
			{
				setBackground(Color.WHITE);
			}
		});
	}
}
