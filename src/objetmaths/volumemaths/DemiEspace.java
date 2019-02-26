package objetmaths.volumemaths;

import algLin.Point3;
import algLin.R3;

public class DemiEspace implements VolumeMath {

	R3 normal; 
	Point3 ptPart;
	
	public DemiEspace(Point3 ptPart, R3 normale) {
		normal=normale;
		this.ptPart=ptPart;
	}
	
	public boolean estDedans(Point3 p) {
		return normal.scal(ptPart.Vecteur(p))>0;
	}
	
	public static boolean estDedans(Point3 p, Point3 ptPrt, R3 normale) {
		return normale.scal(ptPrt.Vecteur(p))>0;
	}
	
	public static void main(String[] args) {
		DemiEspace e = new DemiEspace(Point3.origine, R3.uz);
		System.out.println(e.estDedans(Point3.origine.plus(R3.uz).plus(R3.uy)));
	}

}
