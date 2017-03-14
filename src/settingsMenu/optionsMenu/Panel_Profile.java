package settingsMenu.optionsMenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import arcSelector.CircleDrawing;
import main.Constants;

public class Panel_Profile extends JPanel
{
	private static final long serialVersionUID = 1L;
	private int currentDegrees;
	private int pieceAmount;
	private int radius;
	private int precision;
	private int pieceWidth;
	private Polygon[] shapes;

	public Panel_Profile(String name)
	{
		currentDegrees = 0;
		radius = Constants.arcRadius;
		pieceAmount = Constants.arcPieceAmount;
		precision = Constants.arcPrecision;
		pieceWidth = Constants.arcPieceWidth;
		shapes = new Polygon[pieceAmount];

		setBackground(Color.GRAY);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.clearRect(getWidth() / 2 - radius, 10, radius * 2, radius * 2);
		g.setColor(getBackground());
		g.fillRect(getWidth() / 2 - radius, 10, radius * 2, radius * 2);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int[] measures =
		{ precision, pieceAmount, radius, pieceWidth, currentDegrees, 50, 10 };

		Polygon[] polyArray = CircleDrawing.GetCircle(g, measures);
		shapes = polyArray;

		for (int i = 0; i < polyArray.length; i++)
		{
			g.setColor(new Color(180, 180, 180));
			g2d.fillPolygon(polyArray[i]);
			g2d.setColor(Color.BLACK);
			g2d.drawPolygon(polyArray[i]);
		}

		g2d.dispose();
	}

}
