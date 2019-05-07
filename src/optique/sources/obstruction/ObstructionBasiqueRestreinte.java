package optique.sources.obstruction;

import auxMaths.algLin.Point3;
import objetmaths.volumemaths.VolumeMath;
import objets.ObjetRaytracing;
import objets.scene.Stageable;
import optique.lumiere.Lumiere;
import optique.sources.Obstruable;

/**L'obstruction associé à la source virtuelle engendrée par la réflexion 
 * d'une source ponctuelle sur un miroir (ça marche si c'est plan)
 * @author Adel
 *
 */
public class ObstructionBasiqueRestreinte extends ObstructionBasique {

	Obstruable aux;
	
	/**Source ponctuelle en position et restriction à v.
	 * 
	 * @param position
	 * @param v
	 */
	public ObstructionBasiqueRestreinte(Point3 position, VolumeMath v, ObjetRaytracing... aIgnorer) {
		super(position,aIgnorer);
		aux = Obstruable.indicatrice(v);
	}

	@Override
	public Lumiere voilement(Lumiere lum, Point3 pt, Stageable sc) {
		return super.voilement(aux.voilement(lum, pt, sc), pt, sc);
	}

	
	
}
