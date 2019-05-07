package objets.objetMecatro;

import java.awt.Color;
import java.util.List;

import auxMaths.algLin.Point3;
import auxMaths.algLin.VectUnitaire;
import auxMaths.transformations.Symetrie;
import auxMaths.transformations.TransformationEspace;
import objetmaths.surfacemaths.RectangleMath;
import objetmaths.volumemaths.DemiEspace;
import objetmaths.volumemaths.VolumePyramide;
import objets.MiroirMecatro;
import objets.objetPhong.Rectangle;
import objets.scene.SceneMecatro;
import optique.Eclairage;
import optique.Source;
import optique.SourcePonctuelleIsotrope;
import optique.sources.Illumination;
import optique.sources.Obstruable;
import optique.sources.illumination.IlluminationPonctuelle;
import optique.sources.obstruction.ObstructionBasiqueRestreinte;

/*Miroir Rectangle avec une unique source ponctuelle en 0
 * 
 */
public class MiroirRectangle extends Rectangle implements MiroirMecatro{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4502307120773927116L;
	

	TransformationEspace sym;		//la transformation qui donne sourceVirtuelle.champLumiere = sourceReelle.champLumiere rond sym 

	public MiroirRectangle(String nom, VectUnitaire n, Point3 centre, double longueur, double largeur) {
		super(nom,n, centre, largeur, longueur, Color.RED);
		sym = (new Symetrie(centre, n, Symetrie.PLANE)).inverse();
		
	}



	@Override
	public void addSourcesVirtuelles(List<Eclairage> listeSourcesVirtuelles, SceneMecatro s) {
		Point3 sourceVirt;
		
		VolumePyramide v1;
		DemiEspace v2;	//le demi-espace défini par le plan du miroir et contenant la source (l'origine)
		Obstruable obs;	//ne pas oublier d'oublier cet objet dans l'algorithme de calcul de voilement
		
		Illumination ill ;
		
			
		
		for (SourcePonctuelleIsotrope e : s.getSources()) {
			sourceVirt = Point3.origine.symetrie(centre, normale);
			
			v1 =new VolumePyramide(sourceVirt, (RectangleMath)surf );
			v2 = new DemiEspace(centre, normale.bonSens(Point3.origine.Vecteur(centre)));	//le demi-espace défini par le plan du miroir et contenant la source (l'origine)
			obs = new ObstructionBasiqueRestreinte(sourceVirt,v1.intersection(v2) , this);	//ne pas oublier d'oublier cet objet dans l'algorithme de calcul de voilement
			
			ill = pt -> e.getIllumination().champLumiere(sym.agirSur(pt));
			
			listeSourcesVirtuelles.add(new Source( "Source virtuelle n°" +listeSourcesVirtuelles.size() ,ill,obs));
		}
			
		
	}
	
	

}


