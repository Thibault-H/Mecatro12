package optique;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import auxMaths.PointMobile;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import ihm.fenetre1.edition.Editable;
import ihm.fenetre1.edition.Entrable;
import ihm.fenetre1.edition.entrees.Couleur;
import ihm.fenetre1.edition.entrees.Point;
import objets.CategorieObjet;
import objets.TourneAutour;
import objets.objetPhong.Plan;
import objets.scene.SceneRaytracing;
import optique.lumiere.CouleurL;
import optique.lumiere.Lumiere;
import optique.sources.PointLumineux;
import optique.sources.illumination.IlluminationPonctuelle;
import optique.sources.obstruction.ObstructionBasique;


// TODO : isEditable

public class SourcePonctuelleIsotrope extends PointLumineux implements TourneAutour, Editable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -854256641185524200L;
	
	 
	


	//Constructeurs

	public SourcePonctuelleIsotrope(String nom, Point3 p, CouleurL l) {
		super(nom, new IlluminationPonctuelle(p, l, 1.0/Math.pow(4*Math.PI, 0.5)), p);	
	}

	public SourcePonctuelleIsotrope(String nom, Point3 p,double i) {
		this(nom,p,new CouleurL(Color.WHITE, i));
	}

	//==========================================
	//Getters

	@Override
	public CategorieObjet getTypeObjet() {
		return CategorieObjet.Source;
	}

	@Override
	public Point3 getPointRef() {    
		return super.getPoint();
	}
	
	public CouleurL getLumiereCentrale() {
		return ((IlluminationPonctuelle)illum).getCouleur();
	}



	//==================================
	// Edition
	

	@Override
	public Map<String,Entrable> getAttributsEditables() {
		return new HashMap<String,Entrable>(attributs);
	}
	
	@Override
	public void majListeAttributs() {
		attributs.put("Position", new Point(getPoint()));
		//attributs.put("Portee", new Scalaire(rayonIntMax));
		attributs.put("Lumiere", new Couleur(getLumiereCentrale()) );
		
	}

	@Override
	public void maj() {
		Point3 position = ((Point) attributs.get("Position")).getValue();
		//rayonIntMax = ((Scalaire) attributs.get("Portee")).getValue();
		CouleurL lum = ((Couleur) attributs.get("Lumiere")).getValueL();
		
		illum = new IlluminationPonctuelle(position, lum, 1.0/3.544908);
		voil = new ObstructionBasique(position);
		
	}
	
	
	//====================================
	//Autres

	@Override
	public String toString() {
		return String.format("Source :%n{ Point : %s ; %n Lumière: %s}", getPoint().toStringHor(), getLumiereCentrale());
	}
	
	public static void main(String[] args) {
		Point3 source = Point3.origine;
		CouleurL c = new CouleurL(Color.WHITE,10*4*Math.PI);
		String nom = "nom";
		SourcePonctuelleIsotrope s = new SourcePonctuelleIsotrope(nom, source, c);
		
		
		SceneRaytracing sc = new SceneRaytracing();
		
		VectUnitaire norm = R3.ux;
		Point3 ptPart = Point3.origine.plus(new R3(5,0,0));
		Plan p = new Plan("", norm, ptPart, Color.BLACK);
		sc.ajouter(p);
		

		Point3 pt = Point3.origine.plus(new R3(2,0,0));
		PointMobile photon = new PointMobile(Point3.origine);
		sc.avancerJusquauChoc(photon, source.Vecteur(pt));
		
		Lumiere lum = s.getInfluence(photon, sc);
		System.out.println(lum);
		
	}












}
