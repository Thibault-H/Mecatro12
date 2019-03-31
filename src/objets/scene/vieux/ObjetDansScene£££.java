package objets.scene.vieux;

import objets.Objet;
import objets.scene.Stageable;

public abstract class ObjetDansScene£££<S extends Scene£££> extends Objet{

	
  protected Stageable sc;
  
  public Stageable getScene() {
	    return sc;
	  }

	public void setScene(Stageable sc) {
		  this.sc=sc;
	}
  
  
  
}
