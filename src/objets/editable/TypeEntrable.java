package objets.editable;

import algLin.Point3;
import algLin.R3;
import optique.CouleurL;

public enum TypeEntrable {
	Vecteur,
	Scalaire,
	Couleur,
	Point;
	
	public Entrable valeurDefaut() {
		switch (this) {
		case Vecteur: return new Vecteur(R3.zero);
		case Point: return new Point(Point3.origine);
		case Scalaire: return new Scalaire(0);
		case Couleur: return new Couleur(CouleurL.noir);
		default : throw new IllegalArgumentException("probleme de switch");
		}
	}
}
