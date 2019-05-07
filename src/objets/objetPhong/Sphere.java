package objets.objetPhong;

import java.awt.Color;

import auxMaths.algLin.M3;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import ihm.fenetre1.edition.entrees.Couleur;
import ihm.fenetre1.edition.entrees.Point;
import ihm.fenetre1.edition.entrees.Scalaire;
import objetmaths.surfacemaths.Quadrique;
import objets.TourneAutour;
import optique.lumiere.CouleurS;

public class Sphere extends Surface implements TourneAutour{


	private Point3 centre;
	private double rayon;

	//Constructeur


	public Sphere(String name, Point3 m, double r, Color c) {
		super(name);
		centre=m;
		rayon=r;
		listeCouleurs = new CouleurS[] {new CouleurS(c)};
		surf = Quadrique.chgmtRepereCan( new Quadrique( M3.id,R3.zero,-rayon*rayon)  , M3.id, centre);
		majListeAttributs();
	}


	//Getters et Setters
	/**
	 * @return the centre
	 */
	public Point3 getCentre() {
		return centre;
	}

	/**
	 * @param centre the centre to set
	 */
	public void setCentre(Point3 centre) {
		this.centre = centre;
		attributs.put("Centre", new Point(centre));
		maj();
	}

	/**
	 * @return the rayon
	 */
	public double getRayon() {
		return rayon;
	}

	/**
	 * @param rayon the rayon to set
	 */
	public void setRayon(double rayon) {
		this.rayon = rayon;
		attributs.put("Rayon", new Scalaire(rayon));
		maj();
	}


	// Edition de l'objet
	
	@Override
	public void majListeAttributs() {
		attributs.put("Centre", new Point(centre));
		attributs.put("Rayon", new Scalaire(rayon));
		attributs.put("Couleur", new Couleur(listeCouleurs[0]));
	}

	@Override
	public void maj() {
		centre = ((Point) attributs.get("Centre")).getValue();
		rayon = ((Scalaire) attributs.get("Rayon")).getValue();
		listeCouleurs[0] = ((Couleur) attributs.get("Couleur")).getValueS();
		surf = Quadrique.chgmtRepereCan( new Quadrique( M3.id,R3.zero,-rayon*rayon)  , M3.id, centre);
	}

	



	public void reset(double r, Point3 p, Color c) {
		rayon=r;
		centre=p;
		listeCouleurs[0]=new CouleurS(c);
		majListeAttributs();
		maj();
	}




	
	//Autres
	
	@Override
	public String toString() {
		return String.format("Sphere : { Centre = %s ; Rayon = %3f }", centre, rayon);
	}

	@Override
	public Point3 getPointRef() {
		return centre;
	}






}
