package optique;

import java.awt.Color;
import algLin.Point3;
import objets.editable.Editable;
import objets.scene.Objet;
import objets.scene.Stageable;

public abstract class Source extends Objet implements Editable{


	public Source(String nom) {
		super(nom);
	}

	public abstract CouleurL getCouleurL();
	public abstract Photon getInfluence(Point3 p, Stageable s);

	public abstract Point3 getPoint();

	public Color getRGBColor(Stageable s) {
		return getCouleurL().appliquerIntGlobale(s.getBlanc());
	}


}
