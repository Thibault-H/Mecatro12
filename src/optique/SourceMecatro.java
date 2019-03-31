package optique;

import algLin.Point3;
import objets.editable.Couleur;
import objets.editable.Point;

public class SourceMecatro extends SourcePonctuelleIsotrope {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 512733197655064666L;

	SourceMecatro(CouleurL l) {
		super("Source", Point3.origine, l);
	}
	
	SourceMecatro(double i) {
		super("Source", Point3.origine, i);
	}
	
	@Override
	public void majListeAttributs() {
		//attributs.put("Position", new Point(position));
		//attributs.put("Portee", new Scalaire(rayonIntMax));
		attributs.put("Lumiere", new Couleur(lum) );
		
	}

	@Override
	public void maj() {
		//position = ((Point) attributs.get("Position")).getValue();
		//rayonIntMax = ((Scalaire) attributs.get("Portee")).getValue();
		lum = ((Couleur) attributs.get("Lumiere")).getValueL();
		
	}

}
