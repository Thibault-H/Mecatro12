package corps.tableauCouleurs.parametres;

import auxMaths.algLin.M3;
import auxMaths.algLin.O3;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import corps.tableauCouleurs.Parametres;

public class ParametresRaytracing extends Parametres{


	/**
	 * 
	 */
	private static final long serialVersionUID = 6310704199096881063L;
	Point3 oeil;
	R3 direction;
	double ecart;


	R3 u,v;   //(u,v,direction) est une base orthonormee. Si on regarde le plan transverse a direction de telle sorte que le vecteur direction soit oriente vers l'avant, u = -ux et v=uy
	O3 base;



	public ParametresRaytracing() {
		//Image
		super();
		//Geometrie
		oeil = Point3.origine.plus(R3.zero);
		setDirection(R3.uy);
		ecart = 1;

	}

	public ParametresRaytracing(Parametres p) {
		//Image
		super(p);
		//Geometrie
		oeil = Point3.origine.plus(R3.zero);
		setDirection(R3.uy);
		ecart = 1;
	}




	/** Renvoie le point dans l'espace qui correspond aux coordonnées entières sur le plan
	 * 
	 * @param l
	 * @param h
	 * @return
	 */
	public Point3 getCentre() {  
		return oeil.plus((base).fois(new R3(0,0, ecart)));
	}
	//===================================================
	//====================================================
	//Géometrie

	/**Renvoie le point de vue sous forme d'un point
	 * 
	 * @return
	 */
	public Point3 getOeil() {
		return oeil;
	}

	/**Modifie le point de vue
	 * 
	 * @param oeil
	 */
	public void setOeil(Point3 oeil) {    this.oeil = oeil;  }


	public double getEcart() {
		return ecart;
	}


	/**Renvoie l'angle de vision sous forme d'un vecteur normé
	 * 
	 * @return
	 */
	public R3 getDirection() {
		return direction;
	}



	/**Modifie l'angle de vision (pas adapté à la rotation de la base)
	 * 
	 * @param direction
	 */
	public void setDirection(R3 direction) {    
		this.direction = direction.normer();  
		u= new R3(-direction.get2(), direction.get1(),0);
		v= direction.vect(u).normer();
		u= u.normer();    
		base = O3.casterOrthogonale((new M3(u,v,direction)));
	}


	/**Modifie l'angle de vision (adapté à la rotation de la base)
	 * 
	 * @param m
	 */
	public void setBase(O3 m) {
		base=m;
		direction=m.getC1();
	}

	/**Renvoie la base adaptée à la caméra sous forme de matrice
	 * 
	 * @return
	 */
	public O3 getBase() {
		return base;
	}

}