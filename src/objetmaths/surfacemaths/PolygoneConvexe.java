package objetmaths.surfacemaths;

import algLin.M3;
import algLin.Point3;
import algLin.R3;

public abstract class PolygoneConvexe extends SurfacePlaneConvexe {
	
	
	private Point3[] listePoints;
	Point3 centre;
	
	/**Renvoie la liste des sommets du polygone, tels qu'on peut la construire en tournant autour de la figure dans un même sens
	 * 
	 * @return
	 */
	public Point3[] getListePoints() {
		return listePoints;
	}

	protected void setListePoints(Point3[] listePoints) {
		this.listePoints = listePoints;
	}
	
	protected abstract void setBaseRef(M3 ref) ;

	protected boolean estDedans(Point3 m) {
		return testDeterminant(m, getListePoints(), getNorm(null));
	}
	
	protected Point3 defCentre() {
		R3 result=R3.zero;
		for (int i=0; i<getListePoints().length; i++) 
			result = result.plus(Point3.origine.Vecteur(getListePoints()[i]));
		return Point3.origine.plus(result.prod(1.0/getListePoints().length));
	}
	
	
	
	/*Procède au test de déterminant classique des polygones convexes, mais le généralise à une liste quelconque (intérêt?) 
	 * 
	 */
	public static boolean testDeterminant(Point3 m, Point3[] liste, R3 normale) {
		boolean sens = Point3.estOrientationPositive(normale, m, liste[0], liste[1]);
		boolean result = true;
		if (sens)
			for (int i=1; i<liste.length-1 && result; i++) {
				result= Point3.estOrientationPositive(normale, m, liste[i], liste[i+1]);
			}
		else
			for (int i=1; i<liste.length-1 && result; i++) {
				result= !Point3.estOrientationPositive(normale, m, liste[i], liste[i+1]);
			}
		return result;
	}
	
	
	
	//Méthode de similitude
	
	public void faireTourner(double val, R3 axe) {
		M3 rot = M3.rotation(axe, val);
		setBaseRef(getBaseRef().fois(rot));
		for (int i=0; i<getListePoints().length; i++)
			getListePoints()[i] = getListePoints()[i].appliquerRot(centre, axe, val);
			
	}


	public void faireTourner(R3 val, M3 base) {
		faireTourner(val.get1(),base.getC1());
		faireTourner(val.get2(),base.getC2());
		faireTourner(val.get3(),base.getC3());
		
	}




	@Override
	public void faireTourner(R3 val) {
		faireTourner(val,getBaseRef());
	}




	@Override
	public void faireTournerAvecJoystick(int type, double incrrot, M3 base, Point3 oeil) {
		// TODO Auto-generated method stub
		
	}



	  
	public static void main(String[] args) {
	}

}
