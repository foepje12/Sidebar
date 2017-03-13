package main;

import javax.swing.JFrame;

import arcSelector.Frame_ArcSelector;
import settingsMenu.SettingsPanel;
import sideButton.SideButton;

public class SideBar
{

	private static JFrame currentJFrame;

	public static void main(String[] args)
	{
		SwitchToSettingsPanel();
	}

	public static void SwitchToArcSelector()
	{
		DeleteCurrentFrame();
		JFrame selector = new Frame_ArcSelector();
		currentJFrame = selector;
		
	}

	public static void SwitchToBarButtons()
	{
		DeleteCurrentFrame();
		JFrame sideButton = new SideButton();
		currentJFrame = sideButton;
	}

	public static void SwitchToSettingsPanel()
	{
		DeleteCurrentFrame();
		JFrame settingsPanel = new SettingsPanel();
		currentJFrame = settingsPanel;
	}

	private static void DeleteCurrentFrame()
	{
		try
		{
			currentJFrame.removeAll();
			currentJFrame.dispose();
		}
		catch (NullPointerException ex)
		{
		}
	}
}
