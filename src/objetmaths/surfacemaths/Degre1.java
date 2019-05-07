package objetmaths.surfacemaths;

import java.io.Serializable;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import corps.tableauCouleurs.Parametres;

public class Degre1 implements SurfMath, Serializable{
  

  /**
   * 
   */
  private static final long serialVersionUID = 8920204650877402817L;
  R3 normal;
  Point3 ptPart;
  

  
  
  public Point3 getPoint() {
    return ptPart;
  }
  
  public Degre1( R3 n, Point3 p) {
    normal=n.normer();
    ptPart=p;
  }
  
  public void reset(R3 n ,Point3 p) {
    normal=n.normer();
    ptPart=p;
  }
  
  
  @Override
public double dist(Point3 m, R3 d) {
    d=d.normer();
    double a = d.scal(normal);
    if (Math.abs(a)<Parametres.h)
      return Double.POSITIVE_INFINITY;
    else {
      double result= (m.Vecteur(ptPart).scal(normal)/a);
      if (result <Parametres.h)
        return Double.POSITIVE_INFINITY;
      else
        return result;
    }
  }
  


  
  @Override
public R3 getNorm(Point3 m) {
    return normal;
  }
  
  
  @Override
public String toString() {
    return String.format("Degré 1 : { Normal = %s ; Point particulier = %s }", getNorm(Point3.origine),getPoint() );
  }

 
  
  public static void main(String[] args) {
    Degre1 p = new Degre1(R3.ux,Point3.origine);
    Point3 m = Point3.origine.plus(new R3(3,0,0));
    System.out.println(p.dist(m, new R3(-1,1,0)));
  }
  
  
  
}
