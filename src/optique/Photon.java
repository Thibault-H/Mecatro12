package optique;

import java.awt.Color;
import java.io.Serializable;
import algLin.Point3;
import algLin.R3;
import objets.ObjetRaytracing;
import objets.scene.Scene;
import objets.scene.SceneRaytracing;


public class Photon implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 6334639455076377916L;
  public Point3 position;
  SceneRaytracing sc;
  R3 v=R3.zero;
  CouleurL coul;

  
  //Getters
  /**
   * @return the position
   */
  public Point3 getPosition() {
    return position;
  }
  /**
   * @return the sc
   */
  public SceneRaytracing getSc() {
    return sc;
  }
  /**
   * @return the v
   */
  public R3 getV() {
    return v;
  }
  public CouleurL getCoul() {
    return coul;
  }
  
  /**Utilisee pour reinitialiser le photon de la classe SourcePonctuelleIsotrope
   * 
   * @param l
   * @param p
   */
  public void reset(Photon origine) {
    coul=origine.coul;
    position=origine.position;
    sc=origine.sc;
  }
  
  //Constructeurs
  
  public Photon(Point3 p, CouleurL c, SceneRaytracing sce) {
    position = p;
    coul = c;
    sc=sce;
  }
  
  public Photon(Point3 p, SceneRaytracing sce) {
    position = p;
    coul = new CouleurL(1,1,1,1);
    sc=sce;
  }
  
  
  //====================================
  
  public ObjetRaytracing avancer(R3 dir) {  //dir est normé
    double distance= Double.MAX_VALUE;
    double inter;
    ObjetRaytracing result=sc.getFond();
    for (ObjetRaytracing o : sc.getSurfs()) {
      if ((inter =o.dist(position,dir)) < distance) {
        distance = inter;
        result=o;
      }
    }
    position = position.plus(R3.prod(distance, dir));
    v=dir;
    return result;
  }
 
  
  
  public static void main(String[] args) {
    Point3 position= Point3.origine;
    CouleurL lum=CouleurL.noir;
    SceneRaytracing sc = new SceneRaytracing();
    Photon lux0 = new Photon(position,lum,sc);
    Photon lux1 = new Photon(Point3.origine,CouleurL.noir,sc);
    lux1.reset(lux0);
    lux1.position=Point3.origine.plus(R3.ux);
    System.out.println(lux1.position);
  }
 
  
  
  
  
  
}
