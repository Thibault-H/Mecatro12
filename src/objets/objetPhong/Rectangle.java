package objets.objetPhong;

import java.awt.Color;
import java.util.Map;

import algLin.Point3;
import algLin.R3;
import objetmaths.surfacemaths.Degre1;
import objetmaths.surfacemaths.RectangleMath;
import objets.editable.Entrable;
import optique.CouleurS;

public class Rectangle extends Surface{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 359582056286544205L;


	public Rectangle(R3 n, Point3 p, double largeur, double longueur, Color c) {
		surf=new RectangleMath(n,p, longueur, longueur);
		listeCouleurs = new CouleurS[] {new CouleurS(c)};
		attributs.put("Normale", n);
		attributs.put("Point part", p);
		attributs.put("Largeur",largeur);
		attributs.put("Longueur",longueur);
		
	}


	public Point3 getPoint() {
		return ((RectangleMath)surf).getPoint();
	}



	public void reset(R3 n, Point3 p,double largeur, double longueur, Color c) {
		((Rectangle) surf).reset(n,p, largeur, longueur, c);
		listeCouleurs = new CouleurS[] {new CouleurS(c)};
	}


	public String toString() {
		return String.format("Rectangle : { Normal = %s ; Centre = %s ; Dimension = %s x %s", surf.getNorm(Point3.origine),((RectangleMath) surf).getPoint(), ((RectangleMath) surf).getLongueur1(), ((RectangleMath) surf).getLongueur2() );
	}

	

	@Override
	public Map<String, Entrable> getAttributsEditables() {
		// TODO Auto-generated method stub
		return null;
	}



}
