package corps.tableauCouleurs.algos;
import auxMaths.algLin.Point3;
import corps.tableauCouleurs.parametres.ParametresRaytracing;
import objets.ObjetRaytracing;
import objets.scene.Stageable;
import optique.Photon;
import optique.lumiere.CouleurL;

public class AlgoSimple extends AlgoRaytracing{
  

  

  public AlgoSimple(ParametresRaytracing param, Stageable s) {
    super(param,s);
  }
  
  
  @Override
  public CouleurL getCouleurPoint(Point3 pnt) {
		dir=oeil.Vecteur(pnt).normer();
		Photon phot= new Photon(pnt, s);
		ObjetRaytracing renc = phot.avancer(dir);
		return renc.getColorSimple(phot.getPosition(), dir, s);
  }

  
  
  
  
  
  
}
