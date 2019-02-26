package objets.objetMecatro;

import algLin.Point3;
import algLin.R3;
import objetmaths.surfacemaths.RectangleMath;
import objetmaths.volumemaths.EspaceEntier;
import objetmaths.volumemaths.VolumePyramide;
import optique.Source;
import optique.SourcePonctuelleIsotrope;

public class DomaineIlluminePyramide extends VolumeIllumine{

	RectangleMath cadre;
	Point3 s;
	
	SourcePonctuelleIsotrope sourceVirtuelle;
	
	public DomaineIlluminePyramide(Point3 source, RectangleMath r) {
		cadre = r;
		s=source;
		maj();
	}
	
	public DomaineIlluminePyramide() {
		cadre = new RectangleMath(R3.ux,Point3.origine,0,0);
		s = Point3.origine;
		v= new EspaceEntier();
	}

	public void setSource(Point3 sour) {
		s=sour;
		maj();
	}
	
	public void setCadre(RectangleMath r) {
		cadre = r;
		maj();
	}
	
	private void maj() {
		sourceVirtuelle = new SourcePonctuelleIsotrope(s.symetrie(cadre.getPoint(), cadre.getNorm(Point3.origine)), 1);
		v = new VolumePyramide(sourceVirtuelle.getPoint(),cadre);
	}
	protected double fonctionIllumination(Point3 p) {
		return sourceVirtuelle.getInfluenceBasique(p);
	}



	
}
