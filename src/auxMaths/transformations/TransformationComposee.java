package auxMaths.transformations;

import auxMaths.algLin.Point3;

/**Implémente la composition de deux transformations. t1 rond t2
 * 
 * @author Adel
 *
 */
public class TransformationComposee implements TransformationEspace {

	TransformationEspace t1,t2;
	
	public TransformationComposee(TransformationEspace t1 , TransformationEspace t2) {
		this.t1=t1;
		this.t2=t2;
	}



	@Override
	public Point3 agirSur(Point3 p) {
		return t1.agirSur(t2.agirSur(p));
	}

	@Override
	public TransformationEspace inverse() {
		return new TransformationComposee(t2.inverse(),t1.inverse());
	}}
