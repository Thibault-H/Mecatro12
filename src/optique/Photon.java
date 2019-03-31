package optique;

import java.io.Serializable;

import algLin.Point3;
import algLin.PointMobile;
import algLin.R3;
import objets.ObjetRaytracing;
import objets.scene.Stageable;
import objets.scene.SceneRaytracing;


public class Photon implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 6334639455076377916L;
  private PointMobile position;
  Stageable sc;
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
  public Stageable getSc() {
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
  
  public Photon(Point3 p, CouleurL c, Stageable sce) {
    position = new PointMobile(p);
    coul = c;
    sc=sce;
  }
  
  public Photon(Point3 p, Stageable sce) {
    this(p,new CouleurL(1,1,1,1), sce);
  }
  
  
  //====================================
  
  public ObjetRaytracing avancer(R3 dir) {  //dir est normé
    v=dir;
    return sc.avancerJusquauChoc(position, dir);
  }
 
  
  
  public static void main(String[] args) {
    Point3 position= Point3.origine;
    CouleurL lum=CouleurL.noir;
    Stageable sc = new SceneRaytracing();
    Photon lux0 = new Photon(position,lum,sc);
    Photon lux1 = new Photon(Point3.origine,CouleurL.noir,sc);
    lux1.reset(lux0);
    lux1.position=new PointMobile(Point3.origine.plus(R3.ux));
    System.out.println(lux1.position);
  }
 
  
  
  
  
  
}
