package objetmaths.volumemaths;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;

public class DemiEspace implements VolumeMath {

	R3 normal; 
	Point3 ptPart;
	
	/** Construit un demi-espace défini par l'équation (M-ptPart).normale >0
	 * 
	 * @param ptPart
	 * @param normale
	 */
	public DemiEspace(Point3 ptPart, R3 normale) {
		normal=normale;
		this.ptPart=ptPart;
	}
	
	@Override
	public boolean estDedans(Point3 p) {
		return normal.scal(ptPart.Vecteur(p))>=0;
	}
	
	public static boolean estDedans(Point3 p, Point3 ptPrt, R3 normale) {
		return normale.scal(ptPrt.Vecteur(p))>=0;
	}
	
	public static void main(String[] args) {
		DemiEspace e = new DemiEspace(Point3.origine, R3.uz);
		System.out.println(e.estDedans(Point3.origine.moins(R3.uz).plus(R3.uy)));
	}

}
