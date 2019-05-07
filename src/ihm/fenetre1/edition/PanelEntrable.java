package ihm.fenetre1.edition;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ihm.fenetre1.edition.entrees.TypeEntrable;

public abstract class PanelEntrable extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4021324915555580954L;
	private String nom;
	
	protected Entrable derniereValeurLue;
	
	
	
	public PanelEntrable(String nom, TypeEntrable t) {
		super();
		this.nom = new String(nom);
		derniereValeurLue = t.valeurDefaut();
		setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder(nom),
						BorderFactory.createEmptyBorder(5,5,5,5)));
	}
	
	//========================================
	//Simples getters
	

	
	
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
	
	
	
	//================================================
	//Lectures et mises a jour
	
	/**Met à jour derniereValeurLue au vu de l'entrée
	 * 
	 */
	public abstract void lireEntree();
	
	
	/**Lit l'entree (màj de derniereValeurLue) puis la renvoie
	 * 
	 * @return
	 */
	protected Entrable getValeurLue() {
		lireEntree();
		return derniereValeurLue;
	}
	
	/**Permet a toute sous classe d appeler la methode getValeurLue() d une autre sous classe.
	 * 
	 * @param p
	 * @return
	 */
	protected static Entrable getValeurLue(PanelEntrable p) {
		return p.getValeurLue();
	}

	
	
	/**Maj la derniereValeurLue au vu de l'objet donne, sans en altérer le type
	 * 
	 * @param e
	 */
	protected void majValeur(Object e) {
		derniereValeurLue.conformerA(e);
		resetAffichage();
	}

	/**Permet a toute sous-classe d appeler majValeur d une autre sous classe
	 * 
	 * @param p
	 * @param e
	 */
	protected static void majValeur(PanelEntrable p,Object e) {
		p.majValeur(e);
	}
	//==============================================
	//Autres
	
	/**Màj l'affichage au vu de la derniereValeurLue enregistree
	 * 
	 */
	public abstract void resetAffichage() ;
	
	public abstract void setEditable(boolean RorW);
}
