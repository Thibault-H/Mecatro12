package corps.tableauCouleurs.parametres;

import auxMaths.algLin.M3;
import auxMaths.algLin.O3;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import corps.tableauCouleurs.Parametres;

public class ParametresMecatro extends Parametres {
	
	Point3 centre;
	O3 base;
	
	
	public ParametresMecatro(Point3 centreCadre, O3 baseRef) {
		//Image
		super();
		centre = centreCadre;
		base = baseRef;
	}
	
	public ParametresMecatro() {
		//Image
		this(Point3.origine.plus(R3.uy) , O3.base( R3.uy ) );
	}


	//======================================================
	//Getters & Setters
	/**Renvoie la base adaptée à la caméra sous forme de matrice
	 * 
	 * @return
	 */
	public O3 getBase() {
		return base;
	}

	public Point3 getCentre() {
		return centre;
	}


	public void setCentre(Point3 centre) {
		this.centre = centre;
	}


	public VectUnitaire getNormale() {
		return new VectUnitaire(base.getC1());
	}


	public void setNormale(VectUnitaire normale) {
		base = O3.base(normale);
	}
	
	//==============================================
	public ParametresRaytracing toRay() {
		return new ParametresRaytracing(this);
	}
	
	
	
	
	
	

}
