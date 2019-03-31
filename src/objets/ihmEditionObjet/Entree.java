package objets.ihmEditionObjet;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import objets.editable.Entrable;
import objets.editable.TypeEntrable;

public abstract class Entree extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4021324915555580954L;
	private String nom;
	
	protected Entrable derniereValeurLue;
	
	
	
	public Entree(String nom, TypeEntrable t) {
		super();
		this.nom = new String(nom);
		derniereValeurLue = t.valeurDefaut();
		setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder(nom),
						BorderFactory.createEmptyBorder(5,5,5,5)));
	}
	
	/**Lit l'entree (màj de derniereValeurLue) puis la renvoie
	 * 
	 * @return
	 */
	protected Entrable getValeurLue() {
		lireEntree();
		return derniereValeurLue;
	}
	
	
	/**Renvoie le nombre de colonnes occupées par le JPanel (GridBagLayout)
	 * 
	 * @return
	 */
	public abstract int getNombreCol();
	/**Renvoie le nombre de lignes occupées par le JPanel (GridBagLayout)
	 * 
	 * @return
	 */
	public abstract int getNombreLig();
	
	public String getNom() {
		return new String(nom);
	}
	
	/**Met à jour derniereValeurLue au vu de l'entrée
	 * 
	 */
	public abstract void lireEntree();
	
	/**Maj la derniereValeurLue, sans en altérer le type
	 * 
	 * @param e
	 */
	protected void majValeur(Object e) {
		derniereValeurLue.conformerA(e);
		resetAffichage();
	}
	
	/**Màj l'affichage au vu de la derniereValeurLue enregistree
	 * 
	 */
	public abstract void resetAffichage() ;
	
	public abstract void setEditable(boolean RorW);
}
