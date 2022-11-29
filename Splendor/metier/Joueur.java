package Splendor.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;

public class Joueur 
{
	private String nomJoueur;
	private Color couleur;
	private boolean tours;
	private int marqPosse;
	private HashMap<String, Integer> tabJetons;
	private ArrayList<Objectif> tabCartesObjectif;
	private int pv;

	public Joueur(String nomJoueur, Color couleur)
	{
		this.nomJoueur = nomJoueur;
		this.couleur = couleur;
		this.tours = false;
		this.marqPosse = 40;
		this.tabJetons = this.creerJeton();
		this.tabCartesObjectif = new ArrayList<Objectif>();
		this.pv = 0;
	}

	public String getNomJoueur() {return this.nomJoueur;}
	public Color getCouleur() {return this.couleur;}
	public boolean getTours() {return this.tours;}
	public boolean isTours() {return this.tours;}
	public int getMarqPosse() {return this.marqPosse;}
	public int getPv() {return this.pv;}

	public void addPv(int pv) {this.pv += pv;}
	public void changerTours() {this.tours = !this.tours;}

	// Methode get qui renvoie le tableau de jetons (non modifiable)
	public HashMap<String, Integer> getTabJetons() {
		HashMap<String, Integer> copieTabJeton = new HashMap<String, Integer>();
		this.tabJetons.forEach((k, v) -> copieTabJeton.put(k, v));
		return copieTabJeton;
	}

	// Methode set qui modifie le tableau de jetons pour ajouter un jeton
	public void incrementerJeton (String couleur) {this.tabJetons.put(couleur, this.tabJetons.get(couleur) + 1);}

	// Methode set qui modifie le tableau de jetons pour retirer un jeton
	public void decrementerJeton (String couleur, int nbJeton) {this.tabJetons.put(couleur, this.tabJetons.get(couleur) - nbJeton);}

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
	public String toString() 
	{
		return "{" +
			" nomJoueur='" + getNomJoueur() + "'" +
			", couleur='" + getCouleur() + "'" +
			", tours='" + isTours() + "'" +
			", marqPosse='" + getMarqPosse() + "'" +
			", tabJetons='" + this.tabJetons + "'" +
			", tabCartesObjectif='" + this.tabCartesObjectif + "'" +
			", pv='" + getPv() + "'" +
			"}";
	}

}
