package objets;

import java.util.List;

import objets.scene.SceneMecatro;
import optique.Eclairage;
import optique.Source;

public interface MiroirMecatro {
	

	
	/**Renvoie la contribution du miroir à l'éclairage sous la forme de sources virtuelles qui s'ajoutent à la liste entrée.
	 * 
	 * 
	 */
	public void addSourcesVirtuelles(List<Eclairage> listeSourcesVirtuelles, SceneMecatro s) ;

	
	
	
		
}
