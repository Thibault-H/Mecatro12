package auxMaths.algLin;

import auxMaths.TesteurNullite;
import auxMaths.fonctionsVectorielles.Endomorphisme;
import auxMaths.fonctionsVectorielles.SymetrieVectorielle;

public class O3 extends GL3{

	boolean isDirecte;		// true si det=1; false si det=-1


	//==========================================================
	//Constructeurs

	public O3() {
		super();		//l'identité
		isDirecte = true;
	}

	/**Construit une matrice orthogonale diagonale. true =>1 ; false =>-1
	 * 
	 * @param c1
	 * @param c2
	 * @param c3
	 */
	public O3(boolean c1, boolean c2, boolean c3) {
		super();
		if (!c1) v1=v1.opp();
		if (!c2) v2=v2.opp();
		if (!c3) v3=v3.opp();
		isDirecte = (c1^c2)^c3;
	}



	private O3(M3 m) {
		super(m);
		isDirecte = estDirecte();	//calcul de det
	}

	private O3(M3 m, boolean estDirecte) {
		super(m);
		isDirecte = estDirecte;
	}

	//======================================================
	//Construction


	public static O3 casterOrthogonale(M3 m) {
		if (m.estOrthogonale())
			return new O3(m);
		else throw new IllegalArgumentException("Matrice non orthogonale!");
	}


	/**Renvoie une bon directe dont le premier élément est vect.
	 * 
	 * @param vect
	 * @return
	 */
	public static O3 base(VectUnitaire vect) { 
		R3 u,v,w;

		u=vect;
		if ( !TesteurNullite.estNul(u.get1()) || !TesteurNullite.estNul(u.get2())) {
			v = new VectUnitaire(new R3( -u.get2(), u.get1(), 0));
			w = u.vect(v); 
		}
		else {
			u=R3.uz;
			v=R3.ux;
			w=R3.uy;
		}

		return new O3( new M3(u,v,w));
	}

	/**Renvoie une bon directe dont le premier élément est vect normé.
	 * 
	 * @param vect
	 * @return
	 */
	public static O3 base(R3 vect) {
		return base(new VectUnitaire(vect));
	}



	/**Revoie la matrice de rotation autour de axe et d'angle theta
	 * 
	 * @param axe
	 * @param theta
	 * @return
	 */
	public static O3 rotation(R3 axe, double theta) {
		O3 baseAdaptee= O3.base(axe);
		O3 rotation=new O3(new M3(R3.ux, new R3(0,Math.cos(theta),Math.sin(theta)), new R3(0,-Math.sin(theta), Math.cos(theta))), true);
		return baseAdaptee.fois(rotation).fois(baseAdaptee.transpose());
	}

	/**Renvoie la matrice de rotation correspondant Ã  la composition de 3 rotations successives selon chacun des axes de la base orthonormee donnÃ©e par "base"
	 * 
	 * @param val
	 * @param base
	 * @return
	 */
	public static O3 tripleRotation(R3 val, M3 base) {
		return rotation(base.getC1(),val.get1()).fois(rotation(base.getC2(),val.get2())).fois(rotation(base.getC3(),val.get1()));
	}


	public static O3 symetrie(SymetrieVectorielle s) {
		return new O3( ((Endomorphisme) s).getMatrice());
	}


	//======================================================
	//Opérations

	@Override
	public O3 inverse() {
		return transpose();
	}


	@Override
	public O3 transpose() {
		return new O3(M3.transposee(this),isDirecte);
	}


	@Override
	public O3 opp() {
		return new O3(M3.opp(this), !isDirecte);
	}

	/**Produit matriciel
	 * 
	 * @param b
	 * @return
	 */
	public O3 fois(O3 b) {
		return new O3(new M3( fois(b.getC1()) , fois(b.getC2()) , fois(b.getC3())) , isDirecte ^ b.isDirecte);
	}


	//=======================================================
	@Override
	public boolean estOrthogonale() {
		return true;
	}

	@Override
	public boolean estDirecte() {
		return isDirecte;
	}


	//===========================================================
	//TODO : les faire sortir des VectUnitaires
	//Recopie des getters

	@Override
	public VectUnitaire getC1() {
		return VectUnitaire.extraireVect(this, 1, false);
	}

	@Override 
	public VectUnitaire getC2() {
		return VectUnitaire.extraireVect(this, 2, false);
	}

	@Override 
	public VectUnitaire getC3() {
		return VectUnitaire.extraireVect(this, 3, true);
	}


	@Override 
	public VectUnitaire getL1() {
		return VectUnitaire.extraireVect(this, 1, true);
	}

	@Override 
	public VectUnitaire getL2() {
		return VectUnitaire.extraireVect(this, 2, true);
	}

	@Override 
	public VectUnitaire getL3() {
		return VectUnitaire.extraireVect(this, 3, true);
	}





	@Override
	public VectUnitaire getC(int j) {
		return VectUnitaire.extraireVect(this, j, false);	
	}



	@Override
	public VectUnitaire getL(int i) {
		return VectUnitaire.extraireVect(this, i, true);
	}




}
