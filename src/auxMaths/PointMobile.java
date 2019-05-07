package auxMaths;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;

public class PointMobile extends Point3 {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7332297116200150420L;

	public PointMobile(Point3 p) {
		super(p);
	}
	
	public Point3 getValeurInstantanee() {
		return Point3.origine.plus(pos);
	}
	
	public void deplacerDe(R3 direction) {
		pos = pos.plus(direction);
	}
}
