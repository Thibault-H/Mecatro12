package objets.objetPhong;

import java.awt.Color;
import algLin.Point3;
import algLin.R3;
import objetmaths.surfacemaths.RectangleMath;
import objets.editable.Couleur;
import objets.editable.Point;
import objets.editable.Scalaire;
import objets.editable.Vecteur;
import optique.CouleurS;

public class Rectangle extends Surface{

	
	private R3 normale;
	private Point3 centre;
	private double largeur;
	private double longueur;

	public Rectangle(String name, R3 n, Point3 p, double largeur, double longueur, Color c) {
		super(name);
		normale = n;
		centre= p;
		this.largeur = largeur;
		this.longueur = longueur;
		listeCouleurs = new CouleurS[] {new CouleurS(c)};
		
		surf=new RectangleMath(normale,centre, longueur, longueur);
		majListeAttributs();
	}
	
	//Méthodes d'édition
	
	@Override
	public void majListeAttributs() {
		attributs.put("Normale", new Vecteur(normale));
		attributs.put("Centre", new Point(centre));
		attributs.put("Largeur",new Scalaire(largeur));
		attributs.put("Longueur",new Scalaire(longueur));
		attributs.put("Couleur", new Couleur(listeCouleurs[0]));
	}

	@Override
	public void maj() {
		centre = ((Point) attributs.get("Centre")).getValue();
		normale = ((Vecteur) attributs.get("Normale")).getValue();
		longueur = ((Scalaire) attributs.get("Longueur")).getValue();
		largeur = ((Scalaire) attributs.get("Largeur")).getValue();
		listeCouleurs[0] = ((Couleur) attributs.get("Couleur")).getValueS();
		surf=new RectangleMath(normale,centre, longueur, longueur);
	}


	
	//Autres

	public Point3 getPoint() {
		return ((RectangleMath)surf).getPoint();
	}



	public void reset(R3 n, Point3 p,double largeur, double longueur, Color c) {
		((Rectangle) surf).reset(n,p, largeur, longueur, c);
		listeCouleurs = new CouleurS[] {new CouleurS(c)};
	}


	@Override
	public String toString() {
		return String.format("Rectangle : { Normal = %s ; Centre = %s ; Dimension = %s x %s", surf.getNorm(Point3.origine),((RectangleMath) surf).getPoint(), ((RectangleMath) surf).getLongueur1(), ((RectangleMath) surf).getLongueur2() );
	}



	





}
