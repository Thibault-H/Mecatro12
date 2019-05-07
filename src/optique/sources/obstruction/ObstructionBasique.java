package optique.sources.obstruction;

import java.awt.Color;

import auxMaths.PointMobile;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import objets.ObjetRaytracing;
import objets.objetPhong.Plan;
import objets.scene.SceneRaytracing;
import objets.scene.Stageable;
import optique.lumiere.Lumiere;
import optique.sources.Obstruable;

/**Obstruction de base : source ponctuelle dans un environnement transparent et à indice uniforme.
 * 
 * @author Adel
 *
 */
public class ObstructionBasique implements Obstruable{
	Point3 reference;
	
	ObjetRaytracing[] aIgnorer;
	
	/**Le constructeur d'une obstruction associée à une source ponctuelle
	 * aIgnorer regroupe les objets de la scène dont on ignorera l'obstruction (utile pour miroir plan)
	 * @param position
	 * @param aIgnorer
	 */
	public ObstructionBasique(Point3 position, ObjetRaytracing... aIgnorer) {
		reference = position;
		this.aIgnorer=aIgnorer;
	}
	
	/**Renvoie si oui ou non il y a un objet opaque entre pt et reference
	 * 
	 * @param pt
	 * @param sc
	 * @return
	 */
	public boolean estCache(Point3 pt, Stageable sc) {
		PointMobile photon = new PointMobile(reference);	//On lance un photon...
		sc.avancerJusquauChoc(photon, reference.Vecteur(pt), aIgnorer);	//vers le pt...
		if (reference.estPlusProcheDeQue(pt, photon))		//On regarde s'il l'atteint...
			return false;		//Si oui alors le pt n'est pas voilé...
		else return true;	//Sinon alors il est caché et on voit rien.	
	}
	
	@Override
	public Lumiere voilement(Lumiere lum, Point3 pt, Stageable sc) {
		return estCache(pt,sc)? Lumiere.noir : lum;
		
		
	}
	
	public static void main(String[] args) {
		Point3 source = Point3.origine;
		Point3 pt = Point3.origine.plus(new R3(5,0,0));
		ObstructionBasique obs=new ObstructionBasique(source);
		
		SceneRaytracing sc = new SceneRaytracing();
		
		VectUnitaire norm = R3.ux;
		Point3 ptPart = Point3.origine.plus(new R3(6,0,0));
		Plan p = new Plan("", norm, ptPart, Color.BLACK);
		sc.ajouter(p);
		
		
		System.out.println(obs.estCache(pt, sc));
		
		
		
	}

}
