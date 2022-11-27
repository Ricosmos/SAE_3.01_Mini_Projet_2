package Splendor;

import java.util.ArrayList;

import Splendor.ihm.FramePrincipale;
import Splendor.metier.*;

public class Controleur 
{
	private Metier metier;
	private FramePrincipale ihm;

	public Controleur(String joueur1, String joueur2)
	{
		this.metier = new Metier(joueur1, joueur2);
		this.ihm = new FramePrincipale(this);

		if ((int)(Math.random() * 2) == 0) // [0;1] Pour selectionner le joueur qui commence aleatoirement
			this.metier.getJoueur1().changerTours();
		else
			this.metier.getJoueur2().changerTours();

		while (true)
		{

			this.metier.getJoueur1().changerTours(); // Alternation des tours des deux joueurs
			this.metier.getJoueur2().changerTours();
		}
	}

	public ArrayList<Territoire> getTerritoire() {return this.metier.getTabTerritoires();}

	public static void main(String[] args) 
	{
		new Controleur("J1", "J2");
	}
}
