package optique.sources.illumination;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import optique.lumiere.CouleurL;
import optique.lumiere.Lumiere;
import optique.lumiere.Poynting;
import optique.sources.Illumination;

/**Le modèle d'un faisceau.
 * Décroissance de l'intensité basique des rayons en cos()**(s-1)
 * @author Adel
 *
 */
public class IlluminationSpeculaire implements Illumination{

	CouleurL coul;
	VectUnitaire dir;
	Point3 source;
	double s;
	
	public IlluminationSpeculaire(CouleurL intensite, Point3 source, VectUnitaire direction, double coefFocus) {
		coul=intensite;
		dir=direction;
		s=coefFocus;
		this.source= source;
	}
	
	public IlluminationSpeculaire(CouleurL intensite, Point3 source, R3 direction, double coefFocus) {
		this(intensite, source, new VectUnitaire(direction), coefFocus);
	}
	
	/**
	 * TODO : rendre ça moins artificiel 
	 * 
	 */
	@Override
	public Lumiere champLumiere(Point3 pt) {
		R3 om = source.Vecteur(pt);
		VectUnitaire v = new VectUnitaire(om);
		double facteur = Math.pow(v.scal(dir) , s-1);
		return new Poynting(dir, coul.multiplieIntensite( facteur ));
	}

}
