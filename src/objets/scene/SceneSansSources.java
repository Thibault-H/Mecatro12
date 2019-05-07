package objets.scene;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import auxMaths.PointMobile;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import ihm.fenetre1.ongletsEdition.ongletScene.IHMListe;
import objets.ObjetRaytracing;
import objets.objetPhong.Horizon;
import objets.objetPhong.Plan;
import objets.objetPhong.Surface;
import optique.Ambiant;
import optique.Source;
import optique.lumiere.CouleurL;

/**La scène de base, qui gère les objets classiques de raytracing qui ne sont pas des sources.
 * Une classe fille de SceneSansSources doit implémenter getSources et getLumiereEn
 * @author Adel
 *
 */
public abstract class SceneSansSources implements Stageable {

	protected List<ObjetRaytracing> listeObjets;
	protected List<Surface> listeSurfaces;
	protected Ambiant amb = lumnormale;
	protected double intensiteBlanc;
	protected Horizon fond = ciel;
	
	protected IHMListe<Surface> ihmSurfaces;
	
	static Ambiant lumnormale = new Ambiant(new CouleurL(1, 1, 1, 10));
	static Horizon ciel = new Horizon(50, Color.cyan, lumnormale);

	public SceneSansSources() {
		super();
		listeObjets = new ArrayList<ObjetRaytracing>();
		listeObjets.add(fond);

		listeSurfaces = new ArrayList<Surface>();
		ihmSurfaces = new IHMListe<Surface>(listeSurfaces);
		
		intensiteBlanc = 1;
	}
	
	
	//======================================
	//Getters& Setters


	@Override
	public abstract Source[] getSources() ;

	
	
	@Override
	public Ambiant getAmbiant() {
		return amb;
	}

	@Override
	public ObjetRaytracing[] getListeObjets() {
		ObjetRaytracing[] result = new ObjetRaytracing[listeObjets.size()];
		return listeObjets.toArray(result);
	}

	@Override
	public Surface[] getListeObjetsEditables() {
		Surface[] result = new Surface[listeSurfaces.size()];
		return listeSurfaces.toArray(result);
	}

	@Override
	public Horizon getFond() {
		// TODO Auto-generated method stub
		return fond;
	}
	
	//===========================================
	// Modification de la scene


	@Override
	public void ajouter(Objet o) throws TypeObjetPasTraiteException{
		switch(o.getTypeObjet()) {
			case Surface:
				listeSurfaces.add((Surface)o);
				//o.setScene(this);
			case Horizon:	//case surface OU case horizon
				listeObjets.add((ObjetRaytracing)o);
				//o.setScene(this);
				break;
		
			default:
				throw new TypeObjetPasTraiteException(o);
			}
		ihmSurfaces.conformerAuContenu();
	}

	@Override
	public void supprimer(Objet o) throws TypeObjetPasTraiteException{
		switch(o.getTypeObjet()) {
			case Surface:
				listeSurfaces.remove(o);
			case Horizon:	//case surface OU case horizon
				listeObjets.remove(o);
				break;
	
			
			default:
				throw new IllegalArgumentException("Type d'objet non reconnu. Veuillez rentrer une surface ou une source.");
			}
		ihmSurfaces.conformerAuContenu();
	}
	
	@Override
	public IHMListe getIHMSurfaces() {
		return ihmSurfaces;
	}

	
//===================================
//Algorithmes
	
	
	@Override
	public ObjetRaytracing avancerJusquauChoc(PointMobile m, VectUnitaire dir,ObjetRaytracing... aIgnorer) {
		double distance= Double.MAX_VALUE;
		double inter;
		
		List<ObjetRaytracing> listeObjets = new ArrayList<ObjetRaytracing> (Arrays.asList( getListeObjets() ));
		listeObjets.removeAll(Arrays.asList(aIgnorer));
		
		ObjetRaytracing result=getFond();
		for (ObjetRaytracing o : listeObjets) {
			if ((inter =o.dist(m,dir)) < distance) {
				distance = inter;
				result=o;
			}
		}
		m.deplacerDe(R3.prod(distance, dir));
		return result;
	}

	@Override
	public double getBlanc() {
		// TODO Auto-generated method stub
		return intensiteBlanc;
	}
	
	public static void main(String[] args) {
		Point3 source = Point3.origine;
		Point3 pt = Point3.origine.plus(new R3(5,0,0));
		
		SceneRaytracing sc = new SceneRaytracing();
		
		VectUnitaire norm = R3.ux;
		Point3 ptPart = Point3.origine.plus(new R3(2,0,0));
		Plan p = new Plan("", norm, ptPart, Color.BLACK);
		sc.ajouter(p);
		
		PointMobile photon = new PointMobile(source);
		
		System.out.println(sc.avancerJusquauChoc(photon, source.Vecteur(ptPart)));
		
		
		
	}

}