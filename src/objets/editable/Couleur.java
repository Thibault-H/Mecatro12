package objets.editable;

import java.awt.Color;

import algLin.Point3;
import algLin.R3;
import optique.CouleurL;
import optique.CouleurS;

public class Couleur implements Entrable {

	Color color;
	double i;
	
	public Couleur(Color c, double i) {
		color=c;
		this.i=i;
	}
	
	public Couleur(CouleurL l) {
		color= l.getColor();
		i = l.getIntensite();
	}
	
	public Couleur(CouleurS l) {
		color= l.getColor();
		i = Double.NaN;
	}
	
	

	public CouleurL getValueL() {
		if (((Double)i).isNaN())
			return new CouleurL(color,i);
		else throw new IllegalArgumentException("Impossible de créer une couleur lumineuse avec une intensite NaN!");
	}
	
	public CouleurS getValueS() {
		return new CouleurS(color);
	}
	
	@Override 
	public TypeEntrable getTypeEntrable() {
		return TypeEntrable.Couleur;
	}

	@Override
	public boolean conformerA(Object... input) {
		switch (input.length) {
		case 1 :if (input[0] instanceof CouleurL) {
					color = ((CouleurL) input[0]).getColor();
					i = ((CouleurL) input[0]).getIntensite();
				}
				else if (input[0] instanceof Couleur) {
					color = ((Couleur) input[0]).color;
					i = ((Couleur) input[0]).i;
				}
				else return false;
				return true;
		case 4 :try {
				CouleurL value = new CouleurL((double)input[0], (double)input[1], (double)input[2], (double)input[3]);
				color=value.getColor();
				i = value.getIntensite();
				}
				catch (ClassCastException e2) {
					return false;}
				return true;
		default : return false;
		}
	}

}
