package arcSelector;

import java.awt.Graphics;
import java.awt.Polygon;

public class CircleDrawing
{
	private static Polygon[] shapes;

	private static int precision;
	private static int pieceAmount;
	private static int pieceRadius;
	private static int pieceWidth;
	private static int currentDegrees;
	private static int[] offset;

	/**
	 * Gets all the circle segments as a polygon array
	 * 
	 * @param g
	 * @param measures
	 * @return
	 */
	public static Polygon[] GetCircle(Graphics g, int[] measures)
	{
		offset = new int[2];

		precision = measures[0];
		pieceAmount = measures[1];
		pieceRadius = measures[2];
		pieceWidth = measures[3];
		currentDegrees = measures[4];
		offset[0] = measures[5];
		offset[1] = measures[6];

		shapes = new Polygon[pieceAmount];
		Polygon[] polyArray = new Polygon[pieceAmount];

		for (int p = 0; p < pieceAmount; p++)
		{
			polyArray[p] = DrawPiece(g, p);
		}
		return polyArray;
	}

	/**
	 * Draws a single circle segment
	 * 
	 * @param g
	 * @param p
	 * @return
	 */
	private static Polygon DrawPiece(Graphics g, int p)
	{
		shapes[p] = new Polygon();

		float[] degrees = new float[precision];

		// Calculates all the degrees to be transated to Radians
		for (int i = 0; i < precision; i++)
		{
			degrees[i] = ((float) (360 / pieceAmount) / (precision - 1)) * i + (p * 360 / pieceAmount);
			degrees[i] = degrees[i] + currentDegrees + 22;
		}

		// Adds the outer points of a Piece
		for (int i = 0; i < degrees.length; i++)
		{
			int x = (int) getSinCos(degrees[i], pieceRadius)[0];
			int y = (int) getSinCos(degrees[i], pieceRadius)[1];

			shapes[p].addPoint(x, y);
		}

		// Add the inner points of a Piece
		for (int i = degrees.length - 1; i >= 0; i--)
		{

			int x = (int) getSinCos(degrees[i], pieceRadius - pieceWidth)[0];
			int y = (int) getSinCos(degrees[i], pieceRadius - pieceWidth)[1];

			shapes[p].addPoint(x, y);
		}
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

		double sin = (pieceRadius + offset[0]) + radius * Math.sin(radians);
		double cos = (pieceRadius + offset[1]) + radius * Math.cos(radians);

		double[] sinCos =
		{ sin, cos };

		return sinCos;
	}
}
