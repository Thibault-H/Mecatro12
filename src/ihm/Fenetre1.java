package ihm;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import algLin.Point3;
import corps.ParametresRaytracing;
import ihm.fenetre1.OngParam;
import ihm.fenetre1.OngletCamera;
import ihm.fenetre1.OngletObjet;
import ihm.fenetreImage.ImageScrollPane;
import ihm.util.EcouteurPourFermetureFenetre;
import ihm.util.MenuSauverOuvrir;
import objets.ihmEditionObjet.FenetreEntree;
import objets.ihmEditionObjet.vieux.DoubleJoystick;
import objets.objetPhong.CubepasFini;
import objets.objetPhong.Plan;
import objets.objetPhong.Sphere;
import objets.scene.Objet;

public class Fenetre1 extends Frame implements ActionListener{

  public Programme p;
  
  Button commencer;
  ImageScrollPane zoneImage;
  DoubleJoystick joyPrinc;
  JTabbedPane listOng;
  OngParam onglet1;
  OngletCamera onglet2;
  OngletObjet onglet3;
  MenuBar mb1;
  MenuItem menuCommencer;
  
  public Fenetre1(Programme p){
    super();
    this.p=p;
    addWindowListener(new EcouteurPourFermetureFenetre());
    setLayout(new BorderLayout());
    Panel pan = new Panel();
    pan.setLayout(new BorderLayout());
    
    //Bouton Commencer
    commencer = new Button("Commencer");
    commencer.addActionListener(p);
    add(commencer, BorderLayout.SOUTH);
    
    //Menu
    mb1 = new MenuBar();
    setMenuBar(mb1);
    
    Menu menuFichier1=new Menu("Fichier");
    MenuSauverOuvrir sauvOuv = new MenuSauverOuvrir(p,1);
    mb1.add(menuFichier1);
    
    menuCommencer= new MenuItem("Commencer");
    menuCommencer.addActionListener(p);
    
    MenuItem quitter = new MenuItem("Quitter");
    quitter.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        System.exit(0);  // Sortie du programme  avec code d'erreur 0 (pas d'erreur)
        }
      
    });

    menuFichier1.add(menuCommencer);
    menuFichier1.add(sauvOuv.sauver);
    menuFichier1.add(sauvOuv.ouvrir);
    menuFichier1.add(quitter);
    
    //Zone Image
    zoneImage = new ImageScrollPane();
    add(zoneImage, BorderLayout.WEST);
    afficherImage();
    
    
    //Joystick
    joyPrinc=new DoubleJoystick(p);
    pan.add(joyPrinc, BorderLayout.SOUTH);
    
    
    //===================================
    //Onglets
    //===================================
    listOng=new JTabbedPane();
    
    //Onglet parametres de l'image
    
    onglet1=new OngParam(this);
    
    
    
    listOng.add(onglet1,"Image");
    
    //Onglet parametres camera
    
    onglet2=new OngletCamera(this);

    
    
    listOng.add(onglet2, "Camera (pas encore fonctionnel)");
    
    //Onglet scÃ¨ne
    
    onglet3=new OngletObjet(this);
        
    listOng.add(onglet3, "Scene");
    
    //===================================
    pan.add(listOng,BorderLayout.CENTER);
    
    //Finitions
    add(pan,BorderLayout.CENTER);
    
    
    
    
    
    repaint();
    pack();
    setVisible(true);
    
  }
  
  public Objet trouverSurfaceSelectionnee() {
	  
  }

  @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == onglet1.ok) 
    	p.agir(TypeAction.LireOnglet1, onglet1.isHauteurEditee.getState());
    
    else if (e.getSource() == onglet3.ajouter) {
      ParametresRaytracing par = p.r.getParam();
      Point3 pointDevant= par.getOeil().plus(par.getDirection().prod(5+par.getEcart()));
      switch(onglet3.listeNouv.getSelectedItem()) {
      case "Plan":
        Plan pl =new Plan("Plan 1", par.getDirection(),pointDevant , Color.black);
        p.r.ajouter(pl);
        onglet3.AjouterSurf(pl);
        afficherImage();
        new ModifierObjet(this, p.r.getScene().getListeObjets()[p.r.getScene().getListeObjets().length - 1]);     
        break;
      case "Sphere":
        Sphere sp = new Sphere("Sphere 1",pointDevant, 2, Color.black);
        p.r.ajouter(sp);
        onglet3.AjouterSurf(sp);
        afficherImage();
        new ModifierObjet(this, p.r.getScene().getListeObjets()[p.r.getScene().getListeObjets().length - 1]);     
        break;
      case "Cube":
        CubepasFini cu = new CubepasFini("Cube 1 ",pointDevant, 2, Color.black);
        p.r.ajouter(cu);
        onglet3.AjouterSurf(cu);
        afficherImage();
        new ModifierObjet(this, p.r.getScene().getListeObjets()[p.r.getScene().getListeObjets().length - 1]);     
        break;
      }
    }
    else if (e.getSource() == onglet3.modifierObj ) 
    	p.agir(TypeAction.ModifierObjet, 
    			p.agir(TypeAction.TrouverSurface, onglet3.listeObjets.getSelectedIndex())); //renvoie l'indice de l'objet sélectionné
   
    else if (e.getSource() == onglet3.modifierSou) 
    	p.agir(TypeAction.AjouterSource, onglet3.listeSources.getSelectedIndex()); //renvoie l'indice de la source sélectionné
    
    else if (e.getSource() == onglet3.supprimerObj) {
    	p.agir(TypeAction.SupprimerObjet, onglet3.listeObjets.getSelectedIndex()); //renvoie l'indice de l'objet sélectionné
      p.r.supprimer(p.r.getScene().getListeObjets()[onglet3.listeObjets.getSelectedIndex()]);
      onglet3.SupprimerSurf(onglet3.listeObjets.getSelectedIndex());
      afficherImage();
    }
    else if (e.getSource() == onglet3.supprimerSou) {
    	p.agir(TypeAction.SupprimerSource, onglet3.listeObjets.getSelectedIndex()); //renvoie l'indice de l'objet sélectionné
      p.r.supprimer(p.r.getScene().getSources()[onglet3.listeSources.getSelectedIndex()]);
      onglet3.SupprimerSource(onglet3.listeSources.getSelectedIndex());
      afficherImage();
    }
  }
  
  
  public void afficherImage() {
    zoneImage.affecterImage(p.r.mainProgramSimple());
  }


}
