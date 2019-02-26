package optique;

import java.awt.Color;
import algLin.Point3;
import algLin.R3;
import corps.Raytracing;
import objets.ObjetRaytracing;
import objets.TourneAutour;
import objets.scene.SceneRaytracing;

public class SourcePonctuelleIsotrope extends Source implements TourneAutour{

  CouleurL lum;
  Point3 position;
  double rayonIntMax;
  private Photon lux0;
  private Photon lux1 = new Photon(null,null,null);
  
  
  
  public void setScene(SceneRaytracing sce) {
    sc=sce;
    lux0= new Photon(position,lum,sce);
  }
  
  public void setPosition(Point3 p) {
    position=p;
  }
  
  //Constructeurs
  
  public SourcePonctuelleIsotrope(Point3 p, CouleurL l) {
    position=p;
    lum=l;
    rayonIntMax=1;
  }
  
  public SourcePonctuelleIsotrope(Point3 p,double i) {
    position=p;
    lum = new CouleurL(1,1,1,i);
    rayonIntMax=1;
  }

  
  public void reset(Point3 p, CouleurL l) {
    position=p;
    lum=l;
    rayonIntMax=1;
  }
  
  public void reset(Point3 p, Color c, double intensite) {
    position=p;
    lum=new CouleurL(c,intensite);
  }
  
  //Optique
  public CouleurL getCouleurL() {
    return lum;
  }

  
/*  public Photon getInfluence(Point3 p) {
    R3 dir= position.Vecteur(p);
    double dist= dir.norme2();
    dir=dir.normer();
    Photon lux = new Photon(position,lum,sc);
    lux.avancer(dir);
    if (! p.equals(lux.position))
      lux.coul=CouleurL.noir;
    else if (dist> rayonIntMax) {
      lux.coul=lux.coul.multiplieIntensite(rayonIntMax*rayonIntMax/(dist*dist));
  }
    return lux;
      
    }*/
  
  public Photon getInfluence(Point3 p) {
    R3 dir= position.Vecteur(p);
    double dist= dir.norme2();
    dir=dir.normer();
    lux1.reset(lux0);
    lux1.avancer(dir);
    if (! p.equals(lux1.position))
      lux1.coul=CouleurL.noir;
    else if (dist> rayonIntMax) {
      lux1.coul=lux1.coul.multiplieIntensite(rayonIntMax*rayonIntMax/(dist*dist));
  }
    return lux1;
      
    }
  
  
  public double getInfluenceBasique(Point3 p) {
	  return lum.getIntensite()*rayonIntMax*rayonIntMax/p.Vecteur(getPoint()).norme2car();
  }

  @Override
  public Point3 getPoint() {
    return position;
  }
      
  
  public String toString() {
    return String.format("Source :%n{ Point : %s ; %n Lumière: %s}", position.toStringHor(), lum);
  }

  @Override
  public Point3 getPointRef() {    
    return position;
  }

  
  
}
