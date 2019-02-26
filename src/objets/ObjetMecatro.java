package objets;

import algLin.Point3;
import objets.objetMecatro.VolumeIllumine;
import objets.objetPhong.Surface;
import objets.scene.SceneMecatro;

public abstract class ObjetMecatro extends Objet{
	
	protected VolumeIllumine domaineAction;
	protected Surface forme;
	protected SceneMecatro sc;
	
	/*On supppose que la lumière incidente est toujours ponctuelle et homogène
	 * 
	 */
	public double getIntensiteRecue(Point3 p) {
		return domaineAction.getIntensiteRecue(p);
	}
	
	public abstract Surface getSurfacePhong();

	
	
	public void setScene(SceneMecatro sceneMecatro) {
		sc = sceneMecatro;
	}
	
	public SceneMecatro getScene() {
		return sc;
		
	}
}
