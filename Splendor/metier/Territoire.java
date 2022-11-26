package Splendor.metier;

import java.util.ArrayList;
import java.awt.Point;
import java.awt.Color;

public class Territoire 
{
	private Color couleur;
	private String taille;
	private ArrayList<Point> tabPoint;
	private Joueur posseder;

	public Territoire(Color couleur, String taille, ArrayList<Point> tabPoint) 
	{
		this.couleur = couleur;
		this.taille = taille;
		this.tabPoint = tabPoint;
		this.posseder = null;
	}

	public Color getCouleur() {return this.couleur;}
	public String getTaille() {return this.taille;}
	public ArrayList<Point> getTabPoint() {return this.tabPoint;}
	public Joueur getPosseder() {return this.posseder;}

	public void possederTerritoire(Joueur joueur) {this.posseder = joueur;}

	@Override
	public String toString() {
		return "{" +
			" couleur='" + getCouleur() + "'" +
			", taille='" + getTaille() + "'" +
			", tabPoint='" + getTabPoint() + "'" +
			", posseder='" + getPosseder() + "'" +
			"}";
	}
}