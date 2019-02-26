package objets.scene;

import java.awt.Color;
import java.io.Serializable;
import java.util.Arrays;
import algLin.R3;
import objets.ObjetRaytracing;
import objets.objetPhong.*;
import optique.Ambiant;
import optique.CouleurL;
import optique.Source;

public class SceneRaytracing extends Scene {

	protected ObjetRaytracing[] listesurfs;
	private static final long serialVersionUID = 1302058706739955574L;
	public Source[] listesources;
	protected Ambiant amb = lumnormale;

	protected Horizon fond = ciel;
	static Ambiant lumnormale = new Ambiant(new CouleurL(1, 1, 1, 10));
	static Horizon ciel = new Horizon(50, Color.cyan, lumnormale);

	// Constructeurs
	public SceneRaytracing() {
		listesurfs = new ObjetRaytracing[] { fond };
		listesources = new Source[0];
		intensiteBlanc = 1;
	}

	public SceneRaytracing(double i) {
		listesurfs = new ObjetRaytracing[] { fond };
		listesources = new Source[0];
		amb = new Ambiant(new CouleurL(1, 1, 1, i));
		intensiteBlanc = i;
	}

	public SceneRaytracing(CouleurL c) {
		listesurfs = new ObjetRaytracing[] { fond };
		listesources = new Source[0];
		amb = new Ambiant(c);
		intensiteBlanc = c.getIntensite();
	}

	//

	public Source[] getSources() {
		return listesources;
	}

	public Ambiant getAmbiant() {
		return amb;
	}

	public ObjetRaytracing[] getSurfs() {
		return listesurfs;
	}
	@Override
	public Horizon getFond() {
		// TODO Auto-generated method stub
		return fond;
	}
	//

	// Modification de la scene

	public void ajouter(ObjetDansScene o) {
		if (o instanceof ObjetRaytracing) {
			ObjetRaytracing[] result = new ObjetRaytracing[listesurfs.length + 1];
			result[listesurfs.length] = (ObjetRaytracing) o;
			System.arraycopy(listesurfs, 0, result, 0, listesurfs.length);
			listesurfs = result;
			o.setScene(this);
		} else if (o instanceof Source) {
			Source[] result = new Source[listesources.length + 1];
			result[listesources.length] = (Source) o;
			System.arraycopy(listesources, 0, result, 0, listesources.length);
			listesources = result;
			o.setScene(this);
		} else
			throw new IllegalArgumentException("Type d'objet non reconnu. Veuillez rentrer une surface ou une source.");
	}

	public void supprimer(ObjetDansScene o) {
		if (o instanceof ObjetRaytracing) {
			ObjetRaytracing[] result = new ObjetRaytracing[listesurfs.length - 1];
			int k = 0;
			for (int i = 0; i < listesurfs.length - 1; i++) {
				if (listesurfs[i] == o)
					k = 1;
				result[i] = listesurfs[i + k];
			}
			listesurfs = result;
			((ObjetRaytracing)o).setScene(this);
		} else if (o instanceof Source) {
			Source[] result = new Source[listesurfs.length - 1];
			int k = 0;
			for (int i = 0; i < listesources.length - 1; i++) {
				if (listesources[i] == o)
					k = 1;
				result[i] = listesources[i + k];
			}
			listesources = result;
			((ObjetRaytracing)o).setScene(this);
		} else
			throw new IllegalArgumentException("Type d'objet non reconnu. Veuillez rentrer une surface ou une source.");

	}



}
