package Splendor.metier;

import java.util.HashMap;
import java.awt.Color;

public class Joueur 
{
	private String nomJoueur;
	private Color couleur;
	private boolean tours;
	private int marqPosse;
	private HashMap<String, Integer> tabJeton;
	private int pv;

	public Joueur(String nomJoueur, Color couleur)
	{
		this.nomJoueur = nomJoueur;
		this.couleur = couleur;
		this.tours = false;
		this.marqPosse = 40;
		this.tabJeton = new HashMap<String, Integer>();
		this.pv = 0;
	}

	public String getNomJoueur() {return this.nomJoueur;}
	public Color getCouleur() {return this.couleur;}
	public boolean getTours() {return this.tours;}
	public boolean isTours() {return this.tours;}
	public int getMarqPosse() {return this.marqPosse;}
	public HashMap<String, Integer> getTabJeton() {return this.tabJeton;}
	public int getPv() {return this.pv;}

	public void addPv(int pv) {this.pv += pv;}
	public void changerTours() {this.tours = !this.tours;}

	@Override
	public String toString() {
		return "{" +
			" nomJoueur='" + getNomJoueur() + "'" +
			", couleur='" + getCouleur() + "'" +
			", tours='" + getTours() + "'" +
			", marqPosse='" + getMarqPosse() + "'" +
			", tabJeton='" + getTabJeton() + "'" +
			", pv='" + getPv() + "'" +
			"}";
	}
}
