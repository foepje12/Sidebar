package settingsMenu.optionsMenu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import arcSelector.CircleDrawing;
import arcSelector.Piece;
import main.Constants;

public class PaintPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private int currentDegrees;
	private int pieceAmount;
	private int radius;
	private int precision;
	private int pieceWidth;
	private Polygon[] shapes;
	private Piece[] pieces;
	private Piece currentSelectedPiece;

	public PaintPanel()
	{
		super();
		currentDegrees = 0;
		radius = Constants.arcRadius;
		pieceAmount = Constants.arcPieceAmount;
		precision = Constants.arcPrecision;
		pieceWidth = Constants.arcPieceWidth;
		shapes = new Polygon[pieceAmount];
		pieces = new Piece[pieceAmount];

		setBackground(Color.GRAY);

		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent event)
			{
				for (int p = 0; p < shapes.length; p++)
				{
					if (SwingUtilities.isLeftMouseButton(event) && shapes[p].contains(event.getX(), event.getY()))
					{
						if (currentSelectedPiece != null)
						{
							currentSelectedPiece.setColor(new Color(180, 180, 180));
						}

						pieces[p].setColor(Color.RED);
						currentSelectedPiece = pieces[p];
						repaint();
					}
				}
			}
		});

		for (int i = 0; i < shapes.length; i++)
		{
			pieces[i] = new Piece(new Color(180, 180, 180));
		}
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// g.clearRect(getWidth() / 2 - radius, 10, radius * 2, radius * 2);
		// g.setColor(Color.GRAY);
		// g.fillRect(getWidth() / 2 - radius, 10, radius * 2, radius * 2);

		

		int[] measures =
		{ precision, pieceAmount, radius, pieceWidth, currentDegrees, 2, 2 };

		Polygon[] polyArray = CircleDrawing.GetCircle(g, measures);
		shapes = polyArray;

		for (int i = 0; i < polyArray.length; i++)
		{

			g2d.setColor(pieces[i].getColor());
			g2d.fillPolygon(polyArray[i]);
			g2d.setColor(Color.BLACK);
			g2d.drawPolygon(polyArray[i]);
		}
		
		g2d.dispose();
	}
}
