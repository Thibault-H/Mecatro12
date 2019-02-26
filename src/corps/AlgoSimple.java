package corps;
import java.awt.Color;
import algLin.M3;
import algLin.Point3;
import algLin.R3;
import objets.ObjetRaytracing;
import optique.CouleurL;
import optique.Photon;

public class AlgoSimple {
  
  Point3 pointDepart;
  Raytracing r;
  R3 dir;
  
  

  public AlgoSimple(Raytracing ray, int l, int h) {
    r=ray;
    pointDepart=r.getParam().getDepart(l,h);
    dir= r.getParam().getOeil().Vecteur(pointDepart).normer();
  }
  
  
  
  public CouleurL getCouleurPixel() {
    Photon p= new Photon(pointDepart, r.sc);
    ObjetRaytracing renc = p.avancer(dir);
    return renc.getColorSimple(p.position, dir);
  }

  
  
  
  
  
  
}
