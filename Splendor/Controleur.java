package Splendor;

import Splendor.metier.*;

public class Controleur 
{
	private Metier metier;

	public Controleur(String joueur1, String joueur2)
	{
		this.metier = new Metier(joueur1, joueur2);
	}

	public static void main(String[] args) 
	{
		new Controleur("J1", "J2");
	}
}
