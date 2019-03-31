package optique;

import algLin.Point3;
import objets.TypeObjet;
import objets.scene.Stageable;

public class Ambiant extends Source {

	CouleurL lum;

	public Ambiant(CouleurL l) {
		super("Lumière Ambiante");
		lum=l;
	}

	@Override
	public TypeObjet getTypeObjet() {
		return TypeObjet.LumiereAmbiante;
	}

	@Override
	public CouleurL getCouleurL() {
		return lum;
	}


	@Override
	public Photon getInfluence(Point3 p,Stageable s) {
		return new Photon(p,lum,s);  //null car la lumiere arrive partout dans la scene
	}


	@Override
	public Point3 getPoint() {
		// TODO Auto-generated method stub
		return null;
	}

}
