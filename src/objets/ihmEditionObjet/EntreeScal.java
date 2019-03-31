package objets.ihmEditionObjet;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JTextField;

import objets.editable.Entrable;
import objets.editable.Scalaire;
import objets.editable.TypeEntrable;

public class EntreeScal extends Entree{
	JTextField champ;



	public EntreeScal(String name, Entrable e) {
		this(name);
		majValeur(e);
	}

	public EntreeScal(String name) {
		super(name, TypeEntrable.Scalaire);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		champ= new JTextField();
		champ.setColumns(1);
		champ.setText("0");
		
		c.gridx=1;
		c.gridy=1;
		c.ipadx=10;
		
		add(champ,c);
		
		
		derniereValeurLue=new Scalaire(0);
	}


	public double getValue() {
		try {
			return Double.parseDouble(champ.getText());
		}
		catch (NumberFormatException e) {
			System.out.println("EntrÃ©e non reconnue. Veuillez entrer un nombre.");
			return ((Scalaire) derniereValeurLue).getValue();
		}
	}

	
	@Override
	public void resetAffichage() {
		champ.setText(""+ ((Scalaire)derniereValeurLue).getValue());
	}

	@Override
	public void setEditable(boolean b) {
		champ.setEditable(b);
	}
	
	@Override
	public int getNombreCol() {
		return 3;
	}
	
	@Override
	public int getNombreLig() {
		return 3;
	}

	@Override
	public void lireEntree() {
		try {
			derniereValeurLue.conformerA(new Scalaire(Double.parseDouble(champ.getText())) );
		}
		catch (NumberFormatException e) {
			System.out.println("Entrée non reconnue. Veuillez entrer un nombre.");
		}
	}
}
