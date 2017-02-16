package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ArcSelector extends JPanel
{
	private static final long serialVersionUID = 1L;

	int pointAmount = 3;
	
	public ArcSelector()
	{
		super();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.ORANGE);
		
		int[] xPoints = new int[pointAmount];
		int[] yPoints = new int[pointAmount];

		xPoints[0] = (int) getSinCos(0)[0];
		yPoints[0] = (int) getSinCos(0)[1];
		
		xPoints[pointAmount -1] = (int) getSinCos(pointAmount -1)[0];
		yPoints[pointAmount -1] = (int) getSinCos(pointAmount -1)[1];
		
		for (int i = 1; i < 11; i++)
		{
			double[] sinCos = getSinCos(i);

			xPoints[i] = (int) sinCos[0];
			yPoints[i] = (int) sinCos[1];
			
			System.out.println(sinCos[0]);
		}

		g.fillPolygon(xPoints, yPoints, pointAmount + 1);
	}

	double[] getSinCos(int degrees)
	{
		degrees = (90 / pointAmount * degrees);
		
		double sin = 20 + Math.sin((Math.PI / 180) * degrees);
		double cos = 20 + Math.cos((Math.PI / 180) * degrees);

		double[] sinCos =
		{ sin, cos };

		return sinCos;
	}

}
