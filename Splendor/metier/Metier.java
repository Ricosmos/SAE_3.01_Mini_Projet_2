package Splendor.metier;

import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import java.awt.Point;

public class Metier 
{
	private Joueur joueur1, joueur2;
	private ArrayList<Territoire> tabTerritoires;
	private HashMap<String, Integer> tabJetons;
	private ArrayList<String> tabPiocheVisible;
	private ArrayList<Objectif> tabCartesObjectif;

	public Metier(String nomJoueur1, String nomJoueur2) 
	{
		this.joueur1 = new Joueur(nomJoueur1, new Color(3, 167, 255));
		this.joueur2 = new Joueur(nomJoueur2, Color.MAGENTA);
		this.tabTerritoires = this.lireCoordonees("Splendor/metier/coordonnees.txt");
		this.tabJetons = this.creerJeton();
		this.tabPiocheVisible = this.creerPiocheJetons();
		this.tabCartesObjectif = this.creerCartesObjectif();

		this.initialiseTabJetonsJoueur(joueur1);
		this.initialiseTabJetonsJoueur(joueur2);
	}

	/* ===================================================================
	 * A SUPPRIMER, PERMET DE VOIR TOUT LES JETONS QUI SONT DANS TABJETONS
	 */
	private void visuTabJeton()
	{
		for(Map.Entry<String, Integer> entry : this.tabJetons.entrySet()) 
		{
			String key = entry.getKey();
			Integer value = entry.getValue();
		
			System.out.println(String.format("%6s : %1d", key, value));
		}
	}
	// ===================================================================

	public Joueur getJoueur1() {return this.joueur1;}
	public Joueur getJoueur2() {return this.joueur2;}
	public ArrayList<Territoire> getTabTerritoires() {return this.tabTerritoires;}
	public HashMap<String,Integer> getTabJetons() {return this.tabJetons;}
	public ArrayList<String> getTabPiocheVisible() {return this.tabPiocheVisible;}
	public ArrayList<Objectif> getTabCartesObjectif() {return this.tabCartesObjectif;}

	public void retirerJeton(String couleurJeton, int nombreJeton) {this.tabJetons.put(couleurJeton, this.tabJetons.get(couleurJeton) - nombreJeton);}

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

				tabTerritoire.add(new Territoire(this.correspondanceCouleur(ligneDec[0]), ligneDec[0], ligneDec[1], tabPoint));
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
	 * Creer la pioche visible au joueur
	 * @return Le tableau contenant les 5 jetons visibles
	 */
	private ArrayList<String> creerPiocheJetons()
	{
		ArrayList<String> tabPiocheVisible = new ArrayList<String>();

		for(int i = 0; i < 5; i++) 
		{
			int random = (int)(Math.random() * 9);
			String jeton = this.tabJetons.keySet().toArray()[random].toString();
			tabPiocheVisible.add(jeton);
			this.retirerJeton(jeton, 1);
		}
		tabPiocheVisible.add("Pioche");

		return tabPiocheVisible;
	}

	/*
	 * Initialise les 4 jetons de départ du joueur
	 * @param Le joueur a initialise c'est jetons
	 */
	private void initialiseTabJetonsJoueur(Joueur joueur)
	{
		for (int cpt = 0; cpt < 4; cpt++)
		{
			joueur.incrementerJeton(this.piocheJeton());
		}
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

	private Objectif[] piocherObjectif()
	{
		Objectif[] tabObjectif = new Objectif[2];
		
		return null;
	}

	public Joueur getCurrentJoueur()
	{
		if (this.joueur1.getTours())
			return this.joueur1;
		else
			return this.joueur2;
	}

	private String piocheJeton()
	{
		int random = (int)(Math.random() * 9);
		String pioche = this.tabJetons.keySet().toArray()[random].toString();

		return pioche;
	}

	public void prendreJeton(Joueur joueur) 
	{

		//J'essaie de faire une boulce qui s'arrete quand il a pris 2 jetons (mais il faut toute les eventualites avec la pioche)
		//en dessous, la version simpliste qui ne prend pas en compte la pioche

		// Cas des jetons multi-concession visible a gerer 

		int nbJetonRecup = 0;
		while(nbJetonRecup < 2) 
		{
			//Affiche les jetons
			System.out.println("Jetons :");
			for(int i = 0; i < this.tabPiocheVisible.size(); i++) 
			{
				System.out.println(String.format("|%1d - %-6s |", i + 1, this.tabPiocheVisible.get(i)));
			}

			//Choix du joueur
			System.out.print("Choisissez un jeton : ");
			Scanner sc = new Scanner(System.in);

			int choix = sc.nextInt();
			while(choix < 1 || choix > 6) 
			{	
				System.out.print("Choix invalide, veuillez recommencer : ");
				choix = sc.nextInt();
			}

			//Si le joueur a choisi la pioche
			String jetonChoisie = "";
			if(choix == 6) 
			{
				/*ArrayList<String> pioche = new ArrayList<String>();

			
				for(int i = 0; i < 2-nbJetonRecup; i++) // Pourquoi une boucle ? 
				{
					int random = (int)(Math.random() * 9);
					pioche.add(this.tabJetons.keySet().toArray()[random].toString());
				}

				System.out.println("Jetons piochés :");

				for(int i = 0; i < pioche.size(); i++) 
				{
					System.out.println( "| " + pioche.get(i) + " |");
				}

				System.out.println("Choisissez un jeton :");
				choix = sc.nextInt();
				switch(nbJetonRecup) 
				{
					case 0
				} */

				String pioche = this.piocheJeton();
				joueur.incrementerJeton(pioche);
				this.retirerJeton(pioche, 1);
				System.out.println("Jetons piochés : " + pioche);
			}
			else 
			{
				jetonChoisie = this.tabPiocheVisible.get(choix - 1);
				this.tabPiocheVisible.remove(choix - 1);
				joueur.incrementerJeton(jetonChoisie);
				System.out.println("Vous avez choisi le jeton " + jetonChoisie);

				this.tabPiocheVisible.remove(4); // retire la pioche pour pas quelle descente dans l'ArrayList

				String pioche = this.piocheJeton();
				this.tabPiocheVisible.add(pioche);
				this.retirerJeton(pioche, 1);

				tabPiocheVisible.add("Pioche"); // On remet la pioche qui sera de nouveau dernier
			}
			nbJetonRecup++;
		}
/* 
		//Affiche les jetons
		System.out.println("Jetons :");
		for(int i = 0; i < this.tabPiocheVisible.size()-1; i++) {
			System.out.println( "| " + this.tabPiocheVisible.get(i) + " |");
		}

		//Choix du joueur
		System.out.println("Choisissez un jeton :");
		Scanner sc = new Scanner(System.in);

		int choix = sc.nextInt();
		while(choix < 1 || choix > 5) {
			System.out.println("Choix invalide, veuillez recommencer :");
			choix = sc.nextInt();
		}

		String couleur = this.tabPiocheVisible.get(choix - 1);
		this.tabPiocheVisible.remove(choix - 1);
		System.out.println("Vous avez choisi le jeton " + couleur);
		 
		//Ajout du jeton au joueur
		joueur.incrementerJeton(couleur, 1);
		*/
	}


	public boolean prendreTerritoire(Joueur joueur) 
	{
		this.afficherTerritoireLibre();
		Scanner sc = new Scanner(System.in);
		while (true)
		{
			System.out.print("Choisissez un territoire : ");
			int choix = sc.nextInt();
			while(choix < 1 || choix > this.tabTerritoires.size()) {
				System.out.print("Choix invalide, veuillez recommencer :");
				choix = sc.nextInt();
			}
			Territoire territoire = this.tabTerritoires.get(choix - 1);
	
			int  tailleTerritoire = Integer.parseInt(territoire.getTaille().replaceAll("\\D", ""));
			String couleurTerritoire = territoire.getNomCouleur().substring(0, 1) + territoire.getNomCouleur().substring(1).toLowerCase().replaceAll("\\s", ""); // Couleur en caps -> Laisser que la premiere maj
			
			if (joueur.getTabJetons().get(couleurTerritoire) == tailleTerritoire && territoire.getPosseder() == null)
			{
				joueur.decrementerJeton(couleurTerritoire, tailleTerritoire);
				territoire.possederTerritoire(joueur);
				return true;
			}
			else if (territoire.getPosseder() != null)
			{
				System.out.println("Ce territoire est déjà possédé");
			}
			else
			{
				System.out.println(String.format("Vous n'avez pas assez de jeton pour posséder le territoire %-6s %-2s", territoire.getNomCouleur(), territoire.getTaille()));
				System.out.println(joueur.getTabJetons().get(couleurTerritoire) + " " + tailleTerritoire);
				return false;
			}
		}
	}

	private void afficherTerritoireLibre() 
	{
		int cpt = 1;
		for (Territoire territoire : this.tabTerritoires)
		{
			if (territoire.getPosseder() == null)
			{
				System.out.println(String.format("| %2d - %-6s %-2s", cpt, territoire.getNomCouleur(), territoire.getTaille()));
			}			
			cpt++;		
		}
	}
}
