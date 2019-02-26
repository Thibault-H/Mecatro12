package corps;

import algLin.M3;
import algLin.Point3;
import algLin.R3;

public class ParametresMecatro extends Parametres{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3615617808585208604L;
	double dist;
	
	
	public ParametresMecatro(double largeur) {
		super();
		setLarg(largeur);
	}
	
	public Point3 pixToPoint3(int abscisse, int cote,Point3 originePlan, R3 n) {
		M3 base = n.base();
		return originePlan.plus( base.getC1().prod((abscisse-largpx/2)*ratio)).plus( base.getC3().prod((cote-hautpx/2)*ratio));
	}
}
