package corps;

import java.awt.Color;

import algLin.Point3;
import algLin.R3;
import objets.ObjetMecatro;
import objets.ObjetRaytracing;
import objets.objetMecatro.MiroirRectangle;
import objets.scene.SceneMecatro;
import optique.CouleurL;
import optique.Photon;

public class AlgoMecatro {

	  Mecatro m;
	  ParametresMecatro par;
	  SceneMecatro s;
	  
	  int larg;
	  int haut;
	  

	  
	  public AlgoMecatro(Mecatro mec, int l, int h) {
	    m=mec;
	    par=mec.param;
	    s=mec.sc;
	    larg=l;
	    haut=h;
	  }
	  
	  private CouleurL getCouleurPoint(Point3 pt) {
		  double result= 0;
		    for (ObjetMecatro m : s.listesurfs)
		    	result+=(m.getIntensiteRecue(pt));
		    return s.getSource().getCouleurL().multiplieIntensite(result);
	  }
	  
	  public CouleurL getCouleurPixel() {
	    return(getCouleurPoint( par.pixToPoint3(larg, haut,  Point3.origine.plus(R3.ux.prod(50)) ,R3.ux)));

	  } 
	  
	  public static void main(String[] args) {
		  Mecatro mec = new Mecatro(10);
		  Raytracing r = new Raytracing();
		  ObjetMecatro m = new MiroirRectangle("Miroir 1 ",R3.ux, Point3.origine.moins(R3.ux), 20, 20);
		  mec.ajouter(m, r);
		  
		  Point3 pt1 = Point3.origine.plus(R3.ux);
		  Point3 pt2 = Point3.origine.plus(R3.ux).plus(R3.ux);
		  
		  AlgoMecatro alg = new AlgoMecatro(mec,0,0);
		  System.out.println(alg.getCouleurPoint(pt1));
		  System.out.println(alg.getCouleurPoint(pt2));
		  
	  }
	  
}

