package objets.objetMecatro;

import algLin.Point3;

public class EspaceEntierIllumine extends VolumeIllumine{

	public EspaceEntierIllumine() {
		
	}
	
	@Override
	protected double fonctionIllumination(Point3 p) {
		// TODO Auto-generated method stub
		return 1;
	}
	
	@Override
	public double getIntensiteRecue(Point3 p) {
		return 1; 
	}

}
