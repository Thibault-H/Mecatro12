package objets.scene;

import java.io.Serializable;

import objets.objetPhong.Horizon;

public abstract class Scene implements Serializable{
  

  private static final long serialVersionUID = 1302058706739955574L;

 
  protected double intensiteBlanc;
  
  
  public Scene() {
  }
  
  public double getBlanc() {
    return intensiteBlanc;
  }
  
  
  public abstract Horizon getFond();
    
  //Modification de la scene
  
  public static Object[] ajouterCase(Object[] tab, Object o) {
    Object[] result = new Object[tab.length+1];
    result[tab.length]=o;
    return result;
  }
   
 
  
}
