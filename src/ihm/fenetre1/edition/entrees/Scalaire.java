package ihm.fenetre1.edition.entrees;

import ihm.fenetre1.edition.Entrable;

public class Scalaire implements Entrable{
	private double value;
	
	public Scalaire(double largeur) {
		value=largeur;
	}

	public double getValue() {
		return value;
	}
	@Override 
	public TypeEntrable getTypeEntrable() {
		return TypeEntrable.Scalaire;
	}

	@Override
	public boolean conformerA(Object... input) {
		if (input.length==1) {
			try {
				value = ((Scalaire) input[0]).value;
			} 
			catch (ClassCastException e) {
				try {
					value = (double) input[0];
				}
				catch (ClassCastException e2) {
					return false;
				}
			}
			return true;
		}
		else return false;		
	}
}
