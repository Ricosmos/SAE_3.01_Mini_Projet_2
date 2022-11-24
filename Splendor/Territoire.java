/**
 * Territoire
 */
public class Territoire 
{
	private Color couleur;
	private String nom;
	private int x;
	private int y;


	public Territoire(Color couleur, String nom, int x, int y) 
	{
		this.couleur = couleur;
		this.nom     = nom;
		this.x       = x;
		this.y       = y;
	}

	public Color getCouleur() { return this.couleur; }
	public String getNom() 	  { return this.nom; }
	public int getX() 		  { return this.x; }
	public int getY() 		  {	return this.y; }

	@Override
	public String toString() 
	{
		return "{" +
			" couleur='" + getCouleur() + "'" +
			", nom='" + getNom() + "'" +
			", x='" + getX() + "'" +
			", y='" + getY() + "'" +
			"}";
	}

	
}