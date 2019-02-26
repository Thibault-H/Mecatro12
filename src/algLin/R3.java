package algLin;

import java.io.Serializable;

import corps.Parametres;
import objets.editable.Entrable;
import objets.editable.TypeEntrable;

/** La classe R3 sert à implémenter les vecteurs de R3.
 * 
 * @author Adel
 *
 */
public class R3 implements Serializable, TesteurNullite, Entrable{
  
  /**
   * 
   */
  private static final long serialVersionUID = -8818382864089358062L;
  private double x;
  private double y;
  private double z;
  public double epsilon = 0.000001;
  public final static R3 zero = new R3();
  public final static R3 ux = new R3(1,0,0), uy = new R3(0,1,0), uz =new R3(0,0,1); 
  
  
  public double get1() {
    return x;
  }
  public double get2() {
    return y;
  }
  public double get3() {
    return z;
  }
  

  
  /** Renvoie la ie composante du vecteur
   * 
   * @param i
   * @return
   */
  public double getVal(int i) {
    if (i==1)
      return x;
    else if (i==2)
      return y;
    else if (i==3)
      return z;
    else throw new IndexOutOfBoundsException("Seulement 3 composantes dans un vecteur!");
  }
  
  public double getTheta() {
	  double r = x*x+y*y;
	  if (estNul(r))
		  return 0;
	  else 
		  if (y>0)
			  return Math.acos(x/Math.pow(r, 0.5));
		  else return 2*Math.PI-Math.acos(x/Math.pow(r,0.5));
  }
  
  public double getPhi() {
	  double r = norme2car();
	  if (estNul(r))
		  return 0;
	  else return Math.acos(z/r);
  }
  
  
  //====================================
  // Constructeurs
  public R3() {
    x=y=z=0;
  }
  
  public R3(double a, double b, double c) {
    x=a;
    y=b;
    z=c;
  }
  
  public R3(R3 v) {
    x=v.get1();
    y=v.get2();
    z=v.get3();
  }
  
  public R3(double[] tab) {
    if (tab.length != 3) 
      throw new IllegalArgumentException("On ne peut pas faire de vecteur de R3 aavec ce tableau!");
    else {
      x=tab[0];
      y=tab[1];
      z=tab[2];
    } 
  }
  
  //==================================================
  public static R3 SpheriqueToCart(double r, double theta, double phi) {
	  if (r<0)
		  throw new IllegalArgumentException("r doit �tre positif!");
	  else
		  return new R3(r*Math.sin(phi)*Math.cos(theta), r*Math.sin(phi)*Math.sin(theta) ,r*Math.cos(phi));
  }
  
  
  
  
  //============================================
  
  //Opérations alèbriques

    //Produit avec un scalaire
  
  /** Produit externe
   * 
   * @param l
   * @return
   */
  public R3 prod(double l) {
    return new R3(l*x,l*y,l*z);
  }
  
  /** Methode statique de Produit externe
   * 
   * @param l
   * @param u
   * @return
   */
  public static R3 prod(double l, R3 u) {
    return u.prod(l);
  }
  
  
  //Addition
  
  /**Remplace le signe "-".
   * 
   * @return
   */
  
  public R3 opp() {
    return new R3(-x,-y,-z);
  }

  /** Addition vectorielle"
   * 
   * @param v
   * @return
   */
  public R3 plus(R3 v) {
    return new R3(x+v.get1() , y+v.get2() , z+v.get3());
  }
  
  /** Addition d'un nombre quelconque de vecteurs
   * 
   * @param args
   * @return
   */
  public static R3 sum(R3... args) {
    R3 result= R3.zero;
    for ( R3 x : args )
      result= result.plus(x);
    return result;
  }
  
  public static R3 barycentre(R3... args) {
		return R3.sum(args).prod(1.0/args.length);
	}
  
  /** Soustraction vectorielle
   * 
   * @param v
   * @return
   */
  public R3 moins(R3 v) {
    return new R3(x-v.get1() , y-v.get2() , z-v.get3());
  }
  
    //Produit scalaire
  
  /**Produit scalaire
   * 
   * @param v
   * @return
   */
  public double scal(R3 v) {
    return x*v.get1() + y*v.get2() + z*v.get3();
  }
  
  /** Methode statique de produit scalaire
   * 
   * @param u
   * @param v
   * @return
   */
  public static double scal(R3 u , R3 v) {
    return u.scal(v);
  }
  
    //Produit vectoriel
  
  /** Produit vectoriel
   * 
   * @param v
   * @return
   */
  public R3 vect(R3 v) {
    return new R3(y*v.get3() - v.get2()*z , z*v.get1() - v.get3()*x , x*v.get2() - v.get1()*y);
  }
  
  
  /** Methode statique de produit vectoriel
   * 
   * @param u
   * @param v
   * @return
   */
  public static R3 vect(R3 u, R3 v) {
    return u.vect(v);
  }
  
  
  public R3 transformation(int type, double incrrot, M3 base, Point3 oeil) {// M3=(uy,-ux,uz)
    if (type==-1)
      return appliquerRot(base.getC1() , -incrrot);
    else if (type==-2)
      return appliquerRot(base.getC1() , incrrot);
    else if (type==-3)
      return appliquerRot(base.getC2() , incrrot);
    else if (type==-4)
      return appliquerRot(base.getC2() , -incrrot);
    else if (type==-5)
      return appliquerRot(base.getC3() , -incrrot);
    else if (type==-6)
      return appliquerRot(base.getC3() , incrrot);
    
    else return this; //ne fais rien
  }
  
  
  public R3 transformationTrans(int type, double incrtrans, M3 base, Point3 oeil) {// M3=(uy,-ux,uz)
    if (type==-1)
      return appliquerRot(base.getC1() , -incrtrans);
    else if (type==-2)
      return appliquerRot(base.getC1() , incrtrans);
    else if (type==-3)
      return appliquerRot(base.getC2() , incrtrans);
    else if (type==-4)
      return appliquerRot(base.getC2() , -incrtrans);
    else if (type==-5)
      return appliquerRot(base.getC3() , -incrtrans);
    else if (type==-6)
      return appliquerRot(base.getC3() , incrtrans);
    
    else return this; //ne fais rien
  }
  
  //Opérations métriques
  

  public boolean estNul() {
    return estNul(norme2car());
  }
    //Norme
  
  /** Carre de la norme
   * 
   * @return
   */
  public double norme2car() {
    return x*x+y*y+z*z;
  }
  
  public double norme2() {
    return Math.pow(x*x+y*y+z*z, 0.5);
  }
  
    
  
  public R3 normer() {
    if (estNul())
      throw new IllegalArgumentException("0 ne peut être normé!");
    return prod(1/norme2());
  }
  
  public static R3 normer(R3 u) {
    return u.normer();
  }
  
  public M3 base() { //renvoie une base directe exprimée dans B0 dont le premier vect est l'instance
    R3 u=zero;
    R3 v=zero;
    R3 w=zero;
    if (equals(zero))
      throw new IllegalArgumentException();
    else {
      u=normer();
      if ( !estNul(u.get1()) || !estNul(u.get2())) {
        v = new R3( -u.get2(), u.get1(), 0);
        w = u.vect(v); 
      }
      else {
        u=uz;
        v=ux;
        w=uy;
      }
    }
    return new M3(u,v,w);
  }
  
  
  
  //===============================================
  
  public R3 appliquerRot(R3 axe, double theta) {
    if (this.equals(zero))
      return zero;
    else return (M3.rotation(axe, theta).fois(this)).normer().prod(norme2());
  }

  
  /**Renvoie le symétrique de this par rapport à b (pas forc�ment norm�)
   * 
   * @param b
   */
  public R3 symetrieOrth( R3 b) {
    R3 c=b.normer().prod(scal(b.normer())); //La projection orthogonale de a sur la droite vectorielle engendrée par b
    return c.plus(c).moins(this);
  }
  
  /**Renvoie this.b >0
   * 
   * @param b
   * @return
   */
  public boolean estMemeSens(R3 b) {
	  return scal(b)>=0;
  }
  
  /**Renvoie b ou -b pour obtenir un produit scalaire negatif
   * 
   * @param b
   * @return
   */
  public R3 bonSens(R3 b) {
    if (scal(b) >=0)
      return b.opp();
    else return b;
  }
  
  /**Retourne b si x negatif
   * 
   * @param b
   * @param x
   * @return
   */
  public R3 bonSens(R3 b, double x){
    if (x <=0)
      return b.opp();
    else return b;
  }
  
  @Override
  public TypeEntrable getTypeEntrable() {
  	return TypeEntrable.Vecteur;
  }
  
  
  public boolean equals(Object o){
    if (o instanceof R3)
      return ( estNul((moins((R3) o)).norme2()));
    else return false;
  }
  
  public String toString(){
    return String.format( "|%.3g|%n|%.3g|%n|%.3g|", x,y,z);
  }
  
  public String toStringHor() {
    return String.format( "|%.3g|%.3g|%.3g|", x,y,z);
  }
  
  //=======================================
  public static void main(String[] args) {
    R3 x=new R3(1,0,0);
    R3 y = new R3(-1,0,0);
    System.out.println(R3.barycentre(new R3[]{x,y}));
  }

}
