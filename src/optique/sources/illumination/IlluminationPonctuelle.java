package optique.sources.illumination;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import optique.lumiere.CouleurL;
import optique.lumiere.Lumiere;
import optique.lumiere.Poynting;
import optique.sources.Illumination;

/**Modèle d'une illumination ponctuelle de base : décroissance en 1/r² de la puissance surfacique
 * 
 * @author Adel
 *
 */
public class IlluminationPonctuelle implements Illumination{

	Point3 reference;			//Le point où la lumpière est la plus intense
	double rayonInfluenceCarre;		//Le carre du rayon d une sphere de référence dite d'influence où l'intensité est uniforme est vaut couleur
	CouleurL couleur;			//La couleur de la lumiere sur la sphère d'influence
	
	public IlluminationPonctuelle(Point3 ref, CouleurL lum, double rayon) {
		reference = ref;
		couleur = lum;
		rayonInfluenceCarre = rayon*rayon;
	}
	

	
	public CouleurL getCouleur() {
		return couleur;
	}
	
	@Override
	public Lumiere champLumiere(Point3 pt) {
		if (pt.equals(reference))
			return Lumiere.noir;
		else {
			R3 om = reference.Vecteur(pt);
			return new Poynting(om,couleur.multiplieIntensite(1/om.norme2car()));
		}
	}

}
