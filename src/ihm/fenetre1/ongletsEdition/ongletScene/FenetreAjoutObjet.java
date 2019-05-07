package ihm.fenetre1.ongletsEdition.ongletScene;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

import objets.objetPhong.Surface;
import objets.scene.Objet;
import objets.scene.Stageable;
import optique.Source;

public class FenetreAjoutObjet extends JFrame implements ABouttonOK {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8875280588759408866L;
	
	IHMListe<Surface> typesSurfaces;
	IHMListe<Source> typesSources;
	
	JList<TypeObjetEntrable> listeAjoutables;
    JButton ok;
    Stageable scene;
    
    public Objet objetValide;
	
    /**Une action à faire après appui sur OK. Utilisé pour mettre à jour l'onglet objet après édition de la scène
     * 
     */
    ActionListener aFaireApresOK = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
    	
    };
	
	public FenetreAjoutObjet(Stageable s) {
		super("Ajouter objet");
		
		objetValide = null;
		scene=s;
		
		listeAjoutables = new JList<TypeObjetEntrable>(s.getObjetsAjoutables());
		add(listeAjoutables);
		
		ok = new JButton("OK");
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					objetValide = listeAjoutables.getSelectedValue().valeurDefaut();
					scene.ajouter(objetValide );
					aFaireApresOK.actionPerformed(arg0);
				} catch (Exception e) {
					throw new RuntimeException("Impossible d'ajouter cet objet dans la scène. Cette erreur ne devrait pas pouvoir se produire");
				}
				dispose();
			}
			
		});
		
	}
	
	@Override
	public void setActionPerformedAtValidation(ActionListener a) {
		aFaireApresOK = a;
	}

	@Override
	public ActionListener getActionPostValidation() {
		// TODO Auto-generated method stub
		return aFaireApresOK;
	}
	
	
}
