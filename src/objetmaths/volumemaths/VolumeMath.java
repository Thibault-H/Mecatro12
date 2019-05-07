package objetmaths.volumemaths;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;

@FunctionalInterface
public interface VolumeMath {
	
	public boolean estDedans(Point3 p) ;
	
	public default VolumeMath intersection(VolumeMath v2) {
		VolumeMath v = p -> estDedans(p)&&v2.estDedans(p);
		return v;
	}
	
	public static void main(String[] args) {
		VolumeMath v1 = new DemiEspace(Point3.origine, R3.ux);
		VolumeMath v2 = new DemiEspace(Point3.origine, R3.uy);
		Point3 p = Point3.origine.plus(new R3(-1,1,0));
		System.out.println(v1.intersection(v2).estDedans( p ));
	}

}
