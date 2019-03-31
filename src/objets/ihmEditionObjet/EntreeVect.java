package objets.ihmEditionObjet;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextField;

import algLin.R3;
import ihm.Programme;
import objets.editable.Entrable;
import objets.editable.TypeEntrable;
import objets.editable.Vecteur;

public class EntreeVect extends Entree {

	/**
	 * 
	 */
	private static final long serialVersionUID = -878183700783514096L;
	JTextField[] tab;
	protected Programme p;
	Boolean orientationFenetre;



	public EntreeVect(String nom, boolean orientation) {
		super(nom, TypeEntrable.Vecteur);
		this.orientationFenetre=orientation;
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
				
		JPanel pan= new JPanel(new GridBagLayout());
		GridBagConstraints cBis = new GridBagConstraints();

		tab = new JTextField[3];
		
		cBis.gridx=0;
		cBis.gridy=0;
		cBis.ipady=10;
		cBis.ipadx=10;
		cBis.insets = new Insets(0,0,0,0);
		for (int i=0; i<=2;i++) {
			tab[i]=new JTextField();
			tab[i].setColumns(1);
			tab[i].setText("0");
			if (orientation) cBis.gridx=i;
			else cBis.gridy=i;
			pan.add(tab[i],cBis);
		}
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		add(pan, c);
		
	}
	
	
	public EntreeVect(String nom, Entrable valeurInit, boolean orientation) {
		this(nom, orientation);
		majValeur(valeurInit); 
	}

	@Override
	public void lireEntree() {
	    try {
	        derniereValeurLue.conformerA(new R3(Double.parseDouble(tab[0].getText()),Double.parseDouble(tab[1].getText()), Double.parseDouble(tab[2].getText())));
	      }
	      catch (NumberFormatException e) {
	        System.out.println("Entrée non reconnue. Veuillez entrer des nombres.");
	      }
	}
	
	@Override
	public void resetAffichage() {
		for (int i =0; i<3 ; i++) {
			tab[i].setText(""+ ((Vecteur)derniereValeurLue).getValue().getVal(i+1));
		}
	}



	@Override
	public void setEditable(boolean RorW) {
		for (JTextField o:tab)
		      o.setEditable(RorW);
	}


	@Override
	public int getNombreCol() {
		// TODO Auto-generated method stub
		return 3;
	}


	@Override
	public int getNombreLig() {
		// TODO Auto-generated method stub
		return 5;
	}


	
}
