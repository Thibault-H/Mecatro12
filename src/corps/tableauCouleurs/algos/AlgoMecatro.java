package corps.tableauCouleurs.algos;

import auxMaths.algLin.Point3;
import corps.tableauCouleurs.parametres.ParametresMecatro;
import objets.scene.SceneMecatro;
import optique.lumiere.CouleurL;
import optique.lumiere.Lumiere;

public class AlgoMecatro extends AlgoLightPoint {

	SceneMecatro sc;
	
	public AlgoMecatro(ParametresMecatro p , SceneMecatro s ) {
		super(p, p.getCentre(), p.getBase());
		sc=s;
	}

	@Override
	protected Lumiere getLumierePoint(Point3 p) {
		return sc.getLumieresEn(p);
	}



}
