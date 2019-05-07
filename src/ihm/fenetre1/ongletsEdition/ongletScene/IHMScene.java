package ihm.fenetre1.ongletsEdition.ongletScene;

import javax.swing.JFrame;

import ihm.fenetre1.edition.FenetreEntree;
import objets.objetPhong.Surface;
import objets.scene.Objet;
import objets.scene.SceneRaytracing;
import objets.scene.Stageable;
import optique.Eclairage;
import optique.Source;

public class IHMScene {
	IHMListe<Surface> surfaces;
	IHMListe<Source> sources;
	
	Stageable sc;
	
	public IHMScene(Stageable base) {
		sc=base;
		surfaces = new IHMListe<Surface>(sc.getListeObjetsEditables());
		sources = new IHMListe<Source>(sc.getSources());
	}
	
	public java.awt.List getComponentSurfaces(){
		return surfaces.getComponent();
	}
	
	public java.awt.List getComponentSources(){
		return sources.getComponent();
	}
	
	//=====================================
	//Edition 
	public void add(Objet o) {
		try {
			sc.ajouter(o);
			conformerAuContenu();
		} catch (Exception e) {
			throw new RuntimeException("Impossible d'ajouter cet objet dans la scène. Cette erreur ne devrait pas pouvoir se produire");
		}
	}
	
	public void removeSelectedSurface() {
		try {
			sc.supprimer(surfaces.getSelectedIndex());
			surfaces.conformerAuContenu();
		} catch (Exception e) {
			throw new RuntimeException("Impossible de supprimer cet objet dans la scène. Cette erreur ne devrait pas pouvoir se produire");
		}
	}
	
	public void removeSelectedSource() {
		try {
			sc.supprimer(sources.getSelectedIndex());
			sources.conformerAuContenu();
		} catch (Exception e) {
			throw new RuntimeException("Impossible de supprimer cet objet dans la scène. Cette erreur ne devrait pas pouvoir se produire");
		}
	}
	
	//=============================================
	
	public void modifierSelectedSurface() {
		new FenetreEntree(surfaces.getSelectedIndex());
		surfaces.conformerAuContenu();
	}
	
	public void modifierSelectedSource() {
		new FenetreEntree(sources.getSelectedIndex());
		sources.conformerAuContenu();
	}
	
	public Surface getSelectedSurface() {
		return surfaces.getSelectedIndex();
	}
	
	public Source getSelectedSource() {
		return sources.getSelectedIndex();
	}
	
	//==============================================
	public Surface getSurface(int i) {
		return surfaces.get(i);
	}
	
	public Source getSource(int i) {
		return sources.get(i);
	}
	
	public void conformerAuContenu() {
		surfaces.conformerAuContenu();
		sources.conformerAuContenu();
	}
	
	public static void main(String[] args) {
		SceneRaytracing s= new SceneRaytracing();
		
		JFrame fenetre = new JFrame("Test");
		
		IHMScene test = new IHMScene(s);
		
		fenetre.add(test.getComponentSources());
		fenetre.add(test.getComponentSurfaces());
		
		Objet p = TypeObjetEntrable.Plan.valeurDefaut();
		Objet sphere = TypeObjetEntrable.Sphere.valeurDefaut();
		Objet srce= TypeObjetEntrable.SourcePonctuelle.valeurDefaut();
		
		test.add(p);
		
		fenetre.getContentPane().validate();
		fenetre.repaint();
		fenetre.pack();
		
		fenetre.setVisible(true);
		
		System.out.println(test.sc.getListeObjetsEditables()[0].getNom());
	}
	
}
