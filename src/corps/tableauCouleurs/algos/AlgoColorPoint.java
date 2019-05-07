package corps.tableauCouleurs.algos;

import auxMaths.algLin.O3;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import corps.tableauCouleurs.AlgoColorFiller;
import corps.tableauCouleurs.Parametres;
import optique.lumiere.CouleurL;

/**Implémenter cette interface signifie savoir attribuer à un point une couleur
 * 
 * @author Adel
 *
 */
public abstract class AlgoColorPoint implements AlgoColorFiller {
	
	Point3 focusCamera;		//centre du rectangle
	O3 base;				//base (uz,ux,uy) de l'espace telle que (ux,uz) forme une base "naturelle" du rectangle-image dans l'espace (côtés parallèles à ces vecteurs)
	int largpx, hautpx;		//largeur portée par ux et hauteur portée par uz
	double ratio;			//rapport dimension du cadre virtuel / dimension du rectangle de l'image 
	
	
	AlgoColorPoint(Parametres p, Point3 centreCadre, O3 baseRef){
		largpx = p.getLargpx();
		hautpx = p.getHautpx();
		ratio = p.getRatio();
		focusCamera = centreCadre;
		base = baseRef;
				
	}
	
	
	//=====================================
	//Getters
	@Override
	public int getLargeurPx() {
		return largpx;
	}


	@Override
	public int getHautPx() {
		return hautpx;
	}
	
	
	//=============================================
	//Rendu
	
	/** Renvoie le point du rectangle direct (ux,uz) dans l'espace qui correspond aux coordonnées discretes (abscisse, cote)
	 * Remarque: (abscisse=0,cote=0) <=> coin supérieur gauche dans cette base directe 
	 * @param abscisse
	 * @param cote
	 * @param originePlan
	 * @param base
	 * @return
	 */
	protected Point3 pixToPoint3(int abscisse, int cote) {
		//Vecteur qui relie l'origine du plan au point de coordonnées discrètes données
		R3 PM = base.fois(new R3(largpx/2 - abscisse, hautpx/2 -cote,0).prod(ratio));
		return focusCamera.plus(PM);
	}

	/**Renvoie la couleur que l'algorithme attribue au point p ; on peut le supposer appartenant au rectangle de référence dans l'espace.
	 * 
	 * @param p
	 * @return
	 */
	protected abstract CouleurL getCouleurPoint(Point3 p);
	
	
	@Override
	public CouleurL getCouleurPixel(int i, int j) {
		return getCouleurPoint(pixToPoint3(i,j));
	}




	
	
}
