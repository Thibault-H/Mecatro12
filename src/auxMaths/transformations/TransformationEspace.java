package auxMaths.transformations;

import auxMaths.algLin.Point3;

public interface TransformationEspace {
	public Point3 agirSur(Point3 p);
	public TransformationEspace inverse();
	
	public default TransformationEspace rond(TransformationEspace t2) {
		return new TransformationComposee(this,t2);
	}
}
