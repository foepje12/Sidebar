package arcSelector;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class CircleDrawing
{
	private static Polygon[] shapes;
	private static int currentDegrees;

	private static int precision;
	private static int pieceAmount;
	private static int radius;
	private static int pieceWidth;

	public static Polygon[] GetCircle(Graphics g, int[] measures)
	{
		precision = measures[0];
		pieceAmount = measures[1];
		radius = measures[2];
		pieceWidth = measures[3];
		currentDegrees = measures[4];

		shapes = new Polygon[pieceAmount];
		Polygon[] polyArray = new Polygon[pieceAmount];

		for (int p = 0; p < pieceAmount; p++)
		{
			polyArray[p] = DrawPiece(g, p);
		}
		return polyArray;
	}

	private static Polygon DrawPiece(Graphics g, int p)
	{
		shapes[p] = new Polygon();
		g.setColor(new Color(180, 180, 180));

		float[] degrees = new float[precision];

		// Calculates all the degrees to be transated to Radians
		for (int i = 0; i < precision; i++)
		{
			degrees[i] = ((float) (360 / pieceAmount) / (precision - 1)) * i + (p * 360 / pieceAmount);
			degrees[i] = degrees[i] + currentDegrees + 20;
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
		return shapes[p];
	}

	/**
	 * Gets the sinus and cosinus for the circle
	 * 
	 * @param degrees
	 * @param radius
	 * @return double[] sinus and cosinus
	 */
	private static double[] getSinCos(float degrees, int radius)
	{
		double radians = (Math.PI / 180) * degrees;

		double sin = 150 + radius * Math.sin(radians);
		double cos = 150 + radius * Math.cos(radians);

		double[] sinCos =
		{ sin, cos };

		return sinCos;
	}
}
