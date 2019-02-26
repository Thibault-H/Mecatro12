package objets;

import algLin.M3;
import algLin.Point3;
import algLin.R3;

public interface AFaireTourner {
  
  
  /**Renvoie la base de référence de l'objet
   * 
   * @return
   */
  public M3 getBaseRef();
  
  
  /**Faire tourner la base autour d'un axe
   * 
   * @param val
   * @param axe
   */
  public void faireTourner(double val, R3 axe);
  
  
  /**Faire tourner la base autour de 3 axes donnés par "base"
   * 
   * @param val
   * @param base
   */
  public default void faireTourner(R3 val, M3 base) {
		faireTourner(val.get1(),base.getC1());
		faireTourner(val.get2(),base.getC2());
		faireTourner(val.get3(),base.getC3());
  }
  
  /**Faire tourner autour de ses propres axes de référence
   * 
   * @param val
   */
  public default void faireTourner(R3 val) {
	  faireTourner(val,getBaseRef());
  };
  
  public default void faireTournerAvecJoystick(int type, double incrrot, M3 base, Point3 oeil) {
	  if (type==-1)
	      faireTourner(-incrrot, base.getC1() );
	    else if (type==-2)
	      faireTourner( incrrot, base.getC1());
	    else if (type==-3)
	      faireTourner( incrrot, base.getC2());
	    else if (type==-4)
	      faireTourner(- incrrot, base.getC2());
	    else if (type==-5)
	      faireTourner(- incrrot, base.getC3());
	    else if (type==-6)
	      faireTourner( incrrot, base.getC3());
  };
  
}
