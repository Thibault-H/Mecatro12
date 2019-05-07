package ihm.fenetre1.edition.ihmEntrees;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import ihm.fenetre1.edition.Entrable;
import ihm.fenetre1.edition.PanelEntrable;
import ihm.fenetre1.edition.entrees.Point;
import ihm.fenetre1.edition.entrees.TypeEntrable;
import ihm.fenetre1.edition.entrees.Vecteur;

public class EntreePoint extends PanelEntrable {

	Point3 reference = Point3.origine;
	protected EntreeVect entreeValeur;
	JButton advanced;
	
	public EntreePoint(String nom) {
		super(nom, TypeEntrable.Point);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		entreeValeur = new EntreeVect("",false);
		entreeValeur.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));	//Pn supprime les bordures habituelles
		
		c.gridheight=3;
		c.gridx=0;
		c.gridy=0;
		add(entreeValeur,c);
		
		
		advanced = new JButton("Plus...");
		
		c.gridheight = 1;
		c.gridx=1;
		c.gridy=0;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.insets = new Insets(10,0,0,0);
		add(advanced,c);
		
	}

	public EntreePoint(String nom, Entrable valeurInit, boolean orientation) {
		this(nom);
		majValeur(valeurInit); 
	}
	
	
	@Override
	protected void majValeur(Object e) {
		derniereValeurLue.conformerA(e);
		PanelEntrable.majValeur( entreeValeur , reference.Vecteur( ((Point)derniereValeurLue).getValue()) );
		resetAffichage();
	}
	
	
	@Override
	public void resetAffichage() {
		entreeValeur.resetAffichage();
	}
	
	@Override
	public int getNombreCol() {
		return entreeValeur.getNombreCol()+1;
	}

	@Override
	public int getNombreLig() {
		return entreeValeur.getNombreLig()+1;
	}

	@Override
	public void lireEntree() {
		R3 r = ((Vecteur) (PanelEntrable.getValeurLue(entreeValeur)) ).getValue();
		derniereValeurLue.conformerA(reference.plus(r));
	}

	@Override
	public void setEditable(boolean RorW) {
		// TODO Auto-generated method stub
		entreeValeur.setEditable(RorW);
	}

}
