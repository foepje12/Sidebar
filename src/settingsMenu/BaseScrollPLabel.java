package settingsMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import main.ClickListener;
import main.Constants;

public class BaseScrollPLabel extends JLabel
{
	private static final long serialVersionUID = 1L;

	public BaseScrollPLabel(String text)
	{
		super(text);
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
				doSingleClick();
			}

			@Override
			public void doubleClick(MouseEvent e)
			{
				doDoubleClick();
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

	public void doSingleClick()
	{
	}

	public void doDoubleClick()
	{
	}
}
