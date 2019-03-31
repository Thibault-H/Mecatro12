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
	
	/** Renvoie le point du rectangle direct (base.C2, base.C3) dans l'espace qui correspond aux coordonnées discretes (abscisse, cote)
	 * Remarque: (abscisse=0,cote=0) <=> coin supérieur gauche dans cette base directe 
	 * @param abscisse
	 * @param cote
	 * @param originePlan
	 * @param base
	 * @return
	 */
	private Point3 pixToPoint3(int abscisse, int cote,Point3 originePlan, M3 base) {
		//Vecteur qui relie l'origine du plan au point de coordonnées discrètes données
		R3 PM = base.fois(new R3(0, largpx/2 - abscisse, hautpx/2 -cote).prod(ratio));
		return originePlan.plus(PM);
	}
	
	public Point3 pixToPoint3(int abscisse, int cote,Point3 originePlan, R3 n) {
		M3 base = n.base();
		return pixToPoint3(abscisse, cote, originePlan, base);
	}
	
	
	public static void main(String[] args) {
		ParametresMecatro p = new ParametresMecatro(10);
		System.out.println(p.pixToPoint3(0,0,Point3.origine,M3.id));
	}
}
