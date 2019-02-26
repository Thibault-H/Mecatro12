package objets.objetPhong;

import java.awt.Color;
import algLin.M3;
import algLin.Point3;
import algLin.R3;
import objetmaths.surfacemaths.Quadrique;
import objets.TourneAutour;
import optique.CouleurS;

public class Sphere extends Surface implements TourneAutour{
  
  
  Point3 centre;
  double rayon;
  
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
    maj();
  }


  public void reset(double r, Point3 p, Color c) {
    rayon=r;
    centre=p;
    listeCouleurs[0]=new CouleurS(c);
    maj();
  }
  
  
  public void maj() {
    surf = Quadrique.chgmtRepereCan( new Quadrique(M3.id,R3.zero,-rayon*rayon)  , M3.id, centre);
  }
  //Constructeur
  
  
  public Sphere(Point3 m, double r, Color c) {
    surf = Quadrique.chgmtRepereCan( new Quadrique(M3.id,R3.zero,-r*r)  , M3.id, m);
    centre=m;
    rayon=r;
    listeCouleurs = new CouleurS[] {new CouleurS(c)};
  }
  
  
  
  public String toString() {
    return String.format("Sphere : { Centre = %s ; Rayon = %3f }", centre, rayon);
  }



  @Override
  public Point3 getPointRef() {
    return centre;
  }



}
