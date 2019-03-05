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
		setName(nom);
		setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder(nom),
						BorderFactory.createEmptyBorder(5,5,5,5)));
		derniereValeurLue = t.valeurDefaut();
	}
	

	
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
	protected void majValeur(Entrable e) {
		derniereValeurLue.conformerA(e);
	}
	
	public abstract void setEditable(boolean RorW);
}
