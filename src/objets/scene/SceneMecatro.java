package objets.scene;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import ihm.fenetre1.ongletsEdition.ongletScene.IHMListe;
import ihm.fenetre1.ongletsEdition.ongletScene.TypeObjetEntrable;
import objets.MiroirMecatro;
import objets.objetMecatro.MiroirRectangle;
import optique.Eclairage;
import optique.Source;
import optique.SourcePonctuelleIsotrope;
import optique.lumiere.AssociationLumieres;
import optique.lumiere.CouleurL;

/** La sceneMecatro est une scène de base dans laquelle il n'y a qu'une unique source, placée en l'origine et de couleur donnée.
 * La scène contient une catégorie particulière d'objets : les miroirMecatros 
 * @author Adel
 *
 */
public class SceneMecatro extends SceneSansSources {
	
	private List<MiroirMecatro> listeMiroirs;
	
	private List<Eclairage> listeSourcesVirtuelles;
	
	//protected CouleurL lumSource;
	//private Point3 pointSource;
	
	protected SourcePonctuelleIsotrope source;
	
	public IHMListe<Source> ihmSources;
	
	
	public SceneMecatro(CouleurL lumiere) {
		super();
		listeMiroirs=new ArrayList<MiroirMecatro>();
		listeSourcesVirtuelles=new ArrayList<Eclairage>();
		
		Point3 pointSource = Point3.origine;
		CouleurL lumSource=lumiere;
		
		source = new SourcePonctuelleIsotrope("Source centrale", pointSource, lumSource);
		

		List<Source> listeSources = new ArrayList<Source>();	//liste destinee a ne contenir qu un element
		listeSources.add(source);
		ihmSources  = new IHMListe<Source>(listeSources);
		}
	
	
	public SceneMecatro() {
		this(new CouleurL(Color.WHITE, 10));
		}
	
	
	//=============================================
	//Getters

	@Override
	public SourcePonctuelleIsotrope[] getSources() {
		return new SourcePonctuelleIsotrope[] {source};
	}
	
	
	
	//===========================================
	// Modification de la scene
	
	@Override
	public void ajouter(Objet o) {
		try {
			super.ajouter(o);
		}
		catch(TypeObjetPasTraiteException e) {
			throw new IllegalArgumentException("Erreur dans l'ajout de l'objet : type non reconnu.");
		}
		
		if (o instanceof MiroirMecatro) {
			listeMiroirs.add((MiroirMecatro)o);
			((MiroirMecatro)o).addSourcesVirtuelles(listeSourcesVirtuelles, this);
		}

		ihmSources.conformerAuContenu();
	}
		
	
	
	@Override
	public void supprimer(Objet o) {
		try {
			super.supprimer(o);
		}
		catch(TypeObjetPasTraiteException e) {
			throw new IllegalArgumentException("Erreur dans l'ajout de l'objet : type non reconnu.");
		}
		
		if (o instanceof MiroirMecatro) {
			listeMiroirs.remove((MiroirMecatro)o);
			majSourcesVirtuelles();
		}
		
		ihmSources.conformerAuContenu();

	}
	
	/**Vide la liste des sources virtuelles puis la remplit compte-tenu de la liste des miroirs
	 * 
	 */
	private void majSourcesVirtuelles() {
		listeSourcesVirtuelles = new ArrayList<Eclairage>();
		for (MiroirMecatro m : listeMiroirs)
			m.addSourcesVirtuelles(listeSourcesVirtuelles, this);
	}
	
	
	@Override
	public IHMListe<Source> getIHMSources() {
		return ihmSources;
	}


	@Override
	public TypeObjetEntrable[] getObjetsAjoutables() {
		return new TypeObjetEntrable[] {
				TypeObjetEntrable.Plan,
				TypeObjetEntrable.Sphere
		};
	}
	
	//===============================================
	//Algorithme
	
	/**Renvoie la somme des contributions de chaque miroir par rapport à la source
	 * (Remarque : l'influence directe de la source est eclipsée)
	 * 
	 */
	@Override
	public AssociationLumieres getLumieresEn(Point3 p) {
		AssociationLumieres result = new AssociationLumieres();
		for (Eclairage e : listeSourcesVirtuelles)
			result.add(e.getInfluence(p, this));
		return result;
	}

	public static void main(String[] args) {
		SceneMecatro scene = new SceneMecatro();
		MiroirRectangle mir1 = new MiroirRectangle("Miroir 1" ,R3.ux.opp(), Point3.origine.moins(R3.ux.prod(5)),10,10);
		scene.ajouter(mir1);
		
		Point3 ptVue = Point3.origine.plus(R3.ux);
		System.out.println(scene.getLumieresEn(ptVue));
		
	}

}
