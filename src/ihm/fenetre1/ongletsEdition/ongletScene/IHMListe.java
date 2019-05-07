package ihm.fenetre1.ongletsEdition.ongletScene;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import objets.scene.Objet;


/**Crée l'objet Java.awt.List utilisé dans l'onglet objet. Passer par cette classe permet de synchroniser les modifications sur l'ihm et dans une liste.
 * 
 * @author Adel
 *
 */
public class IHMListe<O extends Objet>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2724474838656119072L;
	
	private List<O> contenu;
	private Map<String,O> dictionnaireContenu;
	
	private java.awt.List affichage;
	
	/**Modifie la liste de base
	 * 
	 * @param base
	 */
	public IHMListe(List<O> base) {
		super();
		contenu=base;
		affichage = new java.awt.List();
		conformerAuContenu();
	}
	
	/**A priori modifie le tableau
	 * 
	 * @param base
	 */
	public IHMListe(O[] base) {
		this(Arrays.asList(base));
	}
	
	/**Creer l'ihm correspondant au contenu. Appelé à l'instanciation et lorsque contenu est modifié.
	 * 
	 */
	public void conformerAuContenu() {
		creerDico();
		creerAffichage();
	}
	
	public java.awt.List getComponent() {
		return affichage;
	}
	
	//===============================================
	//Créer le dico exhaustif
	
	/**Compte le nombre de fois qu'apparaît un nom dans la liste de départ (contenu).
	 * 
	 * @param nom
	 * @return
	 */
	public int nbOccurences(String nom) {
		int result = 0;
		for (O o : contenu)
			if (o.getNom().compareTo(nom)==0)
				result++;
		return result;
	}
	
	
	
	/**Appelé par créer dico et add. Ajoute o dans le dico avec un nom caractéristique: le sien plus éventuellement un numéro " (i)"
	 * 
	 * @param o
	 */
	private void auxAjoutDansDico(O o) {
		String nom = o.getNom();
		int nbOccr = nbOccurences(nom);
		if (nbOccr==0)
			dictionnaireContenu.put( nom ,o);
		else dictionnaireContenu.put( nom + " ("+nbOccr+")", o );
	}
	
	/**Cree un dico à partir de contenu tel qu'il repréprente une bijection de l'ensemble des clés vers contenu.
	 * 
	 */
	public void creerDico() {
		dictionnaireContenu = new HashMap<String,O>();
		for (O o : contenu) 
			auxAjoutDansDico(o);
	}
	
		
	
	/**Cree la liste affichée à partir du dico
	 * 
	 */
	public void creerAffichage() {
		Iterator<String> itr = dictionnaireContenu.keySet().iterator();
		while (itr.hasNext())
			affichage.add(itr.next());
	}
	
	
	
	//================================================
	//Lire et manipuler la liste sous-jacente sur l'IHM
	
	/**Renvoie le ieme objet affiché dans l'IHM (en commençant par 0)
	 * 
	 * @param i
	 * @return
	 */
	public O get(int i) {
		return dictionnaireContenu.get( affichage.getItem(i));
	}
	
	/**Renvoie l'objet sélectionné dans l'IHM
	 * 
	 * @return
	 */
	public O getSelectedIndex() {
		return get(affichage.getSelectedIndex());
	}
	
	
	
	
	
	
	
	
	
}
