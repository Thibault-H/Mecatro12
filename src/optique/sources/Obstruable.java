package optique.sources;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import objetmaths.volumemaths.DemiEspace;
import objetmaths.volumemaths.VolumeMath;
import objets.scene.Stageable;
import optique.lumiere.Lumiere;
import optique.lumiere.LumiereDiffuse;

/**L'interface qui modélise l'ensemble des phénomènes qui obstruent la lumière sous la forme d'une fonction qui à un point associe une obstruction.
 * 
 * @author Adel
 *
 */
@FunctionalInterface
public interface Obstruable {
	
	public Lumiere voilement(Lumiere lum, Point3 pt, Stageable sc);
	
	
/*	public default Lumiere voilement(Lumiere lum, Point3 pt, Stageable sc) {
		return voilement(lum,pt,sc, new ObjetRaytracing[0]);
	}
	*/
	
	
	
	
	
	public static final Obstruable Nulle = (lum,pt,sc) ->lum;
	
	//==============================================================
	//Instances particulières
	
	//Indicatrice
	
	/** Renvoie une instance d'Obstruable qui correspond à l'indicatrice de v (noir ssi on n'est pas dans v)
	 * ou de celle de son complémentaire si "complementaire" est vrai
	 * 
	 * @param v
	 * @param complementaire
	 * @return
	 */
	public static Obstruable indicatrice(VolumeMath v, boolean complementaire) {
		Obstruable result = (lum,pt,sc) ->  (complementaire ^ v.estDedans(pt))? lum : Lumiere.noir;
		return result;
	}
	
	/** Renvoie une instance d'Obstruable qui correspond à l'indicatrice de v (noir ssi on n'est pas dans v).
	 * 
	 * @param v
	 * @return
	 */
	public static Obstruable indicatrice(VolumeMath v) {
		return indicatrice(v,false);
	}


	//Construction
	
	/**Renvoie la composée o1 rond o2. 
	 * Concrètement, renvoie l'obstruable qui applique la méthode "voilement" de o1 à la sortie de la méthode homonyme de o2 
	 * (le point et la scène sont identiques dans les deux cas).  
	 * @param o2
	 * @return
	 */
	public default Obstruable rond(Obstruable o2) {
		Obstruable result = (lum,pt,sc) -> voilement(o2.voilement(lum, pt, sc), pt, sc);
		return result;
	}
	
	
	public static void main(String[] args) {
		VolumeMath v1 = new DemiEspace(Point3.origine, R3.ux);
		Point3 p = Point3.origine.plus(R3.ux.opp());
		Obstruable o1 = Obstruable.indicatrice(v1, false);
		System.out.println( o1.voilement(new LumiereDiffuse(2), p, null));
	}

}
