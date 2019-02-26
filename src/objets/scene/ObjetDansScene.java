package objets.scene;

import java.io.Serializable;
import algLin.R3;
import objets.Objet;

public abstract class ObjetDansScene<S extends Scene> extends Objet{

	
  protected SceneRaytracing sc;
  
  public SceneRaytracing getScene() {
	    return sc;
	  }

	public void setScene(SceneRaytracing sc) {
		  this.sc=sc;
	}
  
  
  
}
