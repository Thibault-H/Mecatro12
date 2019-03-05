package objets.objetPhong;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import algLin.Point3;
import algLin.R3;
import objetmaths.surfacemaths.SurfMath;
import objets.ObjetRaytracing;
import objets.editable.Editable;
import objets.editable.Entrable;
import optique.CouleurL;
import optique.CouleurS;
import optique.Photon;
import optique.Source;

public abstract class Surface extends ObjetRaytracing implements Editable{
  int dispoCouleurs=0;    //Disposition de la couleur sur la surface
  CouleurS[] listeCouleurs;
  SurfMath surf;
  
  /**La liste des attributs de la surface. A tout instant en dehors du corps d'une méthode,
   * ce dictionnaire est la copie conforme des attributs de l'instance en cours.
   */
  protected Map<String,Entrable> attributs = new HashMap<String,Entrable>();
  private String nom;
  
  double kd=0.75;
  double ks=0.5;
  double s=100;

  
  public Surface(String name) {
	  nom=new String(name);
  }
  
  
  
  public String getNom() {
	  return new String(nom);
  }
  
  public SurfMath getSurfMath() {
	  return surf;
  }
  
  //===============================================
  // Methodes d'édition
  
  
  @Override
  public Map<String,Entrable> getAttributsEditables() {
	  return new HashMap<String,Entrable>(attributs);
  }
  
  @Override
  public boolean reconstruireAvecAttributs(Map<String, Entrable> inputAttributs) {
	  if ( attributs.keySet().equals(inputAttributs.keySet()) ){	// 1ere vérification de compatibilité des attributs
		  boolean isConformable= true;	// Vérifions que les entrées sont de types compatibles
		  Iterator<String> itr = attributs.keySet().iterator();
		  String tmp;
		  while (isConformable && itr.hasNext()) {
			  tmp=itr.next();
			  isConformable = attributs.get(tmp).conformerA(inputAttributs.get(tmp)); 
		  }
		  if (isConformable) {	//vrai ssi l'opération s'est faite sans heurt, i.e si toutes les entrees sont reconnues et de type compatible. Dans ce cas, attributs a été actualisé conformément à inputAttributs
			  maj();	//on actualise les attributs de l'instance
			  return true;
		  }
		  else {
			  majListeAttributs(); //on annule les éventuels changements qui sont arrivés à attributs pendant le test précédent
			  return false;
		  } 
	  }
	  else return false;
  }
  
  /**
   * Met à jour le dictionnaire attributs au vu des attributs actuels.
   * (à appeler si les attributs de l'instance sont modifiés)
   */
  public abstract void majListeAttributs();
	
  /**
   * Met à jour les attributs de l'instance au vu du dictionnaire d'attributs. 
   * Son comportement géométrique et optique est lui aussi actualisé.
   * (à appeler dans reconstruireAvecAttributs, par exemple)
   */
  public abstract void maj();
  
  
  
  //====================================================
  //====================== Rendu =======================
  //====================================================
  
  //Methodes de geometrie
  
  public double dist(Point3 m, R3 d) {    //d est normÃ©
    return surf.dist(m, d);
  }
  
  
  /**Renvoie un vecteur normÃ© normal a la surface au point m
   * 
   * @param m
   * @return
   */
  public R3 getNorm(Point3 m) {
    return surf.getNorm(m);
  }
  
  
  
  // Methodes de couleur
  
  public CouleurS getCouleurIntra(Point3 m) {
    if (dispoCouleurs==0) 
      return listeCouleurs[0];
    else 
      throw new IllegalArgumentException("Disposition des couleurs invalide!");
  }
  
  public R3 getValueCouleurIntra(Point3 m) {
    CouleurS r = getCouleurIntra(m);
    return new R3(r.getColor().getRed(),r.getColor().getGreen(),r.getColor().getBlue());
  }
  
  
  
  public CouleurL refDif(Photon lux, R3 n, double a) {
    if (a >0 ) {
      return lux.getCoul().multiplieIntensite(kd * a);
    }
    else {
      return CouleurL.noir;
    }
  }
  
  public CouleurL refMiroir(Point3 m, R3 d, int k) {
    if (k==0)
      return CouleurL.noir;
    else {
      Photon lux = new Photon(m,sc);
      ObjetRaytracing o =lux.avancer(d.symetrieOrth(getNorm(m)));  //RÃ©flexion parfaite
      return o.getColor(lux.position, lux.getV(), k-1);
      
    }
  }
  
  
  
  
  @Override
  public CouleurL getColor(Point3 m, R3 d) {
      CouleurL lumTot = sc.getAmbiant().getCouleurL();
      CouleurL lumTotSpec = CouleurL.noir;
      R3 n = d.bonSens(getNorm(m));
      Photon lux;
      double costheta;
      double cosalpha;
      for (Source src : sc.getSources()) {
        lux=src.getInfluence(m);
        costheta= - n.scal(lux.getV());
        if (costheta>0) {
          lumTot = lumTot.plus(lux.getCoul().multiplieIntensite(kd * costheta));  //terme de reflexion diffuse
          cosalpha=Math.abs(d.scal(n.prod(2*costheta).plus(lux.getV()).normer()));  //cos(alpha)=d.c oÃ¹ c=2*(L.n)n-L (normÃ©) (symetrie axiale)
          lumTotSpec=lumTotSpec.plus(lux.getCoul().multiplieIntensite(ks * Math.pow(cosalpha, s)));
         
        }
        }
      //lumTotSpec=CouleurL.noir;
/*      if ((getCouleurIntra(m).getResultante(lumTot)).plus(lumTotSpec) .getIntensite()< 0.2)
        System.out.print("");*/
      return (getCouleurIntra(m).getResultante(lumTot)).plus(lumTotSpec);
    }
    
  @Override
  public CouleurL getColor(Point3 m, R3 d, int ref) {
    return getColor(m,d).plus(refMiroir(m,d,ref));
  }

  @Override
  public CouleurL getColorSimple(Point3 m, R3 d) {
      return getCouleurIntra(m).getResultante(sc.getAmbiant().getCouleurL());
  }
  
  
  
}
