package settingsMenu.optionsMenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import main.Constants;

public class Panel_Profile extends JPanel
{
	private static final long serialVersionUID = 1L;
	private int pieceAmount;
	private int radius;
	private int precision;
	private int pieceWidth;
	private Polygon[] shapes;

	public Panel_Profile(String name)
	{
		radius = Constants.arcRadius;
		pieceAmount = Constants.arcPieceAmount;
		precision = Constants.arcPrecision;
		pieceWidth = Constants.arcPieceWidth;
		shapes = new Polygon[pieceAmount];
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.clearRect(0, 0, radius - 75, radius * 2);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		for (int p = 0; p < pieceAmount; p++)
		{
			DrawPiece(g2d, p);
		}

		g2d.dispose();
	}

	void DrawPiece(Graphics g, int p)
	{
		shapes[p] = new Polygon();
		g.setColor(new Color(180, 180, 180));

		float[] degrees = new float[precision];

		// Calculates all the degrees to be transated to Radians
		for (int i = 0; i < precision; i++)
		{

			degrees[i] = ((float) (360 / pieceAmount) / (precision - 1)) * i + (p * 360 / pieceAmount);
			degrees[i] = degrees[i] + 20;
		}

		// Adds the outer points of a Piece
		for (int i = 0; i < degrees.length; i++)
		{
			int x = (int) getSinCos(degrees[i], radius)[0];
			int y = (int) getSinCos(degrees[i], radius)[1];

			shapes[p].addPoint(x, y);
		}

		// Add the inner points of a Piece
		for (int i = degrees.length - 1; i >= 0; i--)
		{
			int x = (int) getSinCos(degrees[i], radius - pieceWidth)[0];
			int y = (int) getSinCos(degrees[i], radius - pieceWidth)[1];

			shapes[p].addPoint(x, y);
		}
		// g.drawImage(pieces[p].getIconFile(), 0, 0, null);
		g.fillPolygon(shapes[p]);
	}

	/**
	 * Gets the sinus and cosinus for the circle
	 * 
	 * @param degrees
	 * @param radius
	 * @return double[] sinus and cosinus
	 */
	private double[] getSinCos(float degrees, int radius)
	{
		double radians = (Math.PI / 180) * degrees;

		double sin = 150 + radius * Math.sin(radians);
		double cos = 150 + radius * Math.cos(radians);

		double[] sinCos =
		{ sin, cos };

		return sinCos;
	}
}
