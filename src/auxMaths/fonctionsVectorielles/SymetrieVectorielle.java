package auxMaths.fonctionsVectorielles;

import java.util.function.Function;

import auxMaths.algLin.M3;
import auxMaths.algLin.O3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;

public class SymetrieVectorielle implements MorphismeOrthogonal{

	public static final boolean AXIALE = false;
	public static final boolean PLANE = true;
	
	VectUnitaire v;
	Function<R3,R3> corps;
	
	/**si type= Symetrie.AXIALE, alors pt et v définissent une droite
	 * si type = Symetrie.PLANE, alors pt et v définissent un plan
	 * @param pt
	 * @param v
	 * @param type
	 */
	public SymetrieVectorielle(VectUnitaire vect, boolean type) {
		v=vect;
		if (type)		//PLANE
			corps = b -> b.moins(vect.prod(2*b.scal(vect)));		// b - 2*(b.vect)vect	
		else			//AXIALE
			corps = b ->  vect.prod(2*b.scal(vect)).moins(b);		// 2*(b.vect)vect-b
			
	}
	
	
	
	@Override
	public R3 fonction(R3 r) {
		return corps.apply(r);
	}

	@Override
	public SymetrieVectorielle inverse() {
		return this;
	}



	@Override
	public O3 getMatrice() {
		// TODO Auto-generated method stub
		return O3.casterOrthogonale(new M3(fonction(R3.ux),fonction(R3.uy),fonction(R3.uz)));
	}

}
