package optique;

import java.util.HashMap;
import java.util.Map;

import auxMaths.algLin.Point3;
import ihm.fenetre1.edition.Entrable;
import ihm.fenetre1.edition.entrees.Couleur;
import optique.lumiere.CouleurL;
import optique.sources.illumination.IlluminationPonctuelle;
import optique.sources.obstruction.ObstructionBasique;

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

	//==================================
	// Edition


	@Override
	public Map<String,Entrable> getAttributsEditables() {
		return new HashMap<String,Entrable>(attributs);
	}

	@Override
	public void majListeAttributs() {
		//attributs.put("Portee", new Scalaire(rayonIntMax));
		attributs.put("Lumiere", new Couleur(getLumiereCentrale()) );

	}

	@Override
	public void maj() {
		//rayonIntMax = ((Scalaire) attributs.get("Portee")).getValue();
		CouleurL lum = ((Couleur) attributs.get("Lumiere")).getValueL();

		illum = new IlluminationPonctuelle(Point3.origine, lum, 1.0/3.544908);
		voil = new ObstructionBasique(Point3.origine);

	}


}
