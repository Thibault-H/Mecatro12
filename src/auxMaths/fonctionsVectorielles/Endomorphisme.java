package auxMaths.fonctionsVectorielles;

import auxMaths.algLin.M3;
import auxMaths.algLin.R3;

public interface Endomorphisme extends FonctionR3R3 {
	public default M3 getMatrice() {
		return new M3(fonction(R3.ux),fonction(R3.uy),fonction(R3.uz));
	}
}
