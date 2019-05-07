package objets.scene;

import auxMaths.PointMobile;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import ihm.fenetre1.ongletsEdition.ongletScene.FenetreAjoutObjet;
import ihm.fenetre1.ongletsEdition.ongletScene.IHMListe;
import ihm.fenetre1.ongletsEdition.ongletScene.TypeObjetEntrable;
import objets.ObjetRaytracing;
import objets.objetPhong.Horizon;
import objets.objetPhong.Surface;
import optique.Ambiant;
import optique.Source;
import optique.lumiere.AssociationLumieres;

public interface Stageable {

	//=====================================
	//Getters : édition des objets
	
	/** Renvoie la liste des sources. A utiliser pour l'édition.
	 * TODO: enlever l'appel à cette fonction dans MiroirPhong
	 * @return
	 */
	Source[] getSources();
	
	
	
	
	
	
	/** Renvoie l'objet horizon de la scène. A utiliser pour l'édition.
	 * 
	 * @return
	 */
	Horizon getFond();
	
	/** Renvoie la lumière ambiante. A utiliser pour l'édition.
	 * TODO: enlever l'appel à cette fonction dans getColor des classes Surfaces et autres
	 * @return
	 */
	Ambiant getAmbiant();

	/** Renvoie la liste de tous les objets de la scène qui ne sont pas des sources.
	 * 
	 * @return
	 */
	ObjetRaytracing[] getListeObjets();

	/** Renvoie la liste des objets éditables qui ne sont pas des sources, ni l'horizon. A utiliser pour l'édition.
	 * 
	 * @return
	 */
	Surface[] getListeObjetsEditables();

	
	//=============================================
	//Edition de la scène

	/** Ajoute o dans la scène, en tant qu'instance de sa classe
	 * 
	 * @param o
	 */
	void ajouter(Objet o) throws TypeObjetPasTraiteException;

	
	/** Supprime o de la scène. Pas d'erreur renvoyée si on ne le trouve pas.
	 * 
	 * @param o
	 */
	void supprimer(Objet o) throws TypeObjetPasTraiteException;

	
	/**Renvoie la JList correspondant à la liste des surfaces.
	 * 
	 * @return
	 */
	IHMListe<Surface> getIHMSurfaces();
	
	/**Renvoie la JList correspondant à la liste des sources.
	 * 	
	 * @return
	 */
	IHMListe<Source> getIHMSources();
	
	/**Renvoie la liste du type des objets que l'on peut ajouter dans la scène
	 * 
	 * @return
	 */
	TypeObjetEntrable[] getObjetsAjoutables();
	
	/**Renvoie une JFrame d'édition de la scene.
	 * 
	 * @return
	 */
	public default FenetreAjoutObjet getIHMListeAjout() {
		return new FenetreAjoutObjet(this);
	}
	
	//===============================================
	// Rendu et algos
	
	public double getBlanc();
	
	/**Renvoie l'ensemble des lumières incidentes en un point donné : intensité, couleur, direction, sens
	 * TODO : remplacer AssociationLumieres par Lumiere (cf fonction mesurerSpec de LumiereDirective.
	 * @param p
	 * @return
	 */
	 AssociationLumieres getLumieresEn(Point3 p);

	
	/**Avance m en suivant dir jusqu'à tomber sur un objet. Renvoie ce dernier.
	 * 
	 * @param m
	 * @param dir
	 * @return
	 */
	ObjetRaytracing avancerJusquauChoc(PointMobile m, VectUnitaire dir, ObjetRaytracing... aIgnorer);

	/**Avance m en suivant dir jusqu'à tomber sur un objet. Renvoie ce dernier.
	 * 
	 * @param m
	 * @param dir
	 * @return
	 */
	default ObjetRaytracing avancerJusquauChoc(PointMobile m, R3 dir, ObjetRaytracing... aIgnorer) {
		if (dir.estNul())
			return null;		//TODO
		return avancerJusquauChoc(m,dir.normer(),aIgnorer);
	}

}


class TypeObjetPasTraiteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3375280622677558016L;
	
	Objet objetDeLerreur;
	
	public TypeObjetPasTraiteException(Objet t) {
		super(t.getTypeObjet() + " pas traité");
		objetDeLerreur=t;
	}
}