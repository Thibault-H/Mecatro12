package auxMaths.fonctionsVectorielles;

import java.util.function.Function;

import auxMaths.algLin.M3;
import auxMaths.algLin.O3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;

public class ProjectionVectorielle implements Endomorphisme{

	public static final boolean AXIALE = false;
	public static final boolean PLANE = true;
	
	VectUnitaire v;
	Function<R3,R3> corps;
	
	public ProjectionVectorielle(VectUnitaire vect, boolean type) {
		v=vect;
		if (type)		//PLANE
			corps = b -> b.moins(vect.prod(b.scal(vect)));			// b - (b.vect)vect	
		else			//AXIALE
			corps = b ->  vect.prod(b.scal(vect));					//(b.vect)vect
			
	}
	
	
	
	@Override
	public R3 fonction(R3 r) {
		return corps.apply(r);
	}

	



	@Override
	public O3 getMatrice() {
		// TODO Auto-generated method stub
		return O3.casterOrthogonale(new M3(fonction(R3.ux),fonction(R3.uy),fonction(R3.uz)));
	}

}
