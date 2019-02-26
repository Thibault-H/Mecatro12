package corps;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import algLin.R3;
import objets.Objet;
import objets.ObjetMecatro;
import objets.scene.SceneMecatro;
import optique.CouleurL;
import optique.Source;

public class Mecatro extends GenerateurImage{

	// =============================================
	// Attributs
	// =============================================

	// Propriétés fondamentales et nécessaires

	protected ParametresMecatro param;
	protected SceneMecatro sc;

	// =====================================
	// Constructeur: définit les attributs par défaut
	public Mecatro(double largeur) {
	    sc=new SceneMecatro();
	    param=new ParametresMecatro(largeur);
	    intBlanc=0;
	    imageBase=null;
	  }

/*	public Mecatro(SceneMecatro s) {
	    sc=s;
	    param=new ParametresMecatro(s.);
	    intBlanc=0;
	    imageBase=null;
	  }
*/
	public Mecatro(SceneMecatro s, ParametresMecatro p) {
	    sc=s;
	    param=p;
	    intBlanc=0;
	    imageBase=null;
	  }

	public ParametresMecatro getParam() {
		return param;
	}

	public void setParam(ParametresMecatro par) {
		param = par;
	}

	public SceneMecatro getScene() {
		return sc;
	}

	// =================================================

	public void ajouter(Objet m, Raytracing r) {
		sc.ajouter(m);
		if (m instanceof ObjetMecatro)
			r.ajouter( ((ObjetMecatro)m).getSurfacePhong());
	}
	
	public void toRayt(Raytracing r) {
		for (ObjetMecatro o : sc.listesurfs)
			r.ajouter(o.getSurfacePhong());
	}
	
	
	
	/**
	 * Renvoie le tableau de couleur qui correspond à l'image rendue
	 * 
	 * @return
	 */
	public CouleurL[][] getTab() {
		CouleurL[][] result = new CouleurL[param.getLargpx()][param.getHautpx()];
		AlgoMecatro alg;
		CouleurL lum;
		int l = param.getLargpx();
		int h = param.getHautpx();
		for (int i = 0; i < l; i++) {
			if (i % 10 == 0)
				System.out.printf("%d%% %n", (int) 100 * i / l);
			for (int j = 0; j < h; j++) {
				alg = new AlgoMecatro(this, i, j);
				result[i][j] = lum = alg.getCouleurPixel();
				if (intBlanc < lum.getIntensite())
					intBlanc = lum.getIntensite();
			}
		}
		imageBase = result;
		return result;
	}

	

}
