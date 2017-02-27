package main;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BarButton extends JButton
{
	private static final long serialVersionUID = 1L;

	int barWidth = 100;
	int buttonHeight = 0;

	public int xFrom = 80;
	public int xTo = 10;
	public int baseValue = 80;
	public boolean isAnimating = false;
	public boolean isExtended = false;

	Timer timer;

	public BarButton(Color color, int height, String webUrl, ImageIcon iconUrl)
	{
		super();
		buttonHeight = height;
		setLayout(null);
		setBounds(xFrom, height, barWidth, barWidth);
		setPreferredSize(new Dimension(barWidth, barWidth));
		setMinimumSize(new Dimension(barWidth, barWidth));
		setBackground(color);
		setBorderPainted(false);
		setFocusPainted(false);

		JLabel label = new JLabel(iconUrl);
		label.setOpaque(true);
		label.setBounds(0, 0, barWidth, barWidth);
		add(label);

		this.getModel().addChangeListener(new ChangeListener()
		{

			@Override
			public void stateChanged(ChangeEvent event)
			{
				ButtonModel model = (ButtonModel) event.getSource();

				if (model.isRollover())
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
				else
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

				if (model.isPressed())
				{
					OpenWebpage(webUrl);
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
