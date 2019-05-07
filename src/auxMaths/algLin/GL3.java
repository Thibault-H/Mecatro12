package auxMaths.algLin;

import auxMaths.TesteurNullite;
import auxMaths.fonctionsVectorielles.BijectionR3R3;

public class GL3 extends M3 implements TesteurNullite, BijectionR3R3{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4436635401210103044L;

	
	public GL3() {
		super(R3.ux,R3.uy,R3.uz);
	}
	
	/**Construit une matrice diagonale
	 * 
	 * @param l1
	 * @param l2
	 * @param l3
	 */
	public GL3(double l1, double l2, double l3) {
		this();
		if (l1*l2*l3==0)
			throw new IllegalArgumentException("Les coeff diagonaux doivent être tous non nuls pour que la matrice soit inversible!");
		else {
			v1=v1.prod(l1);
			v2=v2.prod(l2);
			v3=v3.prod(l3);
		}
			
	}

	
	
	//Constructeurs qu'on appelle lorsqu'on est sûr de l'inversibilité
	
	/** Construit une matrice Ã  partir de la donnÃ©e de ses colonnes
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	protected GL3(R3 x, R3 y, R3 z) {
		super(x,y,z);
	}

	/** Construit une matrice Ã  partir de la donnÃ©e de ses colonnes si b, 
	 * sinon Ã  partir de celle de ses lignes.
	 * @param x
	 * @param y
	 * @param z
	 * @param b
	 */
	protected GL3(R3 x, R3 y, R3 z, boolean b) {
		super(x,y,z,b);
	}
	



	protected GL3(M3 m) {
		super(m);
	}



	//======================================================
	//Construction
	

	public static GL3 casterInversible(M3 m) {
		if (m.estInversible())
			return new GL3(m);
		else throw new IllegalArgumentException("Matrice non inversible!");
	}
	
	
	public GL3 inverse() {
		//TODO
		throw new RuntimeException("Pas implémenté l'inverse dans le cas général");
	}
	
	

	//==============================================
	//Opérations qui conservent l'inversibilité
	
	

	@Override
	public GL3 transpose() {
		return new GL3(v1,v2,v3,!orient);
	}
	

	@Override
	public GL3 opp() {
		return new GL3(v1.opp(),v2.opp(),v3.opp(),orient);
	}
	
	/**Produit matriciel
	 * 
	 * @param b
	 * @return
	 */
	public GL3 fois(GL3 b) {
		return new GL3( fois(b.getC1()) , fois(b.getC2()) , fois(b.getC3()));
	}
	
	@Override
	public boolean estInversible() {
		return true;
	}
}
