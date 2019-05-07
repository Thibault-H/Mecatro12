package optique;

import java.util.HashMap;
import java.util.Map;

import ihm.fenetre1.edition.Editable;
import ihm.fenetre1.edition.Entrable;
import ihm.fenetre1.edition.entrees.Couleur;
import objets.CategorieObjet;
import optique.lumiere.CouleurL;
import optique.sources.Obstruable;
import optique.sources.illumination.IlluminationAmbiante;

public class Ambiant extends Source implements Editable{

	//CouleurL lum;
	
	/**La liste des attributs de la surface. A tout instant en dehors du corps d'une méthode,
	 * ce dictionnaire est la copie conforme des attributs de l'instance en cours.
	 */
	protected Map<String,Entrable> attributs = new HashMap<String,Entrable>();


	public Ambiant(CouleurL l) {
		super("Lumière Ambiante",new IlluminationAmbiante(l), Obstruable.Nulle );
		//lum=l;
	}

	@Override
	public CategorieObjet getTypeObjet() {
		return CategorieObjet.LumiereAmbiante;
	}
	

	
	//================================================
	//Getters
	
	
	public CouleurL getCouleurL() {
		return ((IlluminationAmbiante)illum).getCouleur();
	}


/*	@Override
	public Photon getInfluence(Point3 p,Stageable s) {
		return new Photon(p,lum,s);  //null car la lumiere arrive partout dans la scene
	}

*/


	//============================================
	//Edition
	
	@Override
	public Map<String, Entrable> getAttributsEditables() {
		return attributs;
	}

	@Override
	public void majListeAttributs() {
		attributs.put("Lumiere", new Couleur(getCouleurL()) );
		
	}

	@Override
	public void maj() {
		illum = new IlluminationAmbiante( ((Couleur) attributs.get("Lumiere")).getValueL() );
		
	}



}
