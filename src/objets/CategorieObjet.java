package objets;

import java.util.ArrayList;
import java.util.Arrays;

import ihm.fenetre1.ongletsEdition.ongletScene.TypeObjetEntrable;

public enum CategorieObjet {
	Surface,
	Source,
	Horizon,
	LumiereAmbiante;
	
	public TypeObjetEntrable[] getListeObjetsEntrables() {
		ArrayList<TypeObjetEntrable> tmp = new ArrayList<TypeObjetEntrable>( Arrays.asList(TypeObjetEntrable.values()));
		for (TypeObjetEntrable t : tmp)
			if ( !equals(t.getCategorie()) )
				tmp.remove(t);
		TypeObjetEntrable[] result = new TypeObjetEntrable[tmp.size()];
		return tmp.toArray(result);	
	}
	
	
}
