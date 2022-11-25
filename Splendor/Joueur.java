import java.util.HashMap;

public class Joueur 
{
	private String nomJoueur;
	private boolean tours;
	private int marqPosse;
	private HashMap<JetonConcession, Integer> tabJeton;
	private int pv;

	public Joueur(String nomJoueur)
	{
		this.nomJoueur = nomJoueur;
		this.tours = false;
		this.marqPosse = 40;
		this.tabJeton = new HashMap<JetonConcession, Integer>();
		this.pv = 0;
	}

	public String getNomJoueur() {return this.nomJoueur;}
	public boolean getTours() {return this.tours;}
	public int getMarqPosse() {return this.marqPosse;}
	public HashMap<JetonConcession, Integer> getTabJeton() {return this.tabJeton;}
	public int getPv() {return this.pv;}

	public void addPv(int pv) {this.pv += pv;}
	public void changerTours() {this.tours = !this.tours;}

	@Override
	public String toString() {
		return "{" +
			" nomJoueur='" + getNomJoueur() + "'" +
			", tours='" + getTours() + "'" +
			", marqPosse='" + getMarqPosse() + "'" +
			", tabJeton='" + getTabJeton() + "'" +
			", pv='" + getPv() + "'" +
			"}";
	}

}
