package objetmaths.surfacemaths;

import auxMaths.algLin.M3;
import auxMaths.algLin.O3;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import objets.AFaireTourner;

public class RectangleMath extends PolygoneConvexe implements AFaireTourner{

	
	M3 baseDeRef;
	double l1;
	double l2;
	
	/*
	 * La liste de points est orientée dans le sens indirect
	 */
	public RectangleMath(R3 n, Point3 centre, double longueur, double largeur) {
		planBase = new Degre1(n, centre);
		baseDeRef = O3.base(n);
		this.centre=centre;
		l1=longueur;
		l2=largeur;
		R3 u = R3.prod(longueur/2,baseDeRef.getC2());
		R3 v = R3.prod(largeur/2,baseDeRef.getC3());
		setListePoints(new Point3[4]);
		getListePoints()[0]= centre.plus(u).plus(v);
		getListePoints()[1]= centre.plus(u).moins(v);
		getListePoints()[2]= centre.moins(u).moins(v);
		getListePoints()[3]= centre.moins(u).plus(v);
	}
	

	

	public Point3 getPoint() {
		// TODO Auto-generated method stub
		return centre;
	}


	public double getLongueur1() {
		// TODO Auto-generated method stub
		return l1;
	}

	public double getLongueur2() {
		// TODO Auto-generated method stub
		return l2;
	}

	@Override
	public M3 getBaseRef() {
		// TODO Auto-generated method stub
		return baseDeRef;
	}
	
	
	@Override
	public void setBaseRef(M3 ref) {
		// TODO Auto-generated method stub
		baseDeRef=ref;
	}
	

	public static void main(String[] args) {
		RectangleMath r = new RectangleMath(R3.uz, Point3.origine, 2,2);
		Point3 m = Point3.origine.plus(R3.uz);
		System.out.println(r.estDedans(Point3.origine.plus(R3.ux)));
	}















	
}
