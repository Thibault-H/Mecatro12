package objets.scene;

import java.util.ArrayList;
import java.util.List;

import algLin.Point3;
import objets.ObjetRaytracing;
import objets.ihmEditionObjet.IHMListe;
import objets.objetPhong.Surface;
import optique.Ambiant;
import optique.CouleurL;
import optique.Photon;
import optique.Source;

/**La scène classique pour le raytracing : une scène de base avec des sources a priori multiples.
 * 
 * @author Adel
 *
 */
public class SceneRaytracing extends SceneSansSources {

	public List<Source> listeSources;
	public IHMListe<Source> ihmSources;
	
	// Constructeurs
	public SceneRaytracing() {
		super();
		listeSources = new ArrayList<Source>();
		ihmSources = new IHMListe(listeSources);
	}

	public SceneRaytracing(double i) {
		this();
		amb = new Ambiant(new CouleurL(1, 1, 1, i));
		intensiteBlanc = i;
	}

	public SceneRaytracing(CouleurL c) {
		this();
		amb = new Ambiant(c);
		intensiteBlanc = c.getIntensite();
	}


	//============================================
	// Getters
	
	@Override
	public Source[] getSources() {
		Source[] result = new Source[0];
		return listeSources.toArray(result);
	}

	//===========================================
	// Modification de la scene
	
	@Override
	public void ajouter(Objet o) {
		try {
			super.ajouter(o);
		}
		catch(TypeObjetPasTraiteException e) {
			switch(e.objetDeLerreur.getTypeObjet()) {
			case Source:
				listeSources.add((Source)o);
				//o.setScene(this);
				break;
			default :
				throw new IllegalArgumentException("Erreur dans l'ajout de l'objet : type non reconnu.");
			}
			ihmSources.conformerAuContenu();
		}
	}
		
	@Override
	public void supprimer(Objet o) {
		try {
			super.supprimer(o);
		}
		catch(TypeObjetPasTraiteException e) {
			switch(e.objetDeLerreur.getTypeObjet()) {
			case Source:
				listeSources.remove((Source)o);
				//o.setScene(this);
				break;
			default :
				throw new IllegalArgumentException("Erreur dans l'ajout de l'objet : type non reconnu.");
			}
			ihmSources.conformerAuContenu();
		}
	}

	@Override
	public IHMListe<Source> getIHMSources() {
		return ihmSources;
	}

	//=============================================
	//Algorithmes


	@Override
	public List<Photon> getLumieresEn(Point3 p) {
		List<Photon> result = new ArrayList<Photon>();
		for (Source src : getSources())
			result.add(src.getInfluence(p,this));
		return result;
	}




}
