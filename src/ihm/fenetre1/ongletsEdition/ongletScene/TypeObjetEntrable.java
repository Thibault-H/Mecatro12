package ihm.fenetre1.ongletsEdition.ongletScene;

import java.awt.Color;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import objets.CategorieObjet;
import objets.objetPhong.Plan;
import objets.objetPhong.Sphere;
import objets.scene.Objet;
import optique.SourcePonctuelleIsotrope;
import optique.lumiere.CouleurL;

public enum TypeObjetEntrable {
	SourcePonctuelle,
	Plan,
	Sphere;
	
	Point3 pointDefaut = Point3.origine;
	R3 vectDefaut = R3.zero;
	R3 vectNormeDefaut = R3.uy;
	Double scalaireDefaut = 1.0;
	Color couleurDefaut = Color.black;
	CouleurL lumiereDefaut = new CouleurL(Color.WHITE, 10);
	
	public Objet valeurDefaut() {
		Objet result;
		switch(this) {
		case SourcePonctuelle: 
			result = new SourcePonctuelleIsotrope("Source Ponctuelle", pointDefaut, lumiereDefaut);
			break;
			
		case Plan:
			result = new Plan("Plan", vectNormeDefaut, pointDefaut, couleurDefaut);
			break;
		case Sphere:
			result = new Sphere("Sphère", pointDefaut, scalaireDefaut, couleurDefaut);
			break;
		default : throw new IllegalArgumentException("probleme de switch");
		}
		return result;
	}
	
	public CategorieObjet getCategorie() {
		CategorieObjet result;
		switch(this) {
		case SourcePonctuelle: 
			result = CategorieObjet.Source;
			break;
			
		case Plan:
		case Sphere:
			result = CategorieObjet.Surface;
			break;
		default : throw new IllegalArgumentException("probleme de switch");
		}
		return result;
		
	}
}
