package objets.ihmEditionObjet;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;

import algLin.R3;
import ihm.Programme;
import objets.editable.Entrable;
import objets.editable.TypeEntrable;
import objets.editable.Vecteur;

public class EntreeR3 extends Entree {

	/**
	 * 
	 */
	private static final long serialVersionUID = -878183700783514096L;
	TextField[] tab;
	protected Programme p;
	Boolean orientationFenetre;



	public EntreeR3(String nom, boolean orientation) {
		super(nom, TypeEntrable.Vecteur);
		this.orientationFenetre=orientation;
		setLayout(new GridLayout(1,2));
		Panel pan= new Panel();
		if (orientationFenetre) 
			pan.setLayout(new GridLayout(1,3));
		else 
			pan.setLayout(new GridLayout(3,1));
		tab = new TextField[3];
		for (int i=0; i<=2;i++) {
			tab[i]=new TextField();
			tab[i].setColumns(1);
			tab[i].setText("0");
			pan.add(tab[i]);
		}
		add(pan);
	}
	
	
	public EntreeR3(String nom, Entrable valeurInit, boolean orientation) {
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
	protected void majValeur(Entrable e) {
		super.majValeur(e);
		for (int i =0; i<3 ; i++) {
			tab[i].setText(""+ ((Vecteur)derniereValeurLue).getValue().getVal(i+1));
		}
	}



	@Override
	public void setEditable(boolean RorW) {
		for (TextField o:tab)
		      o.setEditable(RorW);
	}

	
}
