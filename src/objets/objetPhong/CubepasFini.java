package objets.objetPhong;

import java.awt.Color;

import algLin.M3;
import algLin.Point3;
import algLin.R3;
import objetmaths.surfacemaths.CubeMath2;
import objets.AFaireTourner;
import objets.TourneAutour;
import optique.CouleurS;

public class CubepasFini extends Surface implements TourneAutour, AFaireTourner{

  
  
  public CubepasFini(String name, Point3 p, double cote, M3 b,  Color c) {
	super(name);
    surf=new CubeMath2(p,cote,b);
    listeCouleurs = new CouleurS[] {new CouleurS(c)};
  }
 
  
  public CubepasFini(String name, Point3 p, double cote,  Color c) {
	super(name);
    surf=new CubeMath2(p,cote,M3.id);
    listeCouleurs = new CouleurS[] {new CouleurS(c)};
  }
  
  public CubeMath2 getSurf() {
    return (CubeMath2)surf;
  }

  
  
  public Point3 getPoint() {
    return ((CubeMath2)surf).getCentre();
  }
  
  
  
  public void tourner(R3 axe, double theta) {
    ((CubeMath2) surf).faireTourner(axe, theta);
  }
  
  
  public void reset(Point3 p, double cote, R3 valeurRot, M3 baseRot,  Color c) {
    ((CubeMath2) surf).reset(p,cote,valeurRot,baseRot);
    listeCouleurs = new CouleurS[] {new CouleurS(c)};
  }
  
  
  public String toString() {
    return String.format("Cube : { Cote = %.3g ; Centre = %s", ((CubeMath2)surf).getCote(),((CubeMath2) surf).getCentre() );
  }


  @Override
  public Point3 getPointRef() {
    // TODO Auto-generated method stub
    return ((CubeMath2)surf).getCentre();
  }


  @Override
  public void faireTourner(R3 val) {
    ((CubeMath2)surf).faireTourner(val);
    
  }

  @Override
  public M3 getBaseRef() {
    return ((CubeMath2)surf).getBase();
  }


  @Override
  public void faireTourner(R3 val, M3 base) {
    ((CubeMath2)surf).faireTourner(val,base);
    
  }


  @Override
  public void faireTournerAvecJoystick(int type, double incrrot, M3 base1,
      Point3 oeil) {
    ((CubeMath2)surf).setBase(((CubeMath2)surf).getBase().transformation(type, incrrot, base1, oeil));
    
  }


@Override
public void faireTourner(double val, R3 axe) {
	// TODO Auto-generated method stub
	
}


@Override
public void majListeAttributs() {
	// TODO Auto-generated method stub
	
}


@Override
public void maj() throws ClassCastException {
	// TODO Auto-generated method stub
	
}




}
