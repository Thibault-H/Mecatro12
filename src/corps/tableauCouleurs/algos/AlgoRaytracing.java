package corps.tableauCouleurs.algos;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import corps.tableauCouleurs.parametres.ParametresRaytracing;
import objets.ObjetRaytracing;
import objets.scene.Stageable;
import optique.Photon;
import optique.lumiere.CouleurL;

public class AlgoRaytracing extends AlgoColorPoint{
	//Point3 pointDepart;
	R3 dir;
	Stageable s;
	int nbReflexions;
	Point3 oeil;




	public AlgoRaytracing(ParametresRaytracing param, Stageable sc) {
		super(param, param.getCentre(),param.getBase());
		s=sc;
		nbReflexions = param.getMiroir();
		oeil = param.getOeil();
	}




	@Override
	protected CouleurL getCouleurPoint(Point3 pnt) {
		dir=oeil.Vecteur(pnt).normer();
		Photon phot= new Photon(pnt, s);
		ObjetRaytracing renc = phot.avancer(dir);
		return renc.getColor(phot.getPosition(), dir,s, nbReflexions);
	}
}
