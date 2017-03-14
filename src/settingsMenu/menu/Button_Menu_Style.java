package settingsMenu.menu;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;

public class Button_Menu_Style extends JButton
{
	private static final long serialVersionUID = 1L;

	public Button_Menu_Style()
	{
		super();
		setBorderPainted(false);
		setFocusPainted(false);
		setBackground(Color.GRAY);
		setCursor(new Cursor(Cursor.HAND_CURSOR));		
	}
}
