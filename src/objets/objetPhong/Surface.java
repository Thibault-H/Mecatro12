package objets.objetPhong;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import algLin.Point3;
import algLin.R3;
import objetmaths.surfacemaths.SurfMath;
import objets.ObjetRaytracing;
import objets.TypeObjet;
import objets.editable.Editable;
import objets.editable.Entrable;
import objets.scene.Stageable;
import optique.CouleurL;
import optique.CouleurS;
import optique.Photon;

public abstract class Surface extends ObjetRaytracing implements Editable{
	int dispoCouleurs=0;    //Disposition de la couleur sur la surface
	CouleurS[] listeCouleurs;
	SurfMath surf;


	/**La liste des attributs de la surface. A tout instant en dehors du corps d'une m�thode,
	 * ce dictionnaire est la copie conforme des attributs de l'instance en cours.
	 */
	protected Map<String,Entrable> attributs = new HashMap<String,Entrable>();

	private String nom;

	double kd=0.75;
	double ks=0.5;
	double s=100;


	public Surface(String name) {
		super(name);
	}





	@Override
	public TypeObjet getTypeObjet() {
		return TypeObjet.Surface;
	}



	public SurfMath getSurfMath() {
		return surf;
	}

	//===============================================
	// Methodes d'�dition

	@Override
	public Map<String,Entrable> getAttributsEditables() {
		return new HashMap<String,Entrable>(attributs);
	}



	//====================================================
	//====================== Rendu =======================
	//====================================================

	//Methodes de geometrie

	@Override
	public double dist(Point3 m, R3 d) {    //d est normé
		return surf.dist(m, d);
	}


	/**Renvoie un vecteur normé normal a la surface au point m
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

	public CouleurL refMiroir(Point3 m, R3 d, Stageable s, int k) {
		if (k==0)
			return CouleurL.noir;
		else {
			Photon lux = new Photon(m,s);
			ObjetRaytracing o =lux.avancer(d.symetrieOrth(getNorm(m)));  //Réflexion parfaite
			return o.getColor(lux.getPosition(), lux.getV(), s, k-1);

		}
	}




	@Override
	public CouleurL getColor(Point3 m, R3 d, Stageable scene) {
		CouleurL lumTot = scene.getAmbiant().getCouleurL();
		CouleurL lumTotSpec = CouleurL.noir;
		R3 n = d.bonSens(getNorm(m));
		//Photon lux;
		double costheta;
		double cosalpha;
		for (Photon lux : scene.getLumieresEn(m)) {
			costheta= - n.scal(lux.getV());
			if (costheta>0) {
				lumTot = lumTot.plus(lux.getCoul().multiplieIntensite(kd * costheta));  //terme de reflexion diffuse
				cosalpha=Math.abs(d.scal(n.prod(2*costheta).plus(lux.getV()).normer()));  //cos(alpha)=d.c où c=2*(L.n)n-L (normé) (symetrie axiale)
				lumTotSpec=lumTotSpec.plus(lux.getCoul().multiplieIntensite(ks * Math.pow(cosalpha, s)));

			}
		}
		//lumTotSpec=CouleurL.noir;
		/*      if ((getCouleurIntra(m).getResultante(lumTot)).plus(lumTotSpec) .getIntensite()< 0.2)
        System.out.print("");*/
		return (getCouleurIntra(m).getResultante(lumTot)).plus(lumTotSpec);
	}

	@Override
	public CouleurL getColor(Point3 m, R3 d, Stageable scene, int ref) {
		return getColor(m,d,scene).plus(refMiroir(m,d,scene,ref));
	}

	@Override
	public CouleurL getColorSimple(Point3 m, R3 d, Stageable scene) {
		return getCouleurIntra(m).getResultante(scene.getAmbiant().getCouleurL());
	}



}
