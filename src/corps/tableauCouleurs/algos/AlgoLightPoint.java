package corps.tableauCouleurs.algos;

import auxMaths.algLin.O3;
import auxMaths.algLin.Point3;
import corps.tableauCouleurs.Parametres;
import optique.lumiere.CouleurL;
import optique.lumiere.Lumiere;

public abstract class AlgoLightPoint extends AlgoColorPoint {

	AlgoLightPoint(Parametres p, Point3 centreCadre, O3 baseRef) {
		super(p, centreCadre, baseRef);
	}

	protected abstract Lumiere getLumierePoint(Point3 p) ;
	
	
	@Override
	protected CouleurL getCouleurPoint(Point3 p) {
		return getLumierePoint(p).mesurerDiffus(base.getC1());
	}
	
	
	

}
