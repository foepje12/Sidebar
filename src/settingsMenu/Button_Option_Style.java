package settingsMenu;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;

public class Button_Option_Style extends JButton
{
	private static final long serialVersionUID = 1L;

	public Button_Option_Style(String text)
	{
		super(text);
		setBorderPainted(false);
		setFocusPainted(false);
		setBackground(new Color(211, 211, 255));
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

}
