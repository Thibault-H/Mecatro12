package objets.ihmEditionObjet;

import java.util.Arrays;

import objets.scene.Objet;
import objets.scene.Stageable;
import objets.scene.TypeObjetPasTraiteException;

public class IHMScene {
	IHMListe surfaces;
	IHMListe sources;
	
	Stageable sc;
	
	public IHMScene(Stageable base) {
		sc=base;
		surfaces = new IHMListe(Arrays.asList(sc.getListeObjetsEditables()));
		sources = new IHMListe(Arrays.asList(sc.getSources()));
	}
	
	public java.awt.List getComponentSurfaces(){
		return surfaces.getComponent();
	}
	
	public java.awt.List getComponentSources(){
		return sources.getComponent();
	}
	
	
	
	public void add(Objet o) {
		try {
			sc.ajouter(o);
		} catch (TypeObjetPasTraiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
