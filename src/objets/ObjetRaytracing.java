package objets;


import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import objets.scene.Objet;
import objets.scene.Stageable;
import optique.lumiere.CouleurL;


public abstract class ObjetRaytracing extends Objet {

	
  protected ObjetRaytracing(String nom) {
		super(nom);
	}




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
  public abstract CouleurL getColor(Point3 m, VectUnitaire d, Stageable s);
  
  /**Renvoie la couleur de l'objet en le point m vu selon d, en prenant au mieux en compte l'environnement dans sa complexité
   * 
   * @param m
   * @param d
   * @return
   */
  public CouleurL getColor(Point3 m, R3 d, Stageable s) {
	  return getColor(m,new VectUnitaire(d),s);
  }
  
  
  
  /**Renvoie la couleur avec un terme de réflexion de type miroir
   * 
   * @param m
   * @param d
   * @param ref
   * @return
   */
  public abstract CouleurL getColor(Point3 m, R3 d, Stageable s, int ref);
  
  /**Simple prévisualisation : renvoie la couleur intrinsèque de l'objet illuminée par la lumière ambiante
   * 
   * @param m
   * @param d
   * @return
   */
  public abstract CouleurL getColorSimple(Point3 m, R3 d, Stageable s);
  


  
  
  @Override
public String toString() {
    return this.getClass().getName();
  }
}