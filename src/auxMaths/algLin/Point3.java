package auxMaths.algLin;

import java.io.Serializable;

import auxMaths.TesteurNullite;
import corps.tableauCouleurs.Parametres;
import objets.objetPhong.Sphere;

/**Cette classe implÃ©mente les points d'un espace affine de dimension 3.
 * 
 * @author Adel
 *
 */
public class Point3 implements Serializable, TesteurNullite{

  /**
   * 
   */
  private static final long serialVersionUID = 4348267632078983249L;
  protected R3 pos;
  public static final Point3 origine = new Point3(R3.zero);
  
  
  
  
  private Point3(R3 r) {
    pos=r;
  }
  
  public Point3(Point3 p) {
	pos = new R3(p.pos);
}
  
  //Deplacement
 
/**Operateur de translation
   * 
   * @param r
   * @return
   */
  public Point3 plus(R3 r) {
    return new Point3(pos.plus(r));
  }
  
  public Point3 moins(R3 r) {
    return new Point3(pos.moins(r));
  }
  
  /**Renvoie le symétrique de this par rapport au plan de normale norm passant par ref
   * 
   * @param ref
   * @param norm
   * @return
   */
  public Point3 symetrie(Point3 ref, R3 norm) {
	  R3 n = norm.normer();
	  R3 proj= n.prod(ref.Vecteur(this).scal(n));
	  return ref.plus(ref.Vecteur(this).moins(proj.prod(2)));
  }
  

  
  public Point3 appliquerRot(Point3 o, R3 axe, double theta) {
    return o.plus((o.Vecteur(this)).appliquerRot(axe, theta));
  }
  
  public Point3 appliquerRot(Sphere s, R3 axe, double theta) {
    return s.getCentre().plus((s.getCentre().Vecteur(this)).appliquerRot(axe, theta));
  }
  
  public Point3 transformation(int type, double incrtrans, double incrrot, M3 base, Point3 oeil) {// M3=(uy,-ux,uz)
    if (type==1)
      return plus(base.getC2().prod(incrtrans));
    else if (type==2)
      return plus(base.getC2().prod(-incrtrans));
    else if (type==3)
      return plus(base.getC1().prod(incrtrans));
    else if (type==4)
      return plus(base.getC1().prod(-incrtrans));
    else if (type==5)
      return plus(base.getC3().prod(incrtrans));
    else if (type==6)
      return plus(base.getC3().prod(-incrtrans));
    else if (type==-1)
      return appliquerRot(oeil,base.getC1() , -incrrot);
    else if (type==-2)
      return appliquerRot(oeil,base.getC1() , incrrot);
    else if (type==-3)
      return appliquerRot(oeil,base.getC2() , incrrot);
    else if (type==-4)
      return appliquerRot(oeil,base.getC2() , -incrrot);
    else if (type==-5)
      return appliquerRot(oeil,base.getC3() , -incrrot);
    else if (type==-6)
      return appliquerRot(oeil,base.getC3() , incrrot);
    
    else return this;   //ne fais rien
  }
  
  
  //Autres
  /**Renvoie le vecteur qui part du point et arrive vers b
   * 
   * @param b
   * @return
   */
  public R3 Vecteur(Point3 b) {
    return b.pos.moins(pos);
  }
  
  
  public double dist(Point3 p2) {
	  return Vecteur(p2).norme2();
  }
  
  
  
  /**Renvoie true ssi la distance de p à a est inférieur que celle de p à b.
   * En cas de doute (odg de epsilon), renvoie true.
   * @param a
   * @param b
   * @return
   */
  public boolean estPlusProcheDeQue(Point3 a, Point3 b) {
	  return estPlusPetit( Vecteur(a).norme2car(), Vecteur(b).norme2car()); 
  }
  
  
  @Override
public String toString() {
    return pos.toString();
  }
  
  public String toStringHor() {
    return pos.toStringHor();
  }
  
  @Override
  public int hashCode() {
	  return pos.hashCode();
  }
  
  
  
  @Override
  public boolean equals(Object b) {
    if (b instanceof Point3)
      return Vecteur((Point3)b).norme2car()<Parametres.h*Parametres.h;
    else return false;
  }
  
/*  public boolean equals(Object b) {
    if (b instanceof Point3) {
      R3 v = Vecteur((Point3) b);
      return ( (v.get1()<=Raytracing.h) || (v.get2()<=Raytracing.h) || (v.get3()<=Raytracing.h));
    }
    else return false;
  }*/
  
  

/*Renvoie true ssi la famille (normale, mp1, mp2) est libre et directe 
 * 
 */
public static boolean estOrientationPositive(R3 normale, Point3 m, Point3 p1, Point3 p2) {
	return normale.scal(m.Vecteur(p1).vect(m.Vecteur(p2)))> Parametres.h;
	
}
  






public static void main(String[] args) {
	 R3 n= R3.uz;
	 Point3 m, p1, p2;
	 m = Point3.origine;
	 p1 = m.plus(R3.ux);
	 p2 = m.plus(R3.uy);
	 System.out.println(estOrientationPositive(n,m,p1,p2));
 }
}
