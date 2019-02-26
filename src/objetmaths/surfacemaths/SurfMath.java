package objetmaths.surfacemaths;

import algLin.Point3;
import algLin.R3;


public interface SurfMath{

  /**Renvoie la distance de m à la surface en suivant d; l'infini si pas d'intersection
   * 
   * @param m
   * @param d
   * @return
   */
  public double dist(Point3 m, R3 d);
  
  /**Renvoie un vecteur normal à la surface en m et normé
   * 
   * @param m
   * @return
   */
  public R3 getNorm(Point3 m);
}
