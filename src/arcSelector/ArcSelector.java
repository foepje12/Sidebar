package arcSelector;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import main.Constants;
import main.SideBar;

public class ArcSelector extends JPanel
{
	private static final long serialVersionUID = 1L;
	private int pieceAmount;
	private int radius;
	private int precision;
	private int pieceWidth;
	private Polygon[] shapes;
	// private Piece[] pieces;
	private Timer timer;
	private int currentDegrees;
	private boolean isRotating;
	int dominantColor = 5;

	int rotationAmount;
	int from = 0;
	int to;

	public ArcSelector()
	{
		super();

		radius = Constants.arcRadius;
		pieceAmount = Constants.arcPieceAmount;
		precision = Constants.arcPrecision;
		pieceWidth = Constants.arcPieceWidth;
		shapes = new Polygon[pieceAmount];
		// pieces = new Piece[pieceAmount];

		currentDegrees = 0;
		isRotating = false;

		rotationAmount = 360 / pieceAmount;
		to = rotationAmount;

		setLayout(new FlowLayout());
		setBounds(0, 0, radius - 75, radius * 2);
		setPreferredSize(new Dimension(radius - 75, radius * 2));
		setBackground(new Color(0, 0, 0, 0));

		this.addMouseWheelListener(new MouseWheelListener()
		{
			@Override
			public void mouseWheelMoved(MouseWheelEvent event)
			{
				if (!isRotating)
				{
					for (int i = 0; i < pieceAmount; i++)
					{
						if (shapes[i].contains(event.getX(), event.getY()))
						{
							if (event.getWheelRotation() < 0)
							{
								RotateArc(1);
							}
							else if (event.getWheelRotation() > 0)
							{
								RotateArc(-1);
							}

							isRotating = true;
						}
					}
				}
			}
		});

		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent event)
			{
				if (SwingUtilities.isLeftMouseButton(event))
				{
					for (int i = 0; i < pieceAmount; i++)
					{
						if (shapes[i].contains(event.getX(), event.getY()))
						{
						}
					}
				}

				if (SwingUtilities.isRightMouseButton(event))
				{
					SideBar.SwitchToBarButtons();
				}

				if (SwingUtilities.isMiddleMouseButton(event))
				{
					SideBar.SwitchToSettingsPanel();
				}
			}
		});

		// Create pieces
		for (int i = 0; i < pieceAmount; i++)
		{
			// CategoryHandler.get
			// pieces[i] = new Piece();
		}
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.clearRect(0, 0, radius - 75, radius * 2);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int[] measures =
		{ precision, pieceAmount, radius, pieceWidth, currentDegrees };

		Polygon[] polyArray = CircleDrawing.GetCircle(g, measures);
		shapes = polyArray;

		for (int i = 0; i < polyArray.length; i++)
		{
			g2d.fillPolygon(polyArray[i]);
		}

		g2d.dispose();
	}

	/**
	 * Repaints the paintComponent
	 */
	public void repaintThis()
	{
		repaint();
		revalidate();
	}

	/**
	 * Animate the Arc rotation
	 * 
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

	private void CheckCurrentColor()
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
