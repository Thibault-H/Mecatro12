package objets.editable;

import algLin.R3;

public class Vecteur implements Entrable {

	private R3 value;
	
	public Vecteur(R3 largeur) {
		value=largeur;
	}

	public R3 getValue() {
		return value;
	}
	@Override 
	public TypeEntrable getTypeEntrable() {
		return TypeEntrable.Vecteur;
	}

	@Override
	public boolean conformerA(Object... input) {
		switch (input.length) {
		case 1 :if (input[0] instanceof Vecteur)
					value = ((Vecteur) input[0]).value;
				else if (input[0] instanceof R3)
					value = (R3) input[0];
				else return false;
				return true;
		case 3 :try {value = new R3((double)input[0], (double)input[1], (double)input[2]);
				}
				catch (ClassCastException e2) {
					return false;}
				return true;
		default : return false;
		}
	}

}
