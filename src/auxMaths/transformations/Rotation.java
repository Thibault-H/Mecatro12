package auxMaths.transformations;

import auxMaths.algLin.Point3;
import auxMaths.algLin.VectUnitaire;
import auxMaths.fonctionsVectorielles.RotationAxiale;
import auxMaths.fonctionsVectorielles.RotationVectorielle;

public class Rotation extends TransformationAffinisee {


	
	public Rotation(Point3 origine, RotationVectorielle f) {
		super(origine, f);
	}
	

	public Rotation(Point3 pt, VectUnitaire axe, double theta) {
		this(pt,new RotationAxiale(theta,axe));
	}

}
