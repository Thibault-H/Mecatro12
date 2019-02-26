package objets.scene;

import java.awt.Color;

import algLin.Point3;
import algLin.R3;
import objets.Objet;
import objets.ObjetMecatro;
import objets.objetPhong.Horizon;
import objets.objetPhong.MiroirPhong;
import optique.Ambiant;
import optique.CouleurL;
import optique.Source;
import optique.SourcePonctuelleIsotrope;

public class SceneMecatro extends Scene {
	
	public ObjetMecatro[] listesurfs;
	protected CouleurL lumSource = new CouleurL(Color.RED, 10);
	public Point3 source = Point3.origine.plus(R3.uy);
	
	public SceneMecatro() {
		super();
		listesurfs=new ObjetMecatro[0];
		}
	
	public SceneMecatro(CouleurL source) {
		super();
		lumSource=source;
		}
	
	public SourcePonctuelleIsotrope getSource() {
		return new SourcePonctuelleIsotrope(source, lumSource);
	}
	
	public void ajouter(Objet o) {
	    if (o instanceof ObjetMecatro) {
	        ObjetMecatro[] result = new ObjetMecatro[listesurfs.length+1];
	        result[listesurfs.length]=(ObjetMecatro) o;
	        System.arraycopy(listesurfs,0,result,0,listesurfs.length);
	        listesurfs=result;
	        ((ObjetMecatro) o).setScene(this);
	      }
	      else
	        throw new IllegalArgumentException("Type d'objet non reconnu. Veuillez rentrer un miroir.");
	    }
	
	public void supprimer(Objet o) {    
	    if (o instanceof ObjetMecatro) {
	      ObjetMecatro[] result = new ObjetMecatro[listesurfs.length-1];
	      int k=0;
	      for (int i=0; i< listesurfs.length-1 ; i++) {
	        if (listesurfs[i] == o)
	          k=1;
	        result[i]= listesurfs[i+k];
	      }
	      listesurfs=result;
	      ((ObjetMecatro) o).setScene(this);
	    }
	    else
	      throw new IllegalArgumentException("Type d'objet non reconnu. Veuillez rentrer un miroir.");
	  
	  }

	@Override
	public Horizon getFond() {
		// TODO Auto-generated method stub
		return null;
	}
}
