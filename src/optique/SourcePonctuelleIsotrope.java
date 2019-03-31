package optique;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import algLin.Point3;
import algLin.R3;
import objetmaths.surfacemaths.Degre1;
import objets.TourneAutour;
import objets.TypeObjet;
import objets.editable.Couleur;
import objets.editable.Editable;
import objets.editable.Entrable;
import objets.editable.Point;
import objets.editable.Scalaire;
import objets.editable.Vecteur;
import objets.scene.Stageable;


// TODO : isEditable

public class SourcePonctuelleIsotrope extends Source implements TourneAutour, Editable{

	CouleurL lum;
	Point3 position;
	double rayonIntMax;
	
	/**La liste des attributs de la surface. A tout instant en dehors du corps d'une méthode,
	 * ce dictionnaire est la copie conforme des attributs de l'instance en cours.
	 */
	protected Map<String,Entrable> attributs = new HashMap<String,Entrable>();

	//Constructeurs

	public SourcePonctuelleIsotrope(String nom, Point3 p, CouleurL l) {
		super(nom);
		position=p;
		lum=l;
		rayonIntMax=1;
	}

	public SourcePonctuelleIsotrope(String nom, Point3 p,double i) {
		super(nom);
		position=p;
		lum = new CouleurL(1,1,1,i);
		rayonIntMax=1;
	}

	//==========================================
	//Getters

	@Override
	public TypeObjet getTypeObjet() {
		return TypeObjet.Source;
	}

	@Override
	public Point3 getPointRef() {    
		return position;
	}
	
	@Override
	public Point3 getPoint() {
		return position;
	}

	@Override
	public CouleurL getCouleurL() {
		return lum;
	}


	//==================================
	// Edition
	

	@Override
	public Map<String,Entrable> getAttributsEditables() {
		return new HashMap<String,Entrable>(attributs);
	}
	
	@Override
	public void majListeAttributs() {
		attributs.put("Position", new Point(position));
		//attributs.put("Portee", new Scalaire(rayonIntMax));
		attributs.put("Lumiere", new Couleur(lum) );
		
	}

	@Override
	public void maj() {
		position = ((Point) attributs.get("Position")).getValue();
		//rayonIntMax = ((Scalaire) attributs.get("Portee")).getValue();
		lum = ((Couleur) attributs.get("Lumiere")).getValueL();
		
	}
	
	
	//====================================
	//Optique
	
	
	// /!\ HYPOTHESE : indice optique uniforme, ni réfraction, ni réflexion
	@Override
	public Photon getInfluence(Point3 p, Stageable s) {

		R3 dir= position.Vecteur(p);
		double dist= dir.norme2();
		dir=dir.normer();
		
		Photon lux1 = new Photon(position,lum,s);
		lux1.avancer(dir);
		
		if ( !position.estPlusProcheDeQue(p,lux1.getPosition()) )	//si le photon s'est heurté sur un truc avant de l'atteindre
			lux1.coul=CouleurL.noir;
		else if (dist> rayonIntMax) 
			lux1.coul=lux1.coul.multiplieIntensite(rayonIntMax*rayonIntMax/(dist*dist));	//Même calcul que dans getInfluenceTheorique
			
		return lux1;

	}

	
	/**Renvoie la lumière que reçue en p dans une scène vide (décroissance quadratique).
	 * 
	 * @param p
	 * @return
	 */
	public double getInfluenceTheoriqueEn(Point3 p) {
		return lum.getIntensite()*rayonIntMax*rayonIntMax/p.Vecteur(getPoint()).norme2car();
	}




	@Override
	public String toString() {
		return String.format("Source :%n{ Point : %s ; %n LumiÃ¨re: %s}", position.toStringHor(), lum);
	}










	/*  @Override
  public void setScene(Scene sce) {
      sc=sce;
      lux0= new Photon(position,lum,sce);
    }*/


	/*
  public void reset(Point3 p, CouleurL l) {
    position=p;
    lum=l;
    rayonIntMax=1;
  }

  public void reset(Point3 p, Color c, double intensite) {
    position=p;
    lum=new CouleurL(c,intensite);
  }*/

	/*  public Photon getInfluence(Point3 p) {
  R3 dir= position.Vecteur(p);
  double dist= dir.norme2();
  dir=dir.normer();
  Photon lux = new Photon(position,lum,sc);
  lux.avancer(dir);
  if (! p.equals(lux.position))
    lux.coul=CouleurL.noir;
  else if (dist> rayonIntMax) {
    lux.coul=lux.coul.multiplieIntensite(rayonIntMax*rayonIntMax/(dist*dist));
}
  return lux;

  }*/


}
