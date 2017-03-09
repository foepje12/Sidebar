package main;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class BarButton extends JPanel
{
	private static final long serialVersionUID = 1L;

	private int barWidth = 100;
	private int buttonHeight = 0;

	private int xFrom = 80;
	private int xTo = 10;
	private int baseValue = 80;
	private boolean isAnimating = false;
	private boolean isExtended = false;

	Timer timer;

	public BarButton(int height, String webUrl, ImageIcon iconUrl)
	{
		super();

		buttonHeight = height;
		setLayout(null);
		setBounds(xFrom, height, barWidth, barWidth);
		setPreferredSize(new Dimension(barWidth, barWidth));
		setMinimumSize(new Dimension(barWidth, barWidth));

		JLabel label = new JLabel(iconUrl);
		label.setOpaque(true);
		label.setBounds(0, 0, barWidth, barWidth);
		add(label);

		this.addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseEntered(MouseEvent event)
			{
				if (!isExtended)
				{
					if (!isAnimating)
					{
						StartOutAnimation();
						isAnimating = true;
					}
					else
					{
						timer.stop();
						StartOutAnimation();
						isAnimating = true;
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent event)
			{
				if (isExtended)
				{
					if (!isAnimating)
					{
						StartInAnimation();
						isAnimating = true;
					}
					else
					{
						timer.stop();
						StartInAnimation();
						isAnimating = true;
					}
				}
			}

			@Override
			public void mouseClicked(MouseEvent event)
			{
				if (SwingUtilities.isLeftMouseButton(event))
				{
					OpenWebpage(webUrl);
				}

				if (SwingUtilities.isRightMouseButton(event))
				{
					Sidebar.SwitchToArcSelector();
				}
			}
		});
	}

	void StartOutAnimation()
	{
		isExtended = true;
		timer = new Timer(1, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent aEvent)
			{
				if (xTo < xFrom)
				{
					xFrom -= 1;
					SetBounds();
				}
				else
				{
					timer.stop();
					isAnimating = false;
				}
			}
		});
		timer.start();
	}

	void StartInAnimation()
	{
		isExtended = false;

		timer = new Timer(1, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent aEvent)
			{
				if (xFrom < baseValue)
				{
					xFrom += 1;
					SetBounds();
				}
				else
				{
					timer.stop();
					isAnimating = false;
				}
			}
		});
		timer.start();
	}

	public void SetBounds()
	{
		setBounds(xFrom, buttonHeight, barWidth, barWidth);
		setPreferredSize(new Dimension(barWidth, barWidth));
	}

	public void OpenWebpage(String webUrl)
	{
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE))
		{
			try
			{
				URL url = new URL(webUrl);
				desktop.browse(url.toURI());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
