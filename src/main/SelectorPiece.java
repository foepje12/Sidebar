package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SelectorPiece extends JPanel {

	private static final long serialVersionUID = 1L;

	private int pieceAmount;
	private int pointAmount;
	private int radius;
	private int piece;
	private int precision;
	private Color color;

	public SelectorPiece(int piece, Color color) {
		super();

		this.pointAmount = ArcConstants.pointAmount;
		this.radius = ArcConstants.radius;
		this.piece = piece;
		this.pieceAmount = ArcConstants.pieceAmount;
		this.precision = ArcConstants.precision;
		this.color = color;

		this.setBounds(100,100, 400, 200);
		this.setPreferredSize(new Dimension(200, 200));
		this.setBackground(new Color(0, 0, 0, 0));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(this.color);

		Polygon shape = new Polygon();

		float[] degrees = new float[precision];

		for (int i = 0; i < precision; i++) {

			degrees[i] = ((float) (360 / pieceAmount) / (precision - 1)) * i + (piece * 360 / pieceAmount);
		}

		for (int i = 0; i < degrees.length; i++) {
			int x = (int) getSinCos(degrees[i], radius)[0];
			int y = (int) getSinCos(degrees[i], radius)[1];

			shape.addPoint(x, y);
		}

		for (int i = degrees.length - 1; i >= 0; i--) {

			System.out.println(i);

			int x = (int) getSinCos(degrees[i], radius - 20)[0];
			int y = (int) getSinCos(degrees[i], radius - 20)[1];

			shape.addPoint(x, y);
		}

		g.fillPolygon(shape);
	}

	double[] getSinCos(float degrees, int radius) {
		double radians = (Math.PI / 180) * degrees;

		double sin = 50 + radius * Math.sin(radians);
		double cos = 50 + radius * Math.cos(radians);

		double[] sinCos = { sin, cos };

		return sinCos;
	}
}
