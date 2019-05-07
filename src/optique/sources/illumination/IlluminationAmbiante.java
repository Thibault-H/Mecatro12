package optique.sources.illumination;

import auxMaths.algLin.Point3;
import optique.lumiere.CouleurL;
import optique.lumiere.Lumiere;
import optique.lumiere.LumiereDiffuse;
import optique.sources.Illumination;

public class IlluminationAmbiante implements Illumination {

	CouleurL valeur;
	
	public IlluminationAmbiante(CouleurL lum) {
		valeur = lum;
	}
	
	public CouleurL getCouleur() {
		return valeur;
	}
	
	@Override
	public Lumiere champLumiere(Point3 pt) {
		return new LumiereDiffuse(valeur);
	}

}
