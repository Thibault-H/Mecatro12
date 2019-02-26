package optique;

import java.awt.Color;
import algLin.Point3;
import algLin.R3;
import objets.scene.ObjetDansScene;

public abstract class Source extends ObjetDansScene{
  
  public abstract CouleurL getCouleurL();
  public abstract Photon getInfluence(Point3 p);
  public abstract Point3 getPoint();
  
  public Color getRGBColor() {
    return getCouleurL().appliquerIntGlobale(sc.getBlanc());
  }
  
  
}
