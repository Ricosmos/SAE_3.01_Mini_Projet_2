import java.awt.Color;

public class JetonConcession 
{
	private Color couleur;

	public JetonConcession(Color couleur) 
	{
		this.couleur = couleur;
	}

	public Color getCouleur() {return this.couleur;}

	@Override
	public String toString() 
	{
		return "{" +
			" couleur='" + getCouleur() + "'" +
			"}";
	}
}
