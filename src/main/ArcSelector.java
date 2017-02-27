package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class ArcSelector extends JPanel
{
	private static final long serialVersionUID = 1L;
	private int pieceAmount;
	private int radius;
	private int precision;
	private int pieceWidth;
	private Polygon[] shapes;
	private Piece[] pieces;
	Color[] colors =
	{ Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.PINK, Color.BLACK };

	public ArcSelector()
	{
		super();
		radius = ArcConstants.radius;
		pieceAmount = ArcConstants.pieceAmount;
		precision = ArcConstants.precision;
		pieceWidth = ArcConstants.pieceWidth;
		shapes = new Polygon[pieceAmount];
		pieces = new Piece[pieceAmount];
		
		setLayout(new FlowLayout());
		setBounds(0, 0, ArcConstants.radius * 2, ArcConstants.radius * 2);
		setPreferredSize(new Dimension(ArcConstants.radius * 2, ArcConstants.radius * 2));
		setBackground(new Color(0, 0, 0, 0));

		this.addMouseMotionListener(new MouseMotionListener()
		{
			@Override
			public void mouseMoved(MouseEvent event)
			{
				for (int i = 0; i < pieceAmount; i++)
				{
					pieces[i].setColor(Color.DARK_GRAY);

					if (shapes[i].contains(event.getX(), event.getY()))
					{
						pieces[i].setColor(colors[i]);
						repaintThis();
					}
				}
			}

			@Override
			public void mouseDragged(MouseEvent e)
			{
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
		this.repaint();
	}
}
