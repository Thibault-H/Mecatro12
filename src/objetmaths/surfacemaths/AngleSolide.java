package objetmaths.surfacemaths;

import auxMaths.algLin.M3;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import objetmaths.volumemaths.VolumeMath;

public class AngleSolide implements VolumeMath {

	Point3 origine;
	double theta1, theta2, phi1, phi2;
	M3 base;
	
	public AngleSolide(Point3 o, double theta1, double theta2, double phi1, double phi2) {
		origine =o;
		if (theta1 <= theta2) {
			this.theta1=theta1;
			this.theta2=theta2;
		}
		else {
			this.theta1=theta2;
			this.theta2=theta1;
		}
		if (phi1 <= phi2) {
			this.phi1=phi1;
			this.phi2=phi2;
		}
		else {
			this.phi1=phi2;
			this.phi2=phi1;
		}
		base = M3.id;
	}
	
	public AngleSolide(Point3 o,  R3 directionRef, double deltaThetaSur2, double deltaPhiSur2) {
		origine = o;
		double thetaRef= directionRef.getTheta();
		double phiRef = directionRef.getPhi();
		if (deltaThetaSur2 >=0) {
			theta1=thetaRef-deltaThetaSur2;
			theta2=thetaRef+deltaThetaSur2;
		}
		else {
			theta1=thetaRef+deltaThetaSur2;
			theta2=thetaRef-deltaThetaSur2;
		}
		if (deltaPhiSur2 >=0) {
			phi1=phiRef-deltaPhiSur2;
			phi2=phiRef+deltaPhiSur2;
		}
		else {
			phi1=phiRef+deltaPhiSur2;
			phi2=phiRef-deltaPhiSur2;
		}
	}
	
	public AngleSolide(Point3 o, double deltaThetaSur2, double deltaPhiSur2, double thetaRef, double phiRef, boolean y) {
		origine = o;
		theta1=thetaRef-deltaThetaSur2;
		theta2=thetaRef+deltaThetaSur2;
		phi1=phiRef-deltaPhiSur2;
		phi2=phiRef+deltaPhiSur2;
	}	
	
	@Override
	public boolean estDedans(Point3 p) {
		boolean result;
		try {
			R3 v = origine.Vecteur(p);
			double theta= v.getTheta();
			double phi = v.getPhi();
			result= (theta1<=theta && theta <=theta2 && phi1<=phi && phi<=phi2);
		} catch (IllegalArgumentException e) {
			result = true;
		}
		return result;
	}
	
	public static void main(String[] args) {
		Point3 m = Point3.origine.plus(R3.uz.prod(2));
		Point3 p = Point3.origine;
		AngleSolide ang = new AngleSolide(m,R3.uz.opp(),Math.PI/4,Math.PI/4);
		System.out.println(ang.estDedans(p));
		System.out.println(ang.phi1/Math.PI);
	}

}
