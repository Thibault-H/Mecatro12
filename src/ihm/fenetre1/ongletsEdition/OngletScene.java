	package ihm.fenetre1.ongletsEdition;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;

import corps.GenerateurImageScene;
import ihm.Fenetre1;
import ihm.fenetre1.edition.FenetreEntree;
import ihm.fenetre1.ongletsEdition.ongletScene.FenetreAjoutObjet;
import ihm.fenetre1.ongletsEdition.ongletScene.IHMScene;
import ihm.fenetre1.ongletsEdition.ongletScene.Sure;
import ihm.util.MultiLineLabel;
import objets.scene.Stageable;

public class OngletScene extends Panel implements ItemListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6803651736580960832L;
	Label lObjets, lSources;
	JLabel lObjets2;
	MultiLineLabel lSources2;

	public IHMScene listeObjets;

	public Button ajouter;

	public Button supprimerObj;
	public Button supprimerSou;
	public Button modifierObj;
	public Button modifierSou;


	//Fenetre1 fen;
	Stageable scene;
	//Fenetre1 f;
	GenerateurImageScene gen;

	public OngletScene(GenerateurImageScene fen) {
		super();
		setLayout(new GridBagLayout());
		
		gen=fen;

		listeObjets =new IHMScene(fen.getScene());
		//Boutons
		ajouter=new Button("Ajouter");
		ajouter.addActionListener(this);


		supprimerObj= new Button("Supprimer");
		supprimerObj.addActionListener(this);

		supprimerSou= new Button("Supprimer");
		supprimerSou.addActionListener(this);

		modifierObj= new Button("Modifier");
		modifierObj.addActionListener(this);
		modifierSou= new Button("Modifier");
		modifierSou.addActionListener(this);


		//Etiquettes
		lObjets= new Label("Objets");
		lObjets2= new JLabel(listeObjets.getSurface(0).getNom());

		//Finitions


		GridBagConstraints c = new GridBagConstraints();

		c.gridx=0;
		c.gridy=1;
		add(lObjets,c);

		c.gridx=2;
		c.gridy=1;
		c.gridheight=1;
		add(lObjets2,c);

		c.gridx=0;
		c.gridy=2;
		add(listeObjets.getComponentSurfaces(),c); 

		c.gridx=0;
		c.gridy=5;
		c.insets = new Insets(0,0,50,0);
		add(modifierObj,c);

		c.gridx=0;
		c.gridy=5;
		c.insets = new Insets(0,50,0,0);
		add(supprimerObj,c);


		//Liste des sources


		lSources= new Label("Sources");
		lSources2= new MultiLineLabel(listeObjets.getSource(0).getNom());

		c.gridx=1;
		c.gridy=1;
		add(lSources,c);

		c.gridx=2;
		c.gridy=2;
		c.gridheight=3;
		add(lSources2,c);

		c.gridx=1;
		c.gridy=2;
		add(listeObjets.getComponentSources(),c);




		c.gridx=1;
		c.gridy=5;
		c.insets = new Insets(0,0,50,0);
		add(modifierSou,c);



		c.gridx=3;
		c.gridy=5;
		add(ajouter,c);

		c.gridx=1;
		c.gridy=5;
		c.insets = new Insets(0,50,0,0);
		add(supprimerSou,c);

	}



	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource()==listeObjets.getComponentSurfaces()) {
			lObjets2.setText(listeObjets.getSelectedSurface().getNom());
		}
		else if (e.getSource()==listeObjets.getComponentSources()) {
			lSources2.setText(listeObjets.getSelectedSource().getNom());
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==ajouter) {
			FenetreAjoutObjet newFen = new FenetreAjoutObjet(scene);
			newFen.setActionPerformedAtValidation(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					listeObjets.conformerAuContenu();
					gen.afficherImage();
				}

			});
		}

		else if(e.getSource()==modifierSou)
			new FenetreEntree(listeObjets.getSelectedSource(), new Repainter(gen));

		else if(e.getSource()==modifierObj)
			new FenetreEntree(listeObjets.getSelectedSurface(), new Repainter(gen));

		else if (e.getSource()==supprimerObj)
			new Sure(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					listeObjets.removeSelectedSurface();
					gen.afficherImage();
				}
			});

		else if (e.getSource()==supprimerSou)
			new Sure(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					listeObjets.removeSelectedSource();
					gen.afficherImage();
				}
			});

		gen.afficherImage();

	}



}


class Repainter implements ActionListener {

	GenerateurImageScene gen;

	public Repainter(GenerateurImageScene f) {
		gen =f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gen.afficherImage();

	}

}