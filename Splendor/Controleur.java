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
	
				Joueur joueur = this.metier.getCurrentJoueur();
	
				System.out.println("Au tour de " + joueur.getNomJoueur() + " de jouer");
				this.afficherJetonsJoueur(joueur);

				switch(choixAction()) 
				{
					case 1 -> 
					{
						this.metier.prendreJeton(joueur);
						System.out.println("Vos jetons : ");
					}
					case 2 -> 
					{
						this.metier.prendreTerritoire(joueur);
	
					}
					case 3 -> 
					{
						// Prendre Carte Objectif
						
					}
				}

				this.afficherJetonsJoueur(joueur);
				System.out.println();
				this.ihm.majIHM();
				this.metier.getJoueur1().changerTours(); // Alternation des tours des deux joueurs
				this.metier.getJoueur2().changerTours();
			}
	}

	public Joueur getJoueur1() {return this.metier.getJoueur1();}
	public Joueur getJoueur2() {return this.metier.getJoueur2();}

	private void afficherJetonsJoueur(Joueur joueur) 
	{
		for (String key : joueur.getTabJetons().keySet()) 
		{
			System.out.println(String.format("| %-6s : %2d |", key, joueur.getTabJetons().get(key)));
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
