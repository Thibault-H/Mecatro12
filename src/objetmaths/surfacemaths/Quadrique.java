package objetmaths.surfacemaths;

import java.io.Serializable;
import algLin.M3;
import algLin.Point3;
import algLin.R3;
import corps.ParametresRaytracing;


/**Quadrique definie par Q(X)= tXΦX + 2tLX + j=0
 * 
 * @author Adel
 *
 */
public class Quadrique implements SurfMath, Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = -8570190406460136402L;
  M3 phi;
  R3 l;
  double j;
  
  //Constructeur
  public Quadrique(M3 Q, R3 v, double a) {
    phi=Q;
    l=v;
    j=a;
  }
  
  
  //=======================================
  //Methodes statiques utiles  
  
  
  /**Renvoie, entre a et b, le plus petit nombre positif, et l'infini sinon
   * 
   * @param a
   * @param b
   * @return
   */
  public static double minpos(double a, double b) {
    if (a<0) {
      if (b>=0) return b;
      else return Double.POSITIVE_INFINITY;
    }
    else if (b>=0) return Math.min(a, b);
    else return a;    
    }
  
  
  /** Entrée: polynôme de deg2 exprimé dans le repère (omega,B) avec B orthonormé 
  Sortie: son expression dans le repère canonique de R3
**/  
  public static Quadrique chgmtRepereCan(Quadrique Q, R3 u, R3 v, R3 w, Point3 omega) {
    return chgmtRepereCan(Q, new M3(u,v,w), omega);
  }
  
  
  
  /** Entrée: polynôme de deg2 exprimé dans le repère (omega,B) avec B orthonormé (P matrice de passage de Bc à B)
        Sortie: son expression dans le repère canonique de R3
   **/  
  public static Quadrique chgmtRepereCan(Quadrique Q, M3 P, Point3 omega) {
    R3 Oomega = Point3.origine.Vecteur(omega);
    M3 tP = P.transpose();  //Matrice orthogonale
    M3 phi1=P.fois(Q.phi).fois(tP); //independant de l'origine
    R3 L0 = P.fois(Q.l);  //Tout d'abord, simple changement de base
    R3 L1 = L0.plus(phi1.fois(Oomega).opp()); //Maintenant, vrai changement de repère
    double j1 = phi1.gram(Oomega, Oomega) - 2*L0.scal(Oomega) + Q.j;
    return new Quadrique(phi1,L1,j1);
  }
  
  
  
  
  
  
  public double dist(Point3 m, R3 d) {
    R3 om = Point3.origine.Vecteur(m);
    double a= phi.gram(d, d);
    double bsur2 = d.scal(l.plus(phi.fois(om)));
    double c = phi.gram(om, om) + 2*l.scal(om) + j;
    if (Math.abs(a)<ParametresRaytracing.h)
      return Double.POSITIVE_INFINITY;
    else {
      double deltasur4=bsur2*bsur2 - a*c;
      if (deltasur4 <0)
        return Double.POSITIVE_INFINITY;
      else {
        double d1=Math.pow(deltasur4,0.5);
        return minpos( -(bsur2+d1)/a , (-bsur2+d1)/a);
      }
    }
  }

  
  public R3 getNorm(Point3 m) {
    try {
    return (l.plus(phi.fois(Point3.origine.Vecteur(m)))).normer();
    }
    catch (IllegalArgumentException e) {
      return null;
    }
  }
  


}
