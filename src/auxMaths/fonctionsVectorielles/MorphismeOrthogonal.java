package auxMaths.fonctionsVectorielles;

import auxMaths.algLin.M3;
import auxMaths.algLin.O3;
import auxMaths.algLin.R3;

public interface MorphismeOrthogonal extends Automorphisme {
	@Override
	public MorphismeOrthogonal inverse();

	@Override
	public default O3 getMatrice() {
		return O3.casterOrthogonale( new M3(fonction(R3.ux),fonction(R3.uy),fonction(R3.uz)));
	}
}
