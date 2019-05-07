package auxMaths.algLin;

import java.io.Serializable;

import auxMaths.fonctionsVectorielles.FonctionR3R3;
import corps.tableauCouleurs.Parametres;

/** M3 implémente les matrices 3x3 à coefficients réels
 * 
 * @author Adel
 *
 */
public class M3 implements Serializable, FonctionR3R3{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6790017981440987004L;
	//Attributs (colonnes)
	protected R3 v1;
	protected R3 v2;
	protected R3 v3;
	protected boolean orient;  //true si colonnes, false sinon
	public static final M3 zero = new M3();
	public static final O3 id = new O3();

	
	//========================================================
	//Constructeurs

	/** Construit la matrice nulle
	 * 
	 */
	public M3() {
		v1=v2=v3=new R3();
		orient = true;
	}

	/** Construit une matrice à partir de la donnée de ses colonnes
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public M3(R3 x, R3 y, R3 z) {
		v1=x;
		v2=y;
		v3=z;
		orient = true;
	}

	/** Construit une matrice à partir de la donnée de ses colonnes si b, 
	 * sinon à partir de celle de ses lignes.
	 * @param x
	 * @param y
	 * @param z
	 * @param b
	 */
	public M3(R3 x, R3 y, R3 z, boolean b) {
		v1=x;
		v2=y;
		v3=z;
		orient = b;
	}

	/**Construit une matrice diagonale
	 * 
	 * @param l1
	 * @param l2
	 * @param l3
	 */
	public M3(double l1, double l2, double l3) {
		v1= new R3(l1,0,0);
		v2 =new R3(0,l2,0);
		v3 = new R3(0,0,l3);
		orient = true;
	}


	/** Copie une matrice
	 * 
	 * @param m
	 */
	public M3(M3 m) {
		orient = m.orient;
		v1=m.v1;
		v2=m.v2;
		v3=m.v3;
	}
	
	
	//=====================================================================
	//Getters

	public R3 getC1() {
		if (orient)
			return v1;
		else
			return new R3(v1.get1(),v2.get1(),v3.get1());
	}

	public R3 getC2() {
		if (orient)
			return v2;
		else
			return new R3(v1.get2(),v2.get2(),v3.get2());
	}

	public R3 getC3() {
		if (orient)
			return v3;
		else
			return new R3(v1.get3(),v2.get3(),v3.get3());
	}


	public R3 getL1() {
		if (!orient)
			return v1;
		else
			return new R3(v1.get1(),v2.get1(),v3.get1());
	}

	public R3 getL2() {
		if (!orient)
			return v2;
		else
			return new R3(v1.get2(),v2.get2(),v3.get2());
	}

	public R3 getL3() {
		if (!orient)
			return v3;
		else
			return new R3(v1.get3(),v2.get3(),v3.get3());
	}

	/** Renvoie l'élément à la position (i,j) de la matrice
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public double getVal(int i, int j) {
		if (!orient) {
			if (i==1)
				return v1.getVal(j);
			else if (i==2)
				return v2.getVal(j);
			else if (i==3)
				return v3.getVal(j);
			else throw new IndexOutOfBoundsException("Seulement 3 composantes dans un vecteur!");
		}
		else {
			if (j==1)
				return v1.getVal(i);
			else if (j==2)
				return v2.getVal(i);
			else if (j==3)
				return v3.getVal(i);
			else throw new IndexOutOfBoundsException("Seulement 3 composantes dans un vecteur!");
		}
	}


	/**Renvoie la je colonne de la matrice
	 * 
	 * @param j
	 * @return
	 */
	public static R3 getC(M3 m,int j) {
		if (m.orient) {
			if (j==1) return m.v1;
			else if (j==2) return m.v2;
			else if (j==3) return m.v3;
			else throw new IndexOutOfBoundsException("Seulement 3 colonnes dans la matrice!");
		}
		else
			if (j==1) return new R3(m.v1.get1(),m.v2.get1(),m.v3.get1());
			else if (j==2) return new R3(m.v1.get2(),m.v2.get2(),m.v3.get2());
			else if (j==3) return new R3(m.v1.get3(),m.v2.get3(),m.v3.get3());
			else throw new IndexOutOfBoundsException("Seulement 3 colonnes dans la matrice!");
	}

	public R3 getC(int i) {
		return getC(this,i);
	}

	/**Renvoie la ie ligne de la matrice
	 * 
	 * @param i
	 * @return
	 */
	public static R3 getL(M3 m,int i) {
		if (!m.orient) {
			if (i==1) return m.v1;
			else if (i==2) return m.v2;
			else if (i==3) return m.v3;
			else throw new IndexOutOfBoundsException("Seulement 3 lignes dans la matrice!");
		}
		else
			if (i==1) return new R3(m.v1.get1(),m.v2.get1(),m.v3.get1());
			else if (i==2) return new R3(m.v1.get2(),m.v2.get2(),m.v3.get2());
			else if (i==3) return new R3(m.v1.get3(),m.v2.get3(),m.v3.get3());
			else throw new IndexOutOfBoundsException("Seulement 3 lignes dans la matrice!");
	}

	public R3 getL(int i) {
		return getL(this,i);
	}



	//======================================================
	//Opérations algèbriques

	/**Renvoie la trace
	 * 
	 * @return
	 */
	public double trace() {
		return v1.get1()+v2.get2()+v3.get3();
	}

	

	/** Renvoie la transposee
	 * 
	 * @return
	 */
	public static M3 transposee(M3 m) {
		return new M3(m.v1,m.v2,m.v3,!m.orient);
	}
	
	/** Renvoie la transposee
	 * 
	 * @return
	 */
	public M3 transpose() {
		return transposee(this);
	}


	/** Addition matricielle
	 * 
	 * @param b
	 * @return
	 */
	public M3 plus(M3 b) {
		if (orient==false) 
			return new M3(v1.plus(b.getL1()),v2.plus(b.getL2()),v3.plus(b.getL3()),false);
		else
			return new M3(v1.plus(b.getC1()),v2.plus(b.getC2()),v3.plus(b.getC3()));
	}

	/** Renvoie l'oppose de la matrice
	 * 
	 * @return
	 */
	public static M3 opp(M3 m) {
		return new M3(m.v1.opp(),m.v2.opp(),m.v3.opp(),m.orient);
	}
	
	/** Renvoie l'oppose de la matrice
	 * 
	 * @return
	 */
	public M3 opp() {
		return opp(this);
	}

	/** Soustraction matricielle
	 * 
	 * @param b
	 * @return
	 */
	public M3 moins(M3 b) {
		if (orient==false) 
			return new M3(v1.moins(b.getL1()),v2.moins(b.getL2()),v3.moins(b.getL3()),false);
		else
			return new M3(v1.moins(b.getC1()),v2.moins(b.getC2()),v3.moins(b.getC3()));
	}



	/**Produit par un vecteur
	 * 
	 * @param x
	 * @return
	 */
	public R3 fois(R3 x) {
		if (orient)
			return R3.sum( R3.prod(x.get1(), v1) , R3.prod(x.get2(), v2) , R3.prod(x.get3(), v3));
		else
			return new R3( R3.scal(v1, x) , R3.scal(v2, x) , R3.scal(v3, x));
	}

	/**Produit matriciel
	 * 
	 * @param b
	 * @return
	 */
	public M3 fois(M3 b) {
		return new M3( fois(b.getC1()) , fois(b.getC2()) , fois(b.getC3()));
	}

	/**Renvoie tXQY
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public double gram(R3 x,R3 y) {
		return x.scal(fois(y));
	}


	//===============================================================
	//Testeurs de propri�t�s




	public O3 transformation(int type, double incrrot, M3 base, Point3 oeil) {// M3=(uy,-ux,uz)
		return O3.casterOrthogonale(new M3(getC1().transformation(type, incrrot, base, oeil), getC2().transformation(type, incrrot, base, oeil), getC3().transformation(type, incrrot, base, oeil)));

	}

	/** Renvoie la norme 2 matricielle Tr(tAA)
	 * 
	 * @return
	 */
	public double norme2() {
		return fois(transpose()).trace();
	}


	/** Renvoie true ssi la matrice est orthogonale
	 * 
	 * @return
	 */
	public boolean estOrthogonale() {
		return fois(transpose()).equals(id);
	}

	public double det() {
		return v3.scal(v1.vect(v2));
	}

	public boolean estInversible() {
		double d = det();
		return d>=Parametres.h || d<=-Parametres.h;
	}

	public GL3 casterInversible() {
		return GL3.casterInversible(this);
	}

	public boolean estDirecte() {
		return det()>=Parametres.h;
	}

	
	//=================================================
	//Autres
	
	@Override
	public R3 fonction(R3 r) {
		return fois(r);
	}


	@Override
	public String toString() {
		String result ="";
		for (int i=1;i<4;i++) {
			result+= "| ";
			for(int j=1;j<4;j++) 
				result += getVal(i,j) + " ";
			result+= String.format("|%n");
		}
		return result;
	}


	/**Egalité au sens de la norme 2
	 * 
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof M3)
			return moins((M3)o).norme2() <= Parametres.h;
		else return false;
	}


	public static void main(String[] args) {
		R3 x=new R3(1,0,0);
		R3 y = new R3(0,1,0);
		R3 z = new R3(0,0,1);
		System.out.print(O3.rotation(R3.uy, 2).estOrthogonale());
	}





}
