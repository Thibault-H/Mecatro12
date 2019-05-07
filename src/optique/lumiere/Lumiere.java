package optique.lumiere;

import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;

/**Cette interface représente la lumière dans sa dimension potentiellement directive (Poynting)
 * 
 * @author Adel
 *
 */
public interface Lumiere {
	final static LumiereDiffuse noir = new LumiereDiffuse(CouleurL.noir);
	
	//===================================================
	//Fonction principale
	/**Renvoie l'intensité lumineuse perçue dans une direction donnée : idée pondération en cos(theta)**dureté
	 * 
	 * @param ptDeVue
	 * @return
	 */
	public CouleurL mesurerSelon(VectUnitaire ptDeVue);
	public default CouleurL mesurerSelon(R3 ptDeVue) {
		return mesurerSelon(new VectUnitaire(ptDeVue));
	}
	
	/**Renvoie l'intensite lumineuse diffusee dans toutes les directions par un diffuseur localement plan de normale donnee.

	 * 
	 * @return
	 */
	public CouleurL mesurerDiffus(VectUnitaire normale);
	//======================================================
	public Lumiere reflexion(VectUnitaire normale);
	public default Lumiere reflexion(R3 normale) {
		return reflexion(new VectUnitaire(normale));
	}
	
	
}
