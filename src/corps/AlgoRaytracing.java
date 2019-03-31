package corps;

import algLin.Point3;
import algLin.R3;
import objets.ObjetRaytracing;
import objets.scene.Stageable;
import optique.CouleurL;
import optique.Photon;

public class AlgoRaytracing {
	  Point3 pointDepart;
	  Raytracing r;
	  R3 dir;
	  ParametresRaytracing par;
		Stageable s;
	  
	  

	  
	  public AlgoRaytracing(Raytracing ray, int l, int h) {
	    r=ray;
	    par=ray.param;
	    s=ray.sc;
	    pointDepart=par.getDepart(l,h);
	    dir=par.getOeil().Vecteur(pointDepart).normer();
	  }
	  
	  
	  
	  public CouleurL getCouleurPixel() {
	    Photon p= new Photon(pointDepart, s);
	    ObjetRaytracing renc = p.avancer(dir);
	    return renc.getColor(p.getPosition(), dir,s, par.getMiroir());
	  }
}
