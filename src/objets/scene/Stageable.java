package objets.scene;

import java.util.List;

import algLin.Point3;
import algLin.PointMobile;
import algLin.R3;
import objets.ObjetRaytracing;
import objets.ihmEditionObjet.IHMListe;
import objets.objetPhong.Horizon;
import objets.objetPhong.Surface;
import optique.Ambiant;
import optique.Photon;
import optique.Source;

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

	
	IHMListe<Surface> getIHMSurfaces();
	IHMListe<Source> getIHMSources();
	
	//===============================================
	// Rendu et algos
	
	public double getBlanc();
	
	/**Renvoie l'ensemble des lumières incidentes en un point donné : intensité, couleur, direction, sens
	 * TODO : prise en compte des obstacles qui cachent la lumière
	 * @param p
	 * @return
	 */
	List<Photon> getLumieresEn(Point3 p);

	
	/**Avance m en suivant dir jusqu'à tomber sur un objet. Renvoie ce dernier.
	 * 
	 * @param m
	 * @param dir
	 * @return
	 */
	ObjetRaytracing avancerJusquauChoc(PointMobile m, R3 dir);

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