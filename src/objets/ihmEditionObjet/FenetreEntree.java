package objets.ihmEditionObjet;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import algLin.Point3;
import algLin.R3;
import objets.editable.Editable;
import objets.editable.Entrable;
import objets.editable.TypeEntrable;
import objets.objetPhong.Rectangle;

public class FenetreEntree extends JFrame{
	Map<TypeEntrable,List<Entree>> contenu;
	JPanel contenant;

	Editable objAModifier;
	JButton valider;


	private FenetreEntree(String nomFenetre) {
		super(nomFenetre);
	}

	public FenetreEntree(Editable obj) {
		super("Edition de "+obj.getNom());

		Map<TypeEntrable, List<String>> listeContenu = obj.getTypeAttributsEditables();

		//Si l'objet n'est finalement pas modifiable, sa liste d'attributs modifiables est vide.
		if(listeContenu.isEmpty()) 
			JOptionPane.showMessageDialog(this, obj.getNom() + " n'est pas modifiable!");
		
		//Sinon on affiche la fenêtre d'édition
		else {	
			objAModifier = obj;
			setLayout(new FlowLayout());
			setLocationRelativeTo(null);
			setResizable(false);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

			construireContenu(listeContenu);
			majContenu();

			majFenetre();
			add(contenant);

			valider = new JButton("Valider");
			valider.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					objAModifier.reconstruireAvecAttributs(construireListeAttributs());


				}
			});
			add(valider);

			pack();
			setVisible(true);
		}
	}


	//Modification de contenu

	/**Construit le dictionnaire des entrées à partir de la liste des entrées classées par type.
	 * 
	 * @param m
	 */
	private void construireContenu(Map<TypeEntrable, List<String>> m) {
		contenu = new HashMap<TypeEntrable, List<Entree>>();
		Iterator<TypeEntrable> itr = m.keySet().iterator();
		Iterator<String> itr2 ;

		TypeEntrable typeEnCours;
		while (itr.hasNext()) {
			typeEnCours = itr.next();
			contenu.put(typeEnCours, new ArrayList<Entree>()); //initialisation de la liste 

			itr2 = m.get(typeEnCours).iterator();	//on va remplir la liste de contenu[typeEnCours]
			while(itr2.hasNext())
				contenu.get(typeEnCours).add(typeEnCours.entreeDefaut(itr2.next())); //on ajoute à la liste l'entrée défaut correspondant au type en cours et dont le nom est donné dans la liste 	
		}		
	}


	/**Conforme une entrée de contenu à une valeur donnée
	 * 
	 */
	private void majEntree(String nom, Object newValeur) {
		Iterator<TypeEntrable> itr = contenu.keySet().iterator();
		Iterator<Entree> itr2;
		boolean continuer = true;

		Entree tmp;
		while (itr.hasNext() &&continuer) {
			itr2 = contenu.get(itr.next()).iterator();
			while (itr2.hasNext() && continuer) {
				tmp =itr2.next();
				if (tmp.getNom().compareTo(nom)==0) {
					continuer= false;
					tmp.majValeur(newValeur);
				}
			}
		}

	}

	/**Conforme contenu à la valeur des attributs actuels de objAModifier.
	 * (Remarque : ne crée pas de nouvelles entrées dans contenu) 
	 */
	private void majContenu() {
		Map<String,Entrable> m = objAModifier.getAttributsEditables();
		Iterator<String> itr = m.keySet().iterator();
		String nomEnCours;
		while (itr.hasNext()) {
			nomEnCours=itr.next();
			majEntree(nomEnCours, m.get(nomEnCours));
		}
	}

	//Mise à jour de l'affichage

	/**Conforme la fenêtre au contenu
	 *  
	 */
	private void majFenetre() {
		contenant = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 50;

		int nbColonnesMax = 0;

		int numeroColonneAct=0;
		int numeroLigneAct=0;

		Iterator<TypeEntrable> itr = contenu.keySet().iterator();
		Iterator<Entree> itr2;

		TypeEntrable typeEnCours;
		Entree entreeEnCours;
		while (itr.hasNext()) {
			typeEnCours = itr.next();


			c.gridwidth = typeEnCours.entreeDefaut("").getNombreCol();
			c.gridheight = typeEnCours.entreeDefaut("").getNombreLig();
			itr2 = contenu.get(typeEnCours).iterator();
			while (itr2.hasNext()) {
				entreeEnCours = itr2.next();
				c.gridx = numeroColonneAct;
				c.gridy = numeroLigneAct;
				contenant.add(entreeEnCours, c);
				numeroColonneAct += c.gridwidth;
			}
			numeroLigneAct += c.gridheight;
			if (nbColonnesMax<numeroColonneAct +1)
				nbColonnesMax=numeroColonneAct+1;

			numeroColonneAct = 0;


		}

		contenant.validate();
		contenant.repaint();
	}

	//Mise à jour de l'objet

	/**Renvoie les entrées de contenu organisées sous la forme d'un dictionnaire d'entrables 
	 * 
	 * @return
	 */
	private Map<String, Entrable> construireListeAttributs(){
		Map<String, Entrable> result = new HashMap<String, Entrable>();
		Iterator<TypeEntrable> itr1 = contenu.keySet().iterator();
		Iterator<Entree> itr2;

		TypeEntrable typeEnCours;
		Entree entreeEnCours;
		while (itr1.hasNext()) {
			typeEnCours = itr1.next();
			itr2 = contenu.get(typeEnCours).iterator();
			while (itr2.hasNext()) {
				entreeEnCours = itr2.next();
				result.put(entreeEnCours.getNom(), entreeEnCours.getValeurLue());
			}
		}
		return result;
	}



	public static void main(String[] args) {
		Rectangle s = new Rectangle("rect1", R3.ux, Point3.origine, 2, 3, Color.BLACK);
		FenetreEntree fen = new FenetreEntree(s);
	}
}
