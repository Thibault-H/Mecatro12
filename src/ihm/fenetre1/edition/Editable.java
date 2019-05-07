package ihm.fenetre1.edition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ihm.fenetre1.edition.entrees.TypeEntrable;

/**L'interface qu'implémente toutes les classes dont les instances ont une chance d'être éditable par l'ihm. (FenetreEntree)
 * Les classes concernées sont en principe celles qui implémentent des objets de la scène.
 * Une instance qui n'a finalement pas vocation à être éditée n'aura qu'à renvoyer un dictionnaire vide à l'appel de "getAttributsEditables()".
 * 
 * @author Adel
 *
 */
public interface Editable {
	
	
	//=============================================================
	//Obtention de la liste des attributs
	
	//REMARQUE : la liste des attributs éditables est censée être codée sous la forme d'un attributs, rpzé ici avec un getter. 
	
	/**
	 * Renvoie la liste des attributs caractéristiques de l'objet éditable sous
	 * la forme d'un dictionnaire qui associe son nom à un attribut.
	 * @return
	 */
	public Map<String,Entrable> getAttributsEditables();
	
	
	/**
	 * Renvoie la liste du type des attributs caractéristiques de l'objet éditable sous
	 * la forme d'un dictionnaire qui à un type d'entrées associe la liste des attributs de ce type.
	 * Cette dernière est générée à partir du dictionnaire "classique" d'attributs obtenu par getAttributsEditables()
	 * @return
	 */
	public default Map<TypeEntrable, List<String>> getTypeAttributsEditables(){
		Map<TypeEntrable, List<String>> result = new HashMap<TypeEntrable, List<String>>();
		Map<String,Entrable> tab = getAttributsEditables();
		Iterator<String> itr = getAttributsEditables().keySet().iterator();
		
		String nomAttributEnCours;
		TypeEntrable typeAttributEnCours;		
		while (itr.hasNext()) {
			nomAttributEnCours = itr.next();
			typeAttributEnCours = tab.get(nomAttributEnCours).getTypeEntrable();
			result.putIfAbsent( typeAttributEnCours , new ArrayList<String>());
			result.get(typeAttributEnCours).add(nomAttributEnCours);
		}
		return result;
	}
	
	

	
	
	//===============================================================
	//Reconstruction de l'instance à partir de la liste de ses attributs
	
	/**
	 * Modifie l'instance en cours d edition conformement a la liste d attributs
	 * en entree.
	 * Le booleen renvoyé indique la réussite de l'opération.
	 * En cas d'échec, l'instance n'est pas modifiée. 
	 * @param attributs
	 * @return
	 */
	public default boolean reconstruireAvecAttributs(Map<String, Entrable> inputAttributs) {
		if ( getAttributsEditables().keySet().equals(inputAttributs.keySet()) ){	// 1ere vérification de compatibilité des attributs
			boolean isConformable= true;	// Vérifions que les entrées sont de types compatibles
			Iterator<String> itr = getAttributsEditables().keySet().iterator();
			String tmp;
			while (isConformable && itr.hasNext()) {
				tmp=itr.next();
				isConformable = getAttributsEditables().get(tmp).conformerA(inputAttributs.get(tmp)); 
			}
			if (isConformable) {	//vrai ssi l'opération s'est faite sans heurt, i.e si toutes les entrees sont reconnues et de type compatible. Dans ce cas, attributs a été actualisé conformément à inputAttributs
				maj();	//on actualise les attributs de l'instance
				return true;
			}
			else {
				majListeAttributs(); //on annule les éventuels changements qui sont arrivés à attributs pendant le test précédent
				return false;
			} 
		}
		else return false;
	}

	/**
	 * Met à jour le dictionnaire des attributs au vu des attributs actuels.
	 * (à appeler si les attributs de l'instance sont modifiés)
	 */
	public abstract void majListeAttributs();

	/**
	 * Met à jour les attributs de l'instance au vu du dictionnaire d'attributs. 
	 * Son comportement géométrique et optique est lui aussi actualisé.
	 * (à appeler dans reconstruireAvecAttributs, par exemple)
	 */
	public abstract void maj();
}

