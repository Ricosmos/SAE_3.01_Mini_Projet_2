package Splendor.ihm;

import Splendor.Controleur;

import javax.swing.*;
import java.awt.*;

public class FramePrincipale extends JFrame
{
	private Controleur ctrl;
	private PanelCarte panelCarte;

	public FramePrincipale (Controleur ctrl)
	{
		this.ctrl = ctrl;
		
		this.setTitle("La conquête du Splendor");
		this.setLayout(new BorderLayout());

		JPanel panelMain = new JPanel(new BorderLayout()); // Regroupe les panels
		this.panelCarte = new PanelCarte(this.ctrl);

		panelMain.add(this.panelCarte, BorderLayout.CENTER);
		this.add(panelMain);
		
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void majIHM() {this.panelCarte.majIHM();}	
}