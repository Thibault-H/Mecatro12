package objets.objetMecatro;

import algLin.Point3;
import objetmaths.volumemaths.VolumeMath;
import optique.SourcePonctuelleIsotrope;

public abstract class VolumeIllumine {
	protected VolumeMath v;
	protected SourcePonctuelleIsotrope[] listeSource;
	
	public void setSources(SourcePonctuelleIsotrope... args) {
		listeSource = args.clone();
	}
	
	protected abstract double fonctionIllumination(Point3 p);
	
	/*Renvoie la fraction de l'intensite initiale qui atteint le point p 
	 * grâce à l'action du miroir; le double est un réel positif
	 */
	public double getIntensiteRecue(Point3 p) {
		if (v.estDedans(p))
			return fonctionIllumination(p);
		else return 0; 
	}
}
