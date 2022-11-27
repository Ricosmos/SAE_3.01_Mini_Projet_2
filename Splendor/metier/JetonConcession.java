package Splendor.metier;

import java.awt.Color;

public class JetonConcession 
{
	private String nom;
	private Color couleur;

	public JetonConcession(String nom, Color couleur) 
	{
		this.nom = nom;
		this.couleur = couleur;
	}

	public Color getCouleur() {return this.couleur;}
	public String getNom() {return this.nom;}

	@Override
	public String toString() 
	{
		return "{" +
			" nom='" + getNom() + "'" +
			", couleur='" + getCouleur() + "'" +
			"}";
	}
}
