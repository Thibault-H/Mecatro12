package objets;


import algLin.Point3;
import algLin.R3;
import objetmaths.surfacemaths.SurfMath;
import objets.editable.ModifierObjet;
import objets.scene.ObjetDansScene;
import objets.scene.SceneRaytracing;
import ihm.Fenetre1;
import optique.CouleurL;
import optique.CouleurS;
import java.awt.Color;
import java.awt.Panel;
import java.util.function.*;


public abstract class ObjetRaytracing extends ObjetDansScene<SceneRaytracing> {

	
  /**Renvoie la distance entre le point m est l'objet le long de la demi-droite dirigée par d
   * 
   * @param m
   * @param d
   * @return
   */
  public abstract double dist(Point3 m, R3 d);  //d est normé
  
  /**Renvoie la couleur de l'objet en le point m vu selon d, en prenant au mieux en compte l'environnement dans sa complexité
   * 
   * @param m
   * @param d
   * @return
   */
  public abstract CouleurL getColor(Point3 m, R3 d);
  
  /**Renvoie la couleur avec un terme de réflexion de type miroir
   * 
   * @param m
   * @param d
   * @param ref
   * @return
   */
  public abstract CouleurL getColor(Point3 m, R3 d, int ref);
  
  /**Simple prévisualisation : renvoie la couleur intrinsèque de l'objet illuminée par la lumière ambiante
   * 
   * @param m
   * @param d
   * @return
   */
  public abstract CouleurL getColorSimple(Point3 m, R3 d);
  


  
  
  public String toString() {
    return this.getClass().getName();
  }
}