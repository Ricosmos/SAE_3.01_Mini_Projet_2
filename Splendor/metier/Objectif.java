package Splendor.metier;

public class Objectif 
{
	private String objectifCarte;
	private int    type;
	private Integer    nbTerritoire;
	private Integer    nbParcelle;
	private String couleur1;
	private String couleur2;
	private String couleur3;
	private int    nbPV;
	
	/* type = 1 -> Posséder x territoires si nbPacerelle -> de x parcelles
	 *								      sinon 	     -> si couleur2 -> couleur1 à côté de couleur2
	 *														sinon			   -> couleur1
	 * 		= 2 -> Posséder le même nombre de territoires -> couleur1, couleur2, couleur3
	 * 		= 3 -> Etre majoritaire sur les  -> couleur1
	 * 		= 4 -> Posséder aucun territoire -> couleur1
	 */
	public Objectif(int type, Integer nbTerritoire, Integer nbParcelle, String couleur1, String couleur2, String couleur3, int nbPV) 
	{
		this.type = type;
		this.nbTerritoire = nbTerritoire;
		this.nbParcelle   = nbParcelle;
		this.couleur1 = couleur1;
		this.couleur2   = couleur2;
		this.nbPV = nbPV;

		this.objectifCarte = "";
		switch (this.type) {
			case 1:
				this.objectifCarte += "Posséder " + this.nbTerritoire + " territoires ";
				if (this.nbParcelle != null)
				{
					this.objectifCarte += "de " + nbParcelle + " parcelles";
				}
				else
				{
					if (this.couleur2 != null)
					{
						this.objectifCarte += couleur1 + " à côté d'un " + couleur2;
					}
					else
					{
						this.objectifCarte += couleur1;
					}
				}
				break;
			case 2:
				this.objectifCarte += "Posséder le même nombre de territoires " + couleur1 + ", " + couleur2 + " et " + couleur3;
				break;
			case 3:
				this.objectifCarte += "Etre majoritaire sur les " + couleur1;
				break;
			case 4:
				this.objectifCarte += "Posséder aucun territoire " + couleur1;
				break;
			default:
				this.objectifCarte = "ERREUR";
				break;
		}
	}

	public String getObjectifCarte() {return this.objectifCarte;}
	public int getType() {return this.type;}
	public Integer getNbTerritoire() {return this.nbTerritoire;}
	public Integer getNbParcelle() {return this.nbParcelle;}
	public String getCouleur1() {return this.couleur1;}
	public String getCouleur2() {return this.couleur2;}
	public String getCouleur3() {return this.couleur3;}
	public int getNbPV() {return this.nbPV;}

	@Override
	public String toString() {
		return this.objectifCarte + " " + this.nbPV + " PV";
	} 
}