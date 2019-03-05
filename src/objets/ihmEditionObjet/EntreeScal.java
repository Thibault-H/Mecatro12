package objets.ihmEditionObjet;

import java.awt.TextField;

import objets.editable.Entrable;
import objets.editable.Scalaire;
import objets.editable.TypeEntrable;

public class EntreeScal extends Entree{
	TextField champ;



	public EntreeScal(String name) {
		this(name, TypeEntrable.Scalaire.valeurDefaut());
	}

	public EntreeScal(String name, Entrable e) {
		super(name, e.getTypeEntrable());
		champ= new TextField();

		majValeur(e);
		add(champ);
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
	protected void majValeur(Entrable e) {
		super.majValeur(e);
		champ.setText(""+ ((Scalaire)derniereValeurLue).getValue());
	}

	@Override
	public void setEditable(boolean b) {
		champ.setEditable(b);
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
