import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Metier 
{
	private Joueur joueur1, joueur2;
	private ArrayList<Territoire> tabTerritoires;
	private ArrayList<JetonConcession> tabJetons;


	public Metier(String nomJoueur1, String nomJoueur2) 
	{
		this.joueur1 = new Joueur(nomJoueur1);
		this.joueur2 = new Joueur(nomJoueur2);
		this.tabTerritoires = Metier.lireCoordonees();
		this.tabJetons = new ArrayList<JetonConcession>();
	}

	private ArrayList<Territoire> lireCoordonees()
	{
		ArrayList<Territoire> tabTerritoire = new ArrayList<Territoire>();

		try
		{
			Scanner sc = new Scanner ( new FileReader ( "coordonnees.txt" ) );
			sc.nextLine(); // Première ligne qui est les entetes

			while ( sc.hasNextLine() )
			{
				String[] ligneDec = sc.nextLine().split(";");
				tabTerritoire.add(new Territoire(null, null, null))
			}
			sc.close();
		}
		catch (Exception e) { e.printStackTrace();}

		return tabVille;

	}

}
