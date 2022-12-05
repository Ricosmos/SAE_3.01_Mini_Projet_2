package Splendor.ihm;

import Splendor.Controleur;
import Splendor.metier.*;

import java.util.Map;

import javax.swing.table.AbstractTableModel;

public class GrilleDonneesModel extends AbstractTableModel
{
	private Controleur ctrl;

	final private String[] TAB_EN_TETES = { "Couleur", "Quantités" };

	private Object[][] tabDonnees;

	public GrilleDonneesModel (Controleur ctrl, Joueur joueur)
	{
		this.ctrl = ctrl;

		this.tabDonnees = new Object[9][2];
	
		int i = 0;
		for(Map.Entry<String, Integer> entry : joueur.getTabJetons().entrySet()) 
		{
			String key = entry.getKey();
			Integer value = entry.getValue();
		
			this.tabDonnees[i][0] = key;
			this.tabDonnees[i][1] = value;
			i++;
		}
	}

	public int    getColumnCount()                 { return this.TAB_EN_TETES.length;  }
	public int    getRowCount   ()                 { return this.tabDonnees.length;    }
	public String getColumnName (int col)          { return this.TAB_EN_TETES[col];    }
	public Object getValueAt    (int row, int col) { return this.tabDonnees[row][col]; }
}