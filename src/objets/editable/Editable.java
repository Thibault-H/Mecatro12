package objets.editable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//Une classe qui implemente Editable doit implementer Editable<[nom de la classe fille]>
public interface Editable {
	
	public default Map<String,TypeEntrable> getTypeAttributsEditables(){
		Map<String,TypeEntrable> result = new HashMap<String, TypeEntrable>();
		Map<String,Entrable> tab = getAttributsEditables();
		Iterator<String> itr = getAttributsEditables().keySet().iterator();
		
		String tmp;
		while (itr.hasNext())
			result.put( tmp = itr.next(),tab.get(tmp).getTypeEntrable());
		return result;
	}
	
	public Map<String,Entrable> getAttributsEditables();
}

