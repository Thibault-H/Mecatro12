package objets;

import objets.scene.SceneMecatro;
import optique.Photon;

public interface MiroirMecatro {
	

	
	/*On supppose que la lumière incidente est toujours ponctuelle et homogène
	 * 
	 */
	public Photon getIntensiteRecue(SceneMecatro s) ;

	
	
		
}
