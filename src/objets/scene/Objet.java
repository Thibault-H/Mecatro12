package objets.scene;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import objets.TypeObjet;
import objets.editable.Entrable;

public abstract class Objet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8094289181451338202L;
	private String name;

	protected Objet(String nom) {
		setNom(nom);
	}

	public void setNom(String s) {
		name=s;
	}

	public String getNom() {
		if (name==null)
			return toString();
		else return name;
	}

	public abstract TypeObjet getTypeObjet();


	






}
