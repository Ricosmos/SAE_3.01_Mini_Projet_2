package Splendor;

import java.util.ArrayList;
import java.util.Scanner;

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
	
				Joueur actu = this.metier.getCurrentJoueur();
	
				System.out.println("Au tour de " + actu.getNomJoueur() + " de jouer");
	
				switch(choixAction()) 
				{
					case 1 -> 
					{
						// Prendre jeton
						this.metier.prendreJeton(actu);
						System.out.println("Vos jetons : ");
						for (String key : actu.getTabJeton().keySet()) 
						{
							System.out.println(String.format("| %-6s : %1d |", key, actu.getTabJeton().get(key)));
						}
					}
					case 2 -> 
					{
						// Acheter territoire
	
					}
					case 3 -> 
					{
						// Prendre Carte Objectif
						
					}
				}
	
				System.out.println();
				this.metier.getJoueur1().changerTours(); // Alternation des tours des deux joueurs
				this.metier.getJoueur2().changerTours();
			}
	}

	private int choixAction()
	{
		System.out.println("1 - Prendre des jetons concession");
		System.out.println("2 - Prendre possession d'un territoire");
		System.out.println("3 - Prendre des cartes objectif ");
		System.out.print  ("Choisissez une action : ");
		Scanner sc = new Scanner(System.in);
		int choix = sc.nextInt();
		while (choix < 1 || choix > 3)
		{
			System.out.print("Choix incorrect, veuillez recommencer : ");
			choix = sc.nextInt();
		}
		return choix; 
	}

	public ArrayList<Territoire> getTerritoire() {return this.metier.getTabTerritoires();}

	public static void main(String[] args) 
	{
		new Controleur("J1", "J2");
	}
}
