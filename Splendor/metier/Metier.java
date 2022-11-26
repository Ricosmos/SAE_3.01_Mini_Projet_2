package Splendor.metier;

import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

import java.awt.Color;
import java.awt.Point;

public class Metier 
{
	private Joueur joueur1, joueur2;
	private ArrayList<Territoire> tabTerritoires;
	private ArrayList<JetonConcession> tabJetons;


	public Metier(String nomJoueur1, String nomJoueur2) 
	{
		this.joueur1 = new Joueur(nomJoueur1);
		this.joueur2 = new Joueur(nomJoueur2);
		this.tabTerritoires = this.lireCoordonees("Splendor/metier/coordonnees.txt");
		this.tabJetons = new ArrayList<JetonConcession>();
	}

	/*
	 * Fonction permettant de lire le fichier fournis en argument et d'en extraire tout les territoires
	 * @param Le chemin menant vers le fichier .txt qui contient les donnees de tout les territoires
	 * @return Une ArrayList de tout les territoires extrait du fichier coordonnees.txt
	 */
	private ArrayList<Territoire> lireCoordonees(String chemin)
	{
		ArrayList<Territoire> tabTerritoire = new ArrayList<Territoire>();

		try
		{
			Scanner sc = new Scanner ( new FileReader (chemin) );
			sc.nextLine(); // Première ligne qui est les entetes

			while ( sc.hasNextLine() )
			{
				String[] ligneDec = sc.nextLine().split("\\|"); // Decoupe la ligne pour avoir la couleur, taille, coordonnees des parcelles
				String[] pointDec = ligneDec[2].split(":"); // Decoupe les coordonnes pour avoir des tuples ( x, y)
				ArrayList<Point> tabPoint = new ArrayList<Point>();

				for (int cpt = 0; cpt < pointDec.length; cpt++)
				{
					tabPoint.add(new Point(Integer.parseInt(pointDec[cpt].substring(0, 2).replaceAll("\\s","")),  // partie x du tuple et on retire les espaces
											Integer.parseInt(pointDec[cpt].substring(3, 5).replaceAll("\\s","")))); // partie y du tuple et on retire les espaces
				}

				tabTerritoire.add(new Territoire(this.correspondanceCouleur(ligneDec[0]), ligneDec[1], tabPoint));
			}
			sc.close();
		}
		catch (Exception e) { e.printStackTrace();}

		return tabTerritoire;
	}

	/*
	 * Fonction permettant de faire la correspondance entre les couleurs ecrit en chaine de caractere et les couleurs de la classe Color
	 * @param La couleur qu'on souhaite avoir sa correspondance
	 * @return La correspondance de la couleur fournie dans la classe Color
	 */
	private Color correspondanceCouleur(String couleurNom)
	{
		HashMap<String, Color> hmCouleur = new HashMap<String, Color>(); // HashMap permettant de faire la correspondance
		hmCouleur.put("BLANC", Color.WHITE);
		hmCouleur.put("BLEU", Color.BLUE);
		hmCouleur.put("VERT", Color.GREEN);
		hmCouleur.put("JAUNE", Color.YELLOW);
		hmCouleur.put("ORANGE", Color.ORANGE);
		hmCouleur.put("ROSE", Color.PINK);
		hmCouleur.put("NOIR", Color.GRAY);

		return hmCouleur.get(couleurNom.replaceAll("\\s","")); // retire les espaces blancs
	}

	public static void main(String[] args) 
	{
		new Metier("j1", "j2");
	}
}
