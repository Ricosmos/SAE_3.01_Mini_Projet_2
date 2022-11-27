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
	private HashMap<String, Integer> tabJetons;


	public Metier(String nomJoueur1, String nomJoueur2) 
	{
		this.joueur1 = new Joueur(nomJoueur1, Color.GREEN);
		this.joueur2 = new Joueur(nomJoueur2, Color.MAGENTA);
		this.tabTerritoires = this.lireCoordonees("Splendor/metier/coordonnees.txt");
		this.tabJetons = this.creerJeton();
	}

	public Joueur getJoueur1() {return this.joueur1;}
	public Joueur getJoueur2() {return this.joueur2;}
	public ArrayList<Territoire> getTabTerritoires() {return this.tabTerritoires;}
	public HashMap<String,Integer> getTabJetons() {return this.tabJetons;}

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
				String[] pointDec = ligneDec[2].split(":"); // Decoupe les coordonnes pour avoir des tuples ( y, x)
				ArrayList<Point> tabPoint = new ArrayList<Point>();

				for (int cpt = 0; cpt < pointDec.length; cpt++)
				{
					tabPoint.add(new Point(Integer.parseInt(pointDec[cpt].substring(3, 5).replaceAll("\\s","")),  // partie y du tuple et on retire les espaces
											Integer.parseInt(pointDec[cpt].substring(0, 2).replaceAll("\\s","")))); // partie x du tuple et on retire les espaces
				}

				tabTerritoire.add(new Territoire(this.correspondanceCouleur(ligneDec[0]), ligneDec[1], tabPoint));
			}
			sc.close();
		}
		catch (Exception e) { e.printStackTrace();}

		return tabTerritoire;
	}

	/*
	 * Creer tout les jetons de concessions selon les règles du Splendor
	 * @return Une HashMap avec tout les types de concessions et leur quantités
	 */
	private HashMap<String, Integer> creerJeton()
	{
		HashMap<String, Integer> hmJetonConcession = new HashMap<String, Integer>();

		hmJetonConcession.put("Blanc" , 16);
		hmJetonConcession.put("Bleu"  , 16);
		hmJetonConcession.put("Vert"  , 16);
		hmJetonConcession.put("Jaune" , 16);
		hmJetonConcession.put("Orange", 16);
		hmJetonConcession.put("Rose"  , 16);
		hmJetonConcession.put("Rouge" , 16);
		hmJetonConcession.put("Noir"  , 16);
		hmJetonConcession.put("Multi" , 10);

		return hmJetonConcession;
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
		hmCouleur.put("ROUGE", Color.RED);
		hmCouleur.put("NOIR", Color.GRAY);
		hmCouleur.put("MULTI", new Color(65, 0, 204)); // en attendant

		return hmCouleur.get(couleurNom.replaceAll("\\s","").toUpperCase()); // retire les espaces blancs
	}

	public Joueur getCurrentJoueur()
	{
		if (this.joueur1.getTours())
			return this.joueur1;
		else
			return this.joueur2;
	}
	
	private boolean possessionTerritoire(Joueur joueur, Territoire territoire) 
	{
		if (joueur.getTabJeton().get(territoire.getCouleur()) < Integer.parseInt(territoire.getTaille().replaceAll("\\D", "")))
			return false;

		territoire.possederTerritoire(joueur);
		return true;
	}
}
