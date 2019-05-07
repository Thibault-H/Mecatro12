package ihm.fenetre1.ongletsEdition.ongletScene;

import java.awt.event.ActionListener;

/**Une interface pour inciter à suivre une certaine implémentation du motif "agir après validation"
 * Un attribut ActionListener, dont l'actionPerformed est vide par défaut, est éventuellement modifié.
 * La méthode actionPerformed de cet attribut sera appelée dans chaque bouton "valider" à la fin du corps de son actionPerformed.
 * 
 * @author Adel
 *
 */
public interface ABouttonOK {
	
	
	ActionListener getActionPostValidation();
	
	public void setActionPerformedAtValidation(ActionListener a);
}
