package objetmaths.surfacemaths;

import algLin.M3;
import algLin.Point3;
import algLin.R3;
import objets.AFaireTourner;

public abstract class SurfacePlaneConvexe implements SurfMath, AFaireTourner{
	Degre1 planBase;
	
	@Override
	public R3 getNorm(Point3 m) {
		return planBase.getNorm(m);
	}
	
	/* On suppose que m est coplanaire à la surface
	 * 
	 */
	protected abstract boolean estDedans(Point3 m);
	
	
	@Override
	public double dist(Point3 m, R3 d) {
		double l = planBase.dist(m, d);
		if (l< Double.POSITIVE_INFINITY) {
			Point3 projection = m.plus(d.normer().prod(l));
			if (estDedans(projection))
					return l;
			else
				return Double.POSITIVE_INFINITY;
		}
		else
			return Double.POSITIVE_INFINITY;
	}
	
	@Override
	public abstract M3 getBaseRef() ;
}
