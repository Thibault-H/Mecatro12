package objets.objetPhong;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import algLin.Point3;
import algLin.R3;
import objetmaths.surfacemaths.Degre1;
import objets.editable.Editable;
import objets.editable.Entrable;
import optique.CouleurS;

public class Plan extends Surface{

	

	public Plan(R3 n, Point3 p, Color c) {
		surf=new Degre1(n,p);
		attributs = new HashMap<String,Entrable>();
		attributs.put("Normale",n);
		attributs.put("Point particulier",p);
		attributs.put("Couleur", new CouleurS(c));
		listeCouleurs = new CouleurS[] {new CouleurS(c)};
	}


	public Point3 getPoint() {
		return ((Degre1)surf).getPoint();
	}



	public void reset(R3 n, Point3 p, Color c) {
		((Degre1) surf).reset(n,p);
		listeCouleurs = new CouleurS[] {new CouleurS(c)};
	}


	public String toString() {
		return String.format("Plan : { Normal = %s ; Point particulier = %s", surf.getNorm(Point3.origine),((Degre1) surf).getPoint() );
	}





}
