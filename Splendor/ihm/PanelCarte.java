package Splendor.ihm;

import Splendor.metier.*;
import Splendor.Controleur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelCarte extends JPanel
{
	private final int RADIUS  = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()  / 2 / 34;//30;
	private final int RATIO_X = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()  / 2 / 34; // 30;
	private final int RATIO_Y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 / 13; // 45;

	private Controleur ctrl;
	private ArrayList<Territoire> tabTerritoires;

	public PanelCarte(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.tabTerritoires = this.ctrl.getTerritoire();
		this.setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 500,
											  (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 200));
		this.setBackground(Color.WHITE);
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
			for (int j = 0; j < this.tabTerritoires.get(i).getTabPoint().size(); j++)
			{
				g2D.setStroke(new BasicStroke((float)(2)));
				
				Point point = this.tabTerritoires.get(i).getTabPoint().get(j);
				Polygon hexagone = this.createHexagon((int)point.getX(), (int)point.getY());
				g2D.setColor(this.tabTerritoires.get(i).getCouleur());
				g2D.fillPolygon(hexagone);
				g2D.setColor(Color.BLACK);
				g2D.drawPolygon(hexagone);

				if (this.tabTerritoires.get(i).getPosseder() != null)
				{

					g2D.setColor(Color.BLACK); // Croix noir
					g2D.setStroke(new BasicStroke((float)(8)));
					this.dessinerCroix(g2D, (int)point.getX(), (int)point.getY());

					g2D.setColor(this.tabTerritoires.get(i).getPosseder().getCouleur()); // Croix de la couleur du joueur possedant le territoire
					g2D.setStroke(new BasicStroke((float)(5)));
					this.dessinerCroix(g2D, (int)point.getX(), (int)point.getY());
				}
			}
		}
	}
	
	private Polygon createHexagon(int x, int y) 
	{

		Polygon polygon = new Polygon();

		polygon.addPoint(x * this.RATIO_X - this.RADIUS  , y * this.RATIO_Y - this.RADIUS/2);
		polygon.addPoint(x * this.RATIO_X                , y * this.RATIO_Y - this.RADIUS  );
		polygon.addPoint(x * this.RATIO_X + this.RADIUS  , y * this.RATIO_Y - this.RADIUS/2);
		polygon.addPoint(x * this.RATIO_X + this.RADIUS  , y * this.RATIO_Y + this.RADIUS/2);
		polygon.addPoint(x * this.RATIO_X                , y * this.RATIO_Y + this.RADIUS  );
		polygon.addPoint(x * this.RATIO_X - this.RADIUS  , y * this.RATIO_Y + this.RADIUS/2);

		return polygon;
	}

	private void dessinerCroix(Graphics2D g2D, int x, int y)
	{
		g2D.drawLine(x * this.RATIO_X - 10, y * this.RATIO_Y - 10, 
						x * this.RATIO_X + 10, y * this.RATIO_Y + 10);
		g2D.drawLine(x * this.RATIO_X - 10, y * this.RATIO_Y + 10, 
						x * this.RATIO_X + 10, y * this.RATIO_Y - 10);
	}
}
