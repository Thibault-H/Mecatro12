package auxMaths.fonctionsVectorielles;

import auxMaths.algLin.GL3;

public interface Automorphisme extends BijectionR3R3, Endomorphisme{
	@Override
	public BijectionR3R3 inverse();

	@Override
	public GL3 getMatrice();
}
