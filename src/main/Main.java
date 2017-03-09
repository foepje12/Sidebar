package main;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Main
{

	public static void main(String[] args)
	{

		createAndShowGUI();
		new Sidebar();
	}

	private static void createAndShowGUI()
	{
		// Check the SystemTray support
		if (!SystemTray.isSupported())
		{
			System.out.println("SystemTray is not supported");
			return;
		}
		final PopupMenu popup = new PopupMenu();
		final TrayIcon trayIcon = new TrayIcon(createImage("assets/smallProgramIcon.png"));
		final SystemTray tray = SystemTray.getSystemTray();

		// Create a popup menu components
		MenuItem settingsItem = new MenuItem("Settings");
		MenuItem exitItem = new MenuItem("Exit");

		// Add components to popup menu
		popup.add(settingsItem);
		popup.addSeparator();
		popup.add(exitItem);

		trayIcon.setPopupMenu(popup);

		try
		{
			tray.add(trayIcon);
		}
		catch (AWTException e)
		{
			System.out.println("TrayIcon could not be added.");
			return;
		}

		trayIcon.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "This dialog box is run from System Tray");
			}
		});

		settingsItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Sidebar.SwitchToSettingsPanel();
			}
		});

		exitItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tray.remove(trayIcon);
				System.exit(0);
			}
		});
	}

	// Obtain the image URL
	protected static Image createImage(String path)
	{
		ImageIcon icon = new ImageIcon(path);		
		return icon.getImage();
	}

}
