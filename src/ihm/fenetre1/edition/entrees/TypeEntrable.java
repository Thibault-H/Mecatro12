package ihm.fenetre1.edition.entrees;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import ihm.fenetre1.edition.Entrable;
import ihm.fenetre1.edition.PanelEntrable;
import ihm.fenetre1.edition.ihmEntrees.EntreeCouleur;
import ihm.fenetre1.edition.ihmEntrees.EntreePoint;
import ihm.fenetre1.edition.ihmEntrees.EntreeScal;
import ihm.fenetre1.edition.ihmEntrees.EntreeVect;
import optique.lumiere.CouleurL;

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
	
	public PanelEntrable entreeDefaut(String name) {
		switch (this) {
		case Vecteur: return new EntreeVect(name, false);
		case Point: return new EntreePoint(name);
		case Scalaire: return new EntreeScal(name);
		case Couleur: return new EntreeCouleur(name,false);
		default : throw new IllegalArgumentException("probleme de switch");
		}
	}
}
