package auxMaths.transformations;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.fonctionsVectorielles.BijectionR3R3;

/**Modélise les transformations de l'espace par une classe de fonctions déductibles l'une de l'autre par translation.
 * Une classe est donc caractérisée par une fonction vectorielle tandis qu'un représentant l'est par le point "origine"
 * 
 * @author Adel
 *
 */
public class TransformationAffinisee implements TransformationEspace{

	Point3 pointOrigine;
	BijectionR3R3 fonctionVect;
	
	public TransformationAffinisee(Point3 origine, BijectionR3R3 f) {
		fonctionVect =f;
		pointOrigine = origine;
	}
	
	
	
	
	public Point3 agirSur(Point3 p) {
		return pointOrigine.plus(fonctionVect.fonction(pointOrigine.Vecteur(p)));
	}

	
	public TransformationAffinisee inverse() {
		return new TransformationAffinisee(pointOrigine, fonctionVect.inverse());
	}

}
