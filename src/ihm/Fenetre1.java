package ihm;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import corps.TypeImage;
import ihm.fenetre1.commandesSup.DoubleJoystick;
import ihm.fenetre1.ongletsEdition.OngletParam;
import ihm.fenetre1.ongletsEdition.OngletCamera;
import ihm.fenetre1.ongletsEdition.OngletScene;
import ihm.fenetreImage.ImageScrollPane;
import ihm.util.EcouteurPourFermetureFenetre;
import ihm.util.MenuSauverOuvrir;
import objets.scene.Stageable;

public class Fenetre1 extends Frame implements ActionListener{

  
	Programmable pr;
	
  Button commencer;
 ImageScrollPane zoneImage;
  MenuBar mb1;
  MenuItem menuCommencer;
  

  
  public Fenetre1(Programmable pr){
    super();
    this.pr=pr;
    
    addWindowListener(new EcouteurPourFermetureFenetre());
    setLayout(new BorderLayout());
    Panel pan = new Panel();
    pan.setLayout(new BorderLayout());
    
    //Bouton Commencer
    commencer = new Button("Commencer");
    commencer.addActionListener(this);
    add(commencer, BorderLayout.SOUTH);
    
    //Menu
    mb1 = new MenuBar();
    setMenuBar(mb1);
    
    Menu menuFichier1=new Menu("Fichier");
    MenuSauverOuvrir sauvOuv = new MenuSauverOuvrir();
    mb1.add(menuFichier1);
    
    menuCommencer= new MenuItem("Commencer");
    menuCommencer.addActionListener(this);
    
    MenuItem quitter = new MenuItem("Quitter");
    quitter.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        System.exit(0);  // Sortie du programme  avec code d'erreur 0 (pas d'erreur)
        }
      
    });

    menuFichier1.add(menuCommencer);
    sauvOuv.ajouterA(menuFichier1);
    menuFichier1.add(quitter);
    
    //Zone Image
    zoneImage = new ImageScrollPane();
    add(zoneImage, BorderLayout.WEST);
    afficherImage();
    
    
    //Commandes supp
    //joyPrinc=new DoubleJoystick(p);
    pan.add(pr.commandesSupp(), BorderLayout.SOUTH);
    
    
    //Onglets 
    pan.add(pr.ongletsEdition(),BorderLayout.CENTER);
    
    //Finitions
    add(pan,BorderLayout.CENTER);
    
    
    repaint();
    pack();
    setVisible(true);
    
  }
  
/*  public Fenetre1(Programme p, Stageable s) {
	  this(p);
	  //Onglet scène

	  onglet3=new OngletScene(this,s);

	  listOng.add(onglet3, "Scene");
	  
	  repaint();

  }
  
*/

  
  
  public void afficherImage() {
    zoneImage.affecterImage(pr.imageEdition());
  }

@Override
public void actionPerformed(ActionEvent e) {
	if (e.getSource()==commencer || e.getSource()==menuCommencer)
		new Fenetre2(pr);
}


}
