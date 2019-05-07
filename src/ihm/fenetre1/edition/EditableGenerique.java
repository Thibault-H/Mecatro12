package ihm.fenetre1.edition;

import java.util.HashMap;
import java.util.Map;

public class EditableGenerique extends HashMap<String,Entrable> implements Editable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7342778863479249030L;

	public EditableGenerique() {
		super();
	}
	
	public EditableGenerique(Entrable... args) {
		this();
		for (int i=0;i<args.length;i++)
			put("Entrable no "+i,args[i]);
	}
	
	@Override
	public Map<String, Entrable> getAttributsEditables() {
		return this;
	}

	@Override
	public void majListeAttributs() {
	}

	@Override
	public void maj() {
	}

}
