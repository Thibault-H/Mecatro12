package optique.lumiere;

import java.util.ArrayList;
import auxMaths.algLin.VectUnitaire;

/**Une lumiere qui représente la somme de plusieurs autres.
 * 
 * @author Adel
 *
 */
public class AssociationLumieres extends ArrayList<Lumiere> implements Lumiere{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2608521428907705040L;

	public AssociationLumieres() {
		super();
	}
	
	@Override
	public CouleurL mesurerSelon(VectUnitaire ptDeVue) {
		CouleurL result = CouleurL.noir;
		for (Lumiere l : this)
			result= result.plus(l.mesurerSelon(ptDeVue));
		return result;
	}
	
	/**Fonction sans réel sens, pour boucher les trous et faire fonctionner getCouleur() de surface
	 * TODO:changer de conception
	 * @param ptDeVue
	 * @param coefFocus
	 * @return
	 */
	public CouleurL mesurerRefSpec(VectUnitaire ptDeVue, double coefFocus) {
		CouleurL result = CouleurL.noir;
		CouleurL tmp;
		for (Lumiere l : this) {
			tmp=CouleurL.noir;
			tmp = l.mesurerSelon(ptDeVue);
			result= result.plus(tmp);
		}
		return result;
	}

	@Override
	public AssociationLumieres reflexion(VectUnitaire normale) {
		AssociationLumieres result = new AssociationLumieres();
		for (Lumiere l : this)
			result.add(l.reflexion(normale));
		return result;
	}

	@Override
	public CouleurL mesurerDiffus(VectUnitaire normale) {
		CouleurL result = CouleurL.noir;
		for (Lumiere l : this)
			result= result.plus(l.mesurerDiffus(normale));
		return result;
	}
	
	

}
