package objetmaths.surfacemaths;

import java.io.Serializable;
import algLin.M3;
import algLin.Point3;
import algLin.R3;
import corps.Parametres;
import corps.ParametresRaytracing;

public class CubeMath2 implements SurfMath, Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = -2578525217818515359L;
  Point3 centre;
  M3 base;
  double cote;
  private Degre1[] tabFaces;
  
  
  
  public CubeMath2(Point3 p, double c, M3 m){
    centre=p;
    cote=c;
    if (!m.estOrthogonale()) throw new IllegalArgumentException();
    base=new M3(m);
    tabFaces=new Degre1[6];
    creerFaces();
  }
  
  public CubeMath2(Point3 p, double c){
    centre=p;
    cote=c;
    base=M3.id;
    tabFaces=new Degre1[6];
    creerFaces();
  }
  //================
  //Getters and Setters
  /**
   * @return the centre
   */
  public Point3 getCentre() {
    return centre;
  }

  /**
   * @param centre the centre to set
   */
  public void setCentre(Point3 centre) {
    this.centre = centre;
    creerFaces();
  }

  /**
   * @return the base
   */
  public M3 getBase() {
    return base;
  }

  /**
   * @param base the base to set
   */
  public void setBase(M3 base) {
    this.base = base;
    creerFaces();
  }

  /**
   * @return the cote
   */
  public double getCote() {
    return cote;
  }

  /**
   * @param cote the cote to set
   */
  public void setCote(double cote) {
    this.cote = cote;
    creerFaces();
  }
  
  
  
  /** Definit la base orthonormee avec un unique vecteur
   * 
   * @param v
   */
  public void setBase(R3 v) {
    v=v.normer();
    setBase(v.base());
    creerFaces();
  }

  /** Definit la base orthonormee avec uniquement deux vecteurs
   * 
   * @param v
   */
  public void setBase(R3 u, R3 v) {
    u=u.normer();
    v=v.normer();
    setBase(new M3(u,v,u.vect(v)));
    creerFaces();
  }
  //========================
  public void reset(Point3 p, double c, R3 valeurRot, M3 baseRot) {
    centre=p;
    cote=c;
    base = M3.tripleRotation(valeurRot, baseRot).fois(base);
    creerFaces();
  }
  
  /**Faire tourner la base autour d'un unique axe
   * 
   * @param axe
   * @param theta
   */
  public void faireTourner(R3 axe, double theta) {
    base = M3.rotation(axe, theta).fois(base);
    creerFaces();
  }
  
  /**Faire tourner la base autour de 3 axes donnés par "baseRef"
   * 
   * @param valeurs
   * @param baseRef
   */
  public void faireTourner(R3 valeurs, M3 baseRef) {
    base= M3.tripleRotation(valeurs, baseRef).fois(base);
    creerFaces();
    
  }
  
  /**Faire tourner la base autour de ses propres axes
   * 
   * @param valeurs
   */
  public void faireTourner(R3 valeurs) {
    base= M3.tripleRotation(valeurs, base).fois(base);
    creerFaces();
    
  }  
  
  
  /**Crée les faces à partir de la base, dont le point particulier est le centre
   * 
   */
  public void creerFaces() {
    tabFaces[0]=new Degre1(base.getC1(), centre.plus(base.getC1().prod(cote/2)));
    tabFaces[1]=new Degre1(base.getC1().opp(), centre.plus(base.getC1().prod(-cote/2)));
    
    tabFaces[2]=new Degre1(base.getC2(), centre.plus(base.getC2().prod(cote/2)));
    tabFaces[3]=new Degre1(base.getC2().opp(), centre.plus(base.getC2().prod(-cote/2)));
    
    tabFaces[4]=new Degre1(base.getC3(), centre.plus(base.getC3().prod(cote/2)));
    tabFaces[5]=new Degre1(base.getC3().opp(), centre.plus(base.getC3().prod(-cote/2)));
    
  }
  
  
  /**Soit une face p donnée par i (l'indice dans le tableau de faces) et de coté "cote"; soit m un point sur le plan de la face, m est-il dans la face?
   * 
   * @param o
   * @param c
   * @param m
   * @return
   */
  public boolean estSurFace(int i, Point3 m) {
    R3 vectOM = base.transpose().fois(centre.Vecteur(m)); //L'expression de OM dans la base adaptee
    double orientationParRapportNormale= vectOM.getVal(i/2 +1); //On récupère la normale de la face, orientée comme on veut
    boolean validerOrient = ( orientationParRapportNormale >=-Parametres.h && i%2==0) || ( orientationParRapportNormale <= Parametres.h && i%2==1);
    if (!validerOrient) return false;
    else {
      if (i/2==0)      return (Math.abs(vectOM.get3())<=cote/2 && Math.abs(vectOM.get2())<=cote/2);
      else if (i/2==1) return (Math.abs(vectOM.get1())<=cote/2 && Math.abs(vectOM.get3())<=cote/2);
      else if (i/2==2) return (Math.abs(vectOM.get2())<=cote/2 && Math.abs(vectOM.get1())<=cote/2);
      else throw new IllegalArgumentException("Mauvais indice rentré");
    }
  }
  
  @Override
  public double dist(Point3 m, R3 d) {
    double result=Double.POSITIVE_INFINITY;
    double resInterm;
    for (int i=0; i<6;i++) {
      resInterm=tabFaces[i].dist(m, d);
      if ( (resInterm < result) && estSurFace(i, m.plus(d.prod(resInterm))) ) {
        result= resInterm;
      }
    }
    return result;
  }

  @Override
 
  public R3 getNorm(Point3 m) {
    int i=0;
    while (!estSurFace(i,m))
      i++;
    return base.getC(i/2 +1);
  }
  
  public static void main(String[] arg) {
    CubeMath2 c= new CubeMath2(Point3.origine, Math.pow(2, 0.5));
    c.setBase(R3.ux.plus(R3.uy),R3.uy.moins(R3.ux));
    Point3 ptTest= Point3.origine;
    Point3 ptTest2 = Point3.origine;
    ptTest=ptTest.plus(new R3(1,1,0));
    ptTest2=ptTest2.plus(new R3(-5,0,0));
    
    System.out.println(c.dist(ptTest, R3.uy.opp().moins(R3.ux).normer()));
    //System.out.println(c.getNorm(ptTest2));
    //System.out.println(c.estSurFace(0,ptTest));
  }
  
}
