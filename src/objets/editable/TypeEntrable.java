package objets.editable;

import algLin.Point3;
import algLin.R3;
import objets.ihmEditionObjet.Entree;
import objets.ihmEditionObjet.EntreeCouleur;
import objets.ihmEditionObjet.EntreePoint;
import objets.ihmEditionObjet.EntreeVect;
import objets.ihmEditionObjet.EntreeScal;
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
	
	public Entree entreeDefaut(String name) {
		switch (this) {
		case Vecteur: return new EntreeVect(name, false);
		case Point: return new EntreePoint(name);
		case Scalaire: return new EntreeScal(name);
		case Couleur: return new EntreeCouleur(name,false);
		default : throw new IllegalArgumentException("probleme de switch");
		}
	}
}
