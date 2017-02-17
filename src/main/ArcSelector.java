package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ArcSelector extends JPanel {
	private static final long serialVersionUID = 1L;

	public ArcSelector() {
		super();

		setBounds(200, 20, 400, ArcConstants.radius * 5);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(400, ArcConstants.radius * 2));
		this.setBackground(Color.gray);

		Color[] colors = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.PINK,
				Color.BLACK };

		for (int i = 0; i < ArcConstants.pieceAmount; i++) {
			SelectorPiece piece = new SelectorPiece(i, colors[i]);
			add(piece);
		}
	}
	
	int i = 0;

	@Override
	public void paintComponent(Graphics g) {

		
		
		Timer timer = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent aEvent) {
				while (true) 
				{
					Graphics2D g2d = (Graphics2D) g;
					int w2 = getWidth() / 2;
					int h2 = getHeight() / 2;
					g2d.rotate(-Math.PI / 2 + i, w2, h2);
					i++;
					
					if(i == 360)
					{
						i = 0;
					}

				}
			}
		});
		timer.start();

		super.paintComponent(g);
	}

}
