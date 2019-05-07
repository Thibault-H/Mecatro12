package optique.sources;

import auxMaths.algLin.Point3;
import optique.Source;
import optique.sources.obstruction.ObstructionBasique;

public abstract class PointLumineux extends Source{

	/**
	 * 
	 */
	private static final long serialVersionUID = 930683683738444171L;
	Point3 position;
	
	public PointLumineux(String nom, Illumination i, Point3 p) {
		super(nom,i, new ObstructionBasique(p));
		position = p;
	}
	

	
	
	public Point3 getPoint() {
		return position;
	}
	
	

}

