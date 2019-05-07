package auxMaths.fonctionsVectorielles;

import auxMaths.Quaternion;
import auxMaths.algLin.M3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;

public class RotationAxiale extends RotationVectorielle {
	double theta;
	VectUnitaire axe;
	
	
	//=========================================================
	
	public RotationAxiale(double theta, VectUnitaire axe) {
		super(new Quaternion(theta,axe,Quaternion.POLAIRE));
		this.theta = theta;
		this.axe = axe;
		
	}
	
	public RotationAxiale(double theta, R3 axe) {
		this(theta, new VectUnitaire(axe));
	}
	
	
	public double getTheta() {
		return theta;
	}
	
	public VectUnitaire getAxe() {
		return axe;
	}

	@Override
	public RotationAxiale inverse() {
		return new RotationAxiale(-theta,axe);
	}

}
