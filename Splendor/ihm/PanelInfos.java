package Splendor.ihm;

import Splendor.Controleur;

import javax.swing.*;
import java.awt.BorderLayout;

public class PanelInfos extends JPanel
{
	private Controleur ctrl;
	private JTable tableJoueur1;
	private JTable tableJoueur2;

	public PanelInfos(Controleur ctrl) 
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		JScrollPane spTabJoueur1;
		JScrollPane spTabJoueur2;

		JPanel panelJoueur1 = new JPanel();
		JPanel panelJoueur2 = new JPanel();

		this.tableJoueur1 = new JTable(new GrilleDonneesModel(ctrl, this.ctrl.getJoueur1()));
		this.tableJoueur2 = new JTable(new GrilleDonneesModel(ctrl, this.ctrl.getJoueur2()));

		
		spTabJoueur1 = new JScrollPane( this.tableJoueur1 );
		spTabJoueur2 = new JScrollPane( this.tableJoueur2 );

		panelJoueur1.add(new JLabel("Joueur 1"));
		panelJoueur1.add(spTabJoueur1);
		panelJoueur2.add(new JLabel("Joueur 2"));
		panelJoueur2.add(spTabJoueur2);

		this.add(panelJoueur1, BorderLayout.NORTH);
		this.add(panelJoueur2, BorderLayout.SOUTH);
	}

	public void majIHM()
	{
		this.tableJoueur1.setModel(new GrilleDonneesModel(ctrl, this.ctrl.getJoueur1()));
		this.tableJoueur2.setModel(new GrilleDonneesModel(ctrl, this.ctrl.getJoueur2()));
	}	
}
