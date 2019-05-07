package auxMaths.algLin;

public class VectUnitaire extends R3{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1373237295092454606L;

	
	/**A n'appeler que lorsque l'on est sûr du caractère normé.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	private VectUnitaire(R3 r, boolean b) {
		super(r);
	}
	
	/**Renvoie la version normée de r
	 * 
	 * @param r
	 */
	public VectUnitaire(R3 r) {
		super(r.auxNormer());
	}
	
	public static VectUnitaire extraireVect(O3 m, int i, boolean choixLigne) {
		if (choixLigne)
			return new VectUnitaire(M3.getL(m,i),true);
		else return new VectUnitaire(M3.getC(m,i),true);
	}
	

	
	//=================================================
	//Opérations orthogonales
	
	
	@Override
	public VectUnitaire opp() {
		return new VectUnitaire(super.opp());
	}
	
	
	@Override
	public VectUnitaire appliquerRot(R3 axe, double theta) {
		return new VectUnitaire(super.appliquerRot(axe,theta));
	}


	@Override
	public VectUnitaire symetrieOrth( R3 b) {
		return new VectUnitaire(super.symetrieOrth(b));
	}
	
	@Override
	public VectUnitaire symetrieOrth(VectUnitaire b) {
		return new VectUnitaire(super.symetrieOrth(b));
	}
	
	
	

}
