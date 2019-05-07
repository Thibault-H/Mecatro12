package objets.scene;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import ihm.fenetre1.ongletsEdition.ongletScene.IHMListe;
import ihm.fenetre1.ongletsEdition.ongletScene.TypeObjetEntrable;
import objets.objetPhong.Plan;
import optique.Ambiant;
import optique.Eclairage;
import optique.Source;
import optique.SourcePonctuelleIsotrope;
import optique.lumiere.AssociationLumieres;
import optique.lumiere.CouleurL;
import optique.lumiere.Lumiere;

/**La scène classique pour le raytracing : une scène de base avec des sources a priori multiples.
 * 
 * @author Adel
 *
 */
public class SceneRaytracing extends SceneSansSources {

	public List<Source> listeSources;
	public IHMListe<Source> ihmSources;
	
	// Constructeurs
	public SceneRaytracing() {
		super();
		listeSources = new ArrayList<Source>();
		ihmSources = new IHMListe<Source>(listeSources);
	}

	public SceneRaytracing(double i) {
		this();
		amb = new Ambiant(new CouleurL(1, 1, 1, i));
		intensiteBlanc = i;
	}

	public SceneRaytracing(CouleurL c) {
		this();
		amb = new Ambiant(c);
		intensiteBlanc = c.getIntensite();
	}


	//============================================
	// Getters
	
	@Override
	public Source[] getSources() {
		Source[] result = new Source[listeSources.size()];
		return listeSources.toArray(result);
	}

	//===========================================
	// Modification de la scene
	
	@Override
	public void ajouter(Objet o) {
		try {
			super.ajouter(o);
		}
		catch(TypeObjetPasTraiteException e) {
			switch(e.objetDeLerreur.getTypeObjet()) {
			case Source:
				listeSources.add((Source)o);
				//o.setScene(this);
				break;
			default :
				throw new IllegalArgumentException("Erreur dans l'ajout de l'objet : type non reconnu.");
			}
			ihmSources.conformerAuContenu();
		}
	}
		
	@Override
	public void supprimer(Objet o) {
		try {
			super.supprimer(o);
		}
		catch(TypeObjetPasTraiteException e) {
			switch(e.objetDeLerreur.getTypeObjet()) {
			case Source:
				listeSources.remove(o);
				//o.setScene(this);
				break;
			default :
				throw new IllegalArgumentException("Erreur dans l'ajout de l'objet : type non reconnu.");
			}
			ihmSources.conformerAuContenu();
		}
	}

	@Override
	public TypeObjetEntrable[] getObjetsAjoutables() {
		return new TypeObjetEntrable[] {
				TypeObjetEntrable.Plan,
				TypeObjetEntrable.Sphere
		};
	}

	@Override
	public IHMListe<Source> getIHMSources() {
		return ihmSources;
	}
	



	//=============================================
	//Algorithmes


	@Override
	public AssociationLumieres getLumieresEn(Point3 p) {
		AssociationLumieres result = new AssociationLumieres();
		for (Eclairage src : getSources()) {
			Lumiere l = src.getInfluence(p,this);
			result.add(l);
			//result.add(src.getInfluence(p,this));
		}
		return result;
	}
	

	public static void main(String[] args) {
		SceneRaytracing sc = new SceneRaytracing();
		
		SourcePonctuelleIsotrope s1,s2;
		Point3 ps1 = Point3.origine.plus(new R3(1,0,0));
		s1 = new SourcePonctuelleIsotrope("sr", ps1, new CouleurL(1,0,0,10));
		sc.ajouter(s1);
		
		//Point3 ps2 = Point3.origine.plus(new R3(1,0,0));
		//s2 = new SourcePonctuelleIsotrope("sv", ps2, new CouleurL(0,1,0,10));
		
		VectUnitaire norm = R3.ux;
		Point3 ptPart = Point3.origine.plus(new R3(2,0,0));
		Plan p = new Plan("", norm, ptPart, Color.BLACK);
		sc.ajouter(p);
		
		R3 ptDeVue = R3.ux;
		System.out.println(sc.getLumieresEn(ptPart));
		System.out.println(p.getColor(ptPart, ptDeVue, sc));
	}




}
