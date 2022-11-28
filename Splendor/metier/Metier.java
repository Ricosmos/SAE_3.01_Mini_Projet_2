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
	private ArrayList<Objectif> tabCartesObjectif;


	public Metier(String nomJoueur1, String nomJoueur2) 
	{
		this.joueur1 = new Joueur(nomJoueur1, new Color(3, 167, 255));
		this.joueur2 = new Joueur(nomJoueur2, Color.MAGENTA);
		this.tabTerritoires = this.lireCoordonees("Splendor/metier/coordonnees.txt");
		this.tabJetons = this.creerJeton();
		this.tabCartesObjectif = this.creerCartesObjectif();
		for (Objectif objectif : tabCartesObjectif) {
			System.out.println(objectif);
		}
	}

	public Joueur getJoueur1() {return this.joueur1;}
	public Joueur getJoueur2() {return this.joueur2;}
	public ArrayList<Territoire> getTabTerritoires() {return this.tabTerritoires;}
	public HashMap<String,Integer> getTabJetons() {return this.tabJetons;}

	public void addJeton(String couleurJeton) {this.tabJetons.put(couleurJeton, this.tabJetons.get(couleurJeton) + 1);}
	public void removeJeton(String couleurJeton, int nombreJeton) {this.tabJetons.put(couleurJeton, this.tabJetons.get(couleurJeton) - nombreJeton);}

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
	 * Creer toutes les cartes "Objectif de marché" selon les règles du Splendor
	 * @return Une ArrayList avec toutes les cartes "Objectif de marché"
	 */
	private ArrayList<Objectif> creerCartesObjectif()
	{
		String[] tabCouleurs = {"Blanc", "Bleu", "Vert", "Jaune", "Orange", "Rose", "Rouge", "Noir"};
		ArrayList<Objectif> tabCartesObjectif = new ArrayList<Objectif>();

		/*Posséder 5 Territoires de 4 parcelles*/
		tabCartesObjectif.add(new Objectif(1, 5, 4, null, null, null, 10));
		
		/*Posséder 5 Territoires couleur*/
		for (String couleur : tabCouleurs) 
		{
			tabCartesObjectif.add(new Objectif(1, 5, null, couleur, null, null, 6));
		}

		/*Posséder x Territoires couleur1 à côté d'un couleur2*/
		tabCartesObjectif.add(new Objectif(1, 3, null, "Bleu" , "Blanc", null, 4));
		tabCartesObjectif.add(new Objectif(1, 4, null, "Vert" , "Bleu" , null, 4));
		tabCartesObjectif.add(new Objectif(1, 5, null, "Rouge", "Rose" , null, 4));

		/*Posséder le même nombre de territoires couleur1, couleur2 et couleur3*/
		tabCartesObjectif.add(new Objectif(2, null, null, "Bleu"  , "Blanc", "Rouge", 8));
		tabCartesObjectif.add(new Objectif(2, null, null, "Noir"  , "Jaune", "Rouge", 8));
		tabCartesObjectif.add(new Objectif(2, null, null, "Orange", "Blanc", "Vert" , 8));
		
		/*Etre majoritaire sur les couleur1*/
		for (String couleur : tabCouleurs)
		{
			tabCartesObjectif.add(new Objectif(3, null, null, couleur, null, null, 3));
		}

		/*Posséder aucun territoire couleur1*/
		for (String couleur : tabCouleurs)
		{
			tabCartesObjectif.add(new Objectif(4, null, null, couleur, null, null, 3));
		}

		return tabCartesObjectif;
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

	public void prendreJeton(Joueur actu) {

		//Genere les 5 jetons aleatoirement
		ArrayList<String> jetons = new ArrayList<String>();
		for(int i = 0; i < 5; i++) {
			int random = (int)(Math.random() * 9);
			jetons.add(this.tabJetons.keySet().toArray()[random].toString());
		}
		jetons.add("Pioche");

		//J'essaie de faire une boulce qui s'arrete quand il a pris 2 jetons (mais il faut toute les eventualites avec la pioche)
		//en dessous, la version simpliste qui ne prend pas en compte la pioche

		/*int nbJetonRecup = 0;
		while(nbJetonRecup < 2) {
			//Affiche les jetons
			System.out.println("Jetons :");
			for(int i = 0; i < jetons.size()-1; i++) {
				System.out.println( "| " + jetons.get(i) + " |");
			}

			//Choix du joueur
			System.out.println("Choisissez un jeton :");
			Scanner sc = new Scanner(System.in);

			int choix = sc.nextInt();
			while(choix < 1 || choix > 6) {
				System.out.println("Choix invalide, veuillez recommencer :");
				choix = sc.nextInt();
			}

			//Si le joueur a choisi la pioche
			String couleur = "";
			if(choix == 6) {
				ArrayList<String> pioche = new ArrayList<String>();
				for(int i = 0; i < 2-nbJetonRecup; i++) {
					int random = (int)(Math.random() * 9);
					pioche.add(this.tabJetons.keySet().toArray()[random].toString());
				}
				System.out.println("Jetons piochés :");
				for(int i = 0; i < pioche.size(); i++) {
					System.out.println( "| " + pioche.get(i) + " |");
				}
				System.out.println("Choisissez un jeton :");
				choix = sc.nextInt();
				switch(nbJetonRecup) {
					case 0
				}
			else {
				couleur = jetons.get(choix - 1);
				jetons.remove(choix - 1);
				System.out.println("Vous avez choisi le jeton " + couleur);
			}
		}*/

		//Affiche les jetons
		System.out.println("Jetons :");
		for(int i = 0; i < jetons.size()-1; i++) {
			System.out.println( "| " + jetons.get(i) + " |");
		}

		//Choix du joueur
		System.out.println("Choisissez un jeton :");
		Scanner sc = new Scanner(System.in);

		int choix = sc.nextInt();
		while(choix < 1 || choix > 5) {
			System.out.println("Choix invalide, veuillez recommencer :");
			choix = sc.nextInt();
		}

		String couleur = jetons.get(choix - 1);
		jetons.remove(choix - 1);
		System.out.println("Vous avez choisi le jeton " + couleur);
		


		//Ajout du jeton au joueur
		actu.incrementerJeton(couleur, 1);
	}
}
