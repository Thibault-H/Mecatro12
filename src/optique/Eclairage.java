package optique;

import auxMaths.algLin.Point3;
import auxMaths.transformations.TransformationEspace;
import objets.scene.Stageable;
import optique.lumiere.Lumiere;

@FunctionalInterface
public interface Eclairage {

	/**Renvoie l'influence de la source sur un point donné dans une scène donnée.
	 * Prend en compte le type de la source (illumination) et les obstacles présents dans la scène (voilement)
	 * @param pt
	 * @param sc
	 * @return
	 */
	Lumiere getInfluence(Point3 pt, Stageable sc);

	

}