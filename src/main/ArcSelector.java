package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ArcSelector extends JPanel
{
	private static final long serialVersionUID = 1L;
	private int pieceAmount;
	private int radius;
	private int precision;
	private int pieceWidth;
	private Polygon[] shapes;
	private Piece[] pieces;
	Timer timer;
	Color[] colors =
	{ Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.PINK, Color.BLACK };
	Piece currentPiece;
	int currentDegrees;
	boolean isRotating;
	int dominantColor = 5;

	int rotationAmount;
	int from = 0;
	int to;

	public ArcSelector()
	{
		super();
		radius = ArcConstants.radius;
		pieceAmount = ArcConstants.pieceAmount;
		precision = ArcConstants.precision;
		pieceWidth = ArcConstants.pieceWidth;
		shapes = new Polygon[pieceAmount];
		pieces = new Piece[pieceAmount];

		currentPiece = pieces[6];
		currentDegrees = 0;
		isRotating = false;

		rotationAmount = 360 / pieceAmount;
		to = rotationAmount;

		setLayout(new FlowLayout());
		setBounds(0, 0, ArcConstants.radius - 75, ArcConstants.radius * 2);
		setPreferredSize(new Dimension(ArcConstants.radius - 75, ArcConstants.radius * 2));
		setBackground(new Color(0, 0, 0, 0));

		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent event)
			{
				if (!isRotating)
				{
					for (int i = 0; i < pieceAmount; i++)
					{
						if (shapes[i].contains(event.getX(), event.getY()))
						{
							if (i < dominantColor)
							{
								RotateArc(1);
							}
							else
							{
								RotateArc(-1);
							}

							isRotating = true;
						}
					}
				}
			}
		});

		for (int i = 0; i < pieceAmount; i++)
		{
			pieces[i] = new Piece(colors[i]);
		}
	}

	int i = 0;

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		for (int p = 0; p < pieceAmount; p++)
		{
			DrawPiece(g, p);
		}
	}

	void DrawPiece(Graphics g, int p)
	{
		shapes[p] = new Polygon();

		g.setColor(pieces[p].getColor());

		float[] degrees = new float[precision];

		//Calculates all the degrees to be transated to Radians
		for (int i = 0; i < precision; i++)
		{

			degrees[i] = ((float) (360 / pieceAmount) / (precision - 1)) * i + (p * 360 / pieceAmount);
			degrees[i] = degrees[i] + currentDegrees + 20;
		}

		//Adds the outer points of a Piece
		for (int i = 0; i < degrees.length; i++)
		{
			int x = (int) getSinCos(degrees[i], radius)[0];
			int y = (int) getSinCos(degrees[i], radius)[1];

			shapes[p].addPoint(x, y);
		}

		//Add the inner points of a Piece
		for (int i = degrees.length - 1; i >= 0; i--)
		{

			int x = (int) getSinCos(degrees[i], radius - pieceWidth)[0];
			int y = (int) getSinCos(degrees[i], radius - pieceWidth)[1];

			shapes[p].addPoint(x, y);
		}

		g.fillPolygon(shapes[p]);
	}

	/**
	 * Gets the sinus and cosinus for the circle
	 * @param degrees
	 * @param radius
	 * @return double[] sinus and cosinus
	 */
	double[] getSinCos(float degrees, int radius)
	{
		double radians = (Math.PI / 180) * degrees;

		double sin = 150 + radius * Math.sin(radians);
		double cos = 150 + radius * Math.cos(radians);

		double[] sinCos =
		{ sin, cos };

		return sinCos;
	}

	/**
	 * Repaints the paintComponent
	 */
	public void repaintThis()
	{
		for (int i = 0; i < pieceAmount; i++)
		{
			pieces[i].setColor(Color.DARK_GRAY);

			if (i == dominantColor)
			{
				pieces[i].setColor(colors[i]);
			}
		}
		this.repaint();
	}

	/**
	 * Animate the Arc rotation
	 * @param way
	 */
	public void RotateArc(int way)
	{

		timer = new Timer(5, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent aEvent)
			{
				if (way < 0)
				{
					if (from < rotationAmount)
					{
						from += 1;
						currentDegrees += 1;
						if (currentDegrees == 360)
						{
							currentDegrees = 0;
						}
						repaintThis();
					}
					else
					{
						timer.stop();
						from = 0;
						isRotating = false;

						dominantColor -= 1;

						CheckCurrentColor();

						repaintThis();
					}
				}
				else
				{
					if (to > 0)
					{
						to -= 1;
						currentDegrees -= 1;
						if (currentDegrees < 0)
						{
							currentDegrees = 360;
						}
						repaintThis();
					}
					else
					{
						timer.stop();
						to = rotationAmount;
						isRotating = false;

						dominantColor += 1;

						CheckCurrentColor();

						repaintThis();
					}
				}
			}
		});
		timer.start();
	}

	void CheckCurrentColor()
	{
		if (dominantColor < 0)
		{
			dominantColor = pieceAmount - 1;
		}
		if (dominantColor == pieceAmount)
		{
			dominantColor = 0;
		}
	}
}
