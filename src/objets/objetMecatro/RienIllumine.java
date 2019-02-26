package objets.objetMecatro;

import algLin.Point3;

public class RienIllumine extends VolumeIllumine {

	public RienIllumine() {
		
	}
	
	@Override
	protected double fonctionIllumination(Point3 p) {
		// TODO Auto-generated method stub
		return 1;
	}
	
	public double getIntensiteRecue(Point3 p) {
		return 0; 
	}

}
