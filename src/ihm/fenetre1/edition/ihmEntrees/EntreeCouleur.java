package ihm.fenetre1.edition.ihmEntrees;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import ihm.fenetre1.edition.Entrable;
import ihm.fenetre1.edition.PanelEntrable;
import ihm.fenetre1.edition.entrees.Couleur;
import ihm.fenetre1.edition.entrees.TypeEntrable;
import optique.lumiere.CouleurL;

public class EntreeCouleur extends PanelEntrable{
	JTextField champ;
	
	Color couleurAct;	//Hypothèse : à tout instant, cette couleur correspond à la couleur affichée et à la couleur enregistrée
	JButton choixCouleur;
	
	boolean estLumiere;


	public EntreeCouleur(String name, Entrable e, boolean estLumiere) {
		this(name, estLumiere);
		majValeur(e);
	}

	public EntreeCouleur(String name, boolean estLumiere) {
		super(name, TypeEntrable.Couleur);
		this.estLumiere = estLumiere;
		derniereValeurLue=TypeEntrable.Couleur.valeurDefaut();
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
				
		// Bouton de choix de couleur
		choixCouleur = new JButton();
		
		c.gridx=1;
		c.gridy=1;
		c.ipadx=20;
		c.fill = GridBagConstraints.VERTICAL;
		add(choixCouleur,c);
		
		choixCouleur.addActionListener(new afficherFenetreChoixCouleur(this));
		
		// Choix d'intensité

		champ= new JTextField();
		if (estLumiere) {
			champ.setColumns(1);
			champ.setText("1");
			
			c.gridx=1;
			c.gridy=2;
			c.ipadx=10;
			add(champ,c);
		}
		
	}




	
	@Override
	public void resetAffichage() {
		couleurAct = ((Couleur) derniereValeurLue).getValueS().getColor();
		choixCouleur.setBackground(couleurAct);
		if (estLumiere) 
			champ.setText("" + ((Couleur) derniereValeurLue).getValueL().getIntensite());	
	}
	

	@Override
	public void lireEntree() {
		try {
			//Hypothèse : couleurAct est toujours à jour
			if (estLumiere)
				derniereValeurLue.conformerA(new Couleur( new CouleurL(couleurAct, Double.parseDouble(champ.getText()))) );
			else
				derniereValeurLue.conformerA(couleurAct);
		}
		catch (NumberFormatException e) {
			System.out.println("Entrée non reconnue. Veuillez entrer un nombre.");
		}
	}
	
	@Override
	public void setEditable(boolean b) {
		champ.setEditable(b);
		choixCouleur.setEnabled(b);
	}
	
	@Override
	public int getNombreCol() {
		return 3;
	}
	
	@Override
	public int getNombreLig() {
		return estLumiere? 3 : 4;
	}


	
}


class afficherFenetreChoixCouleur implements ActionListener {
	
	EntreeCouleur princ;
	
	public afficherFenetreChoixCouleur(EntreeCouleur princ) {
		this.princ= princ;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		FenetreChoixCouleur.createAndShowGUI(princ);
	}
        
	
}

