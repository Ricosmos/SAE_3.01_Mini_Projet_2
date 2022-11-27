package Splendor.ihm;

import Splendor.metier.*;
import Splendor.Controleur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelCarte extends JPanel
{
	private Controleur ctrl;
	private ArrayList<Territoire> tabTerritoires;

	public PanelCarte(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.tabTerritoires = this.ctrl.getTerritoire();
		
		this.setPreferredSize(new Dimension(1500, 1500));
		this.setBackground(Color.BLACK);
	}

	
 	public void majIHM()
	{
		super.repaint();
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2D = (Graphics2D)(g);

		for (int i = 0; i < this.tabTerritoires.size(); i++)
		{
			g2D.setColor(this.tabTerritoires.get(i).getCouleur());
			for (int j = 0; j < this.tabTerritoires.get(i).getTabPoint().size(); j++)
			{
				Point point = this.tabTerritoires.get(i).getTabPoint().get(j);
				g2D.fillPolygon(this.createHexagon((int)point.getX(), (int)point.getY()));
			}
		}

	}
	
	private Polygon createHexagon(int x, int y) 
	{
		Polygon polygon = new Polygon();
		final int RADIUS = 25;

		for (int i = 0; i < 6; i++) 
		{
			int xval = (int) (x * RADIUS + RADIUS * Math.sin(i * 2 * Math.PI / 6D));
			int yval = (int) (y * RADIUS + RADIUS * Math.cos(i * 2 * Math.PI / 6D));
			
			polygon.addPoint(xval, yval);
		}
		
		return polygon;
	}
}
