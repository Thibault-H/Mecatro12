package objets.editable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//Une classe qui implemente Editable doit implementer Editable<[nom de la classe fille]>
public interface Editable {
	
	/**
	 * Renvoie la liste du type des attributs caractéristiques de l'objet éditable sous
	 * la forme d'un dictionnaire.
	 * @return
	 */
	public default Map<String,TypeEntrable> getTypeAttributsEditables(){
		Map<String,TypeEntrable> result = new HashMap<String, TypeEntrable>();
		Map<String,Entrable> tab = getAttributsEditables();
		Iterator<String> itr = getAttributsEditables().keySet().iterator();
		
		String tmp;
		while (itr.hasNext())
			result.put( tmp = itr.next(),tab.get(tmp).getTypeEntrable());
		return result;
	}
	
	public String getNom();
	
	/**
	 * Renvoie la liste des attributs caractéristiques de l'objet éditable sous
	 * la forme d'un dictionnaire.
	 * @return
	 */
	public Map<String,Entrable> getAttributsEditables();
	
	
	/**
	 * Modifie l'instance en cours d edition conformement a la liste d attributs
	 * en entree.
	 * Le booleen renvoyé indique la réussite de l'opération.
	 * En cas d'échec, l'instance n'est pas modifiée. 
	 * @param attributs
	 * @return
	 */
	public boolean reconstruireAvecAttributs(Map<String,Entrable> inputAttributs);
}

