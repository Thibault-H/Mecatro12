package optique.sources;

import auxMaths.algLin.Point3;
import optique.lumiere.Lumiere;

/**L'interface qui modélise le comportement d'une source dans le vide complet : une fonction de l'espace qui à un point associe de la lumière.
 * 
 * @author Adel
 *
 */
@FunctionalInterface
public interface Illumination {
	public Lumiere champLumiere(Point3 pt);
	
	
}
