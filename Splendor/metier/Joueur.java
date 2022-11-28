package Splendor.metier;

import java.util.HashMap;
import java.awt.Color;

public class Joueur 
{
	private String nomJoueur;
	private Color couleur;
	private boolean tours;
	private int marqPosse;
	private HashMap<String, Integer> tabJetons;
	private int pv;

	public Joueur(String nomJoueur, Color couleur)
	{
		this.nomJoueur = nomJoueur;
		this.couleur = couleur;
		this.tours = false;
		this.marqPosse = 40;
		this.tabJetons = this.creerJeton();
		this.pv = 0;
	}

	public String getNomJoueur() {return this.nomJoueur;}
	public Color getCouleur() {return this.couleur;}
	public boolean getTours() {return this.tours;}
	public boolean isTours() {return this.tours;}
	public int getMarqPosse() {return this.marqPosse;}
	public HashMap<String, Integer> getTabJeton() {return this.tabJetons;}
	public int getPv() {return this.pv;}

	public void addPv(int pv) {this.pv += pv;}
	public void changerTours() {this.tours = !this.tours;}
	public void addJeton(String couleurJeton) {this.tabJetons.put(couleurJeton, this.tabJetons.get(couleurJeton) + 1);}
	public void removeJeton(String couleurJeton, int nombreJeton) {this.tabJetons.put(couleurJeton, this.tabJetons.get(couleurJeton) - nombreJeton);}

	/*
	 * Prepare le joueur a recevoir les jetons concessions
	 * @return Une HashMap avec tout les types de concessions
	 */
	private HashMap<String, Integer> creerJeton()
	{
		HashMap<String, Integer> hmJetonConcession = new HashMap<String, Integer>();

		hmJetonConcession.put("Blanc" , 0);
		hmJetonConcession.put("Bleu"  , 0);
		hmJetonConcession.put("Vert"  , 0);
		hmJetonConcession.put("Jaune" , 0);
		hmJetonConcession.put("Orange", 0);
		hmJetonConcession.put("Rose"  , 0);
		hmJetonConcession.put("Rouge" , 0);
		hmJetonConcession.put("Noir"  , 0);
		hmJetonConcession.put("Multi" , 0);

		return hmJetonConcession;
	}

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
