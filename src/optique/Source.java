package optique;

import java.util.HashMap;
import java.util.Map;

import auxMaths.algLin.Point3;
import ihm.fenetre1.edition.Editable;
import ihm.fenetre1.edition.Entrable;
import objets.CategorieObjet;
import objets.scene.Objet;
import objets.scene.Stageable;
import optique.lumiere.Lumiere;
import optique.sources.Illumination;
import optique.sources.Obstruable;

/**La classe qui représente une source lumineuse.
 * Bien qu'en théorie aboutie et utilisable comme telle, on préferera en pratique utiliser des classes-filles plus spécialisées qui définiront une illumination et une obstruction concrètes.
 * 
 * @author Adel
 *
 */
public class Source extends Objet implements Editable, Eclairage{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1692090022213862365L;
	protected Illumination illum;
	protected Obstruable voil;
	
	/**La liste des attributs de la source. A tout instant en dehors du corps d'une méthode,
	 * ce dictionnaire est la copie conforme des attributs de l'instance en cours.
	 */
	protected Map<String,Entrable> attributs = new HashMap<String,Entrable>();

	
	public Source(String nom,Illumination i, Obstruable v) {
		super(nom);
		illum = i;
		voil=v;
	}
	

	public Illumination getIllumination() {
		return illum;
	}
	
	
	@Override
	public Lumiere getInfluence(Point3 pt, Stageable sc) {
		return voil.voilement(illum.champLumiere(pt), pt,sc);
	}	
	
	
	//=============================================================
	@Override
	public CategorieObjet getTypeObjet() {
		return CategorieObjet.Source;
	}
	
	//============================================================
	//Edition


	@Override
	public Map<String,Entrable> getAttributsEditables() {
		return new HashMap<String,Entrable>(attributs);
	}


	@Override
	public void majListeAttributs() {
	}


	@Override
	public void maj() {
	}



}
