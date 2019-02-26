package optique;

import algLin.Point3;

public class Ambiant extends Source {

  CouleurL lum;
  
  public Ambiant(CouleurL l) {
    lum=l;
  }
  
  
  public CouleurL getCouleurL() {
    return lum;
  }


  public Photon getInfluence(Point3 p) {
    return new Photon(p,lum,null);  //null car la lumiere arrive partout dans la scene
  }


  @Override
  public Point3 getPoint() {
    // TODO Auto-generated method stub
    return null;
  }

}
