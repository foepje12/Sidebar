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
import java.awt.event.MouseMotionListener;

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
	int dominantColor = 6;

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

		setLayout(new FlowLayout());
		setBounds(0, 0, ArcConstants.radius * 2, ArcConstants.radius * 2);
		setPreferredSize(new Dimension(ArcConstants.radius * 2, ArcConstants.radius * 2));
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
							RotateArc(1);
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

		for (int p = 0; p < ArcConstants.pieceAmount; p++)
		{
			shapes[p] = new Polygon();

			g.setColor(pieces[p].getColor());

			float[] degrees = new float[precision];

			for (int i = 0; i < precision; i++)
			{

				degrees[i] = ((float) (360 / pieceAmount) / (precision - 1)) * i + (p * 360 / pieceAmount);
				degrees[i] = degrees[i] + currentDegrees + 20;
			}

			for (int i = 0; i < degrees.length; i++)
			{
				int x = (int) getSinCos(degrees[i], radius)[0];
				int y = (int) getSinCos(degrees[i], radius)[1];

				shapes[p].addPoint(x, y);
			}

			for (int i = degrees.length - 1; i >= 0; i--)
			{

				int x = (int) getSinCos(degrees[i], radius - pieceWidth)[0];
				int y = (int) getSinCos(degrees[i], radius - pieceWidth)[1];

				shapes[p].addPoint(x, y);
			}

			g.fillPolygon(shapes[p]);
		}
	}

	double[] getSinCos(float degrees, int radius)
	{
		double radians = (Math.PI / 180) * degrees;

		double sin = 150 + radius * Math.sin(radians);
		double cos = 150 + radius * Math.cos(radians);

		double[] sinCos =
		{ sin, cos };

		return sinCos;
	}

	public void repaintThis()
	{
		for(int i = 0; i < pieceAmount; i++)
		{
			pieces[i].setColor(Color.DARK_GRAY);
			
			if(i == dominantColor)
			{
				pieces[i].setColor(colors[i]);
			}
		}
		this.repaint();
	}

	public int from = 0;

	public void RotateArc(int way)
	{
		int rotationAmount = 360 / pieceAmount;

		timer = new Timer(50, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent aEvent)
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
				}
			}
		});
		timer.start();
	}
}
