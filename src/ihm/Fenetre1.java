package ihm;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;
import algLin.Point3;
import corps.ParametresRaytracing;
import corps.Raytracing;
import ihm.fenetre1.OngParam;
import ihm.fenetre1.OngletCamera;
import ihm.fenetre1.OngletObjet;
import ihm.fenetreImage.ImageScrollPane;
import ihm.util.EcouteurPourFermetureFenetre;
import ihm.util.MenuSauverOuvrir;
import objets.ihmEditionObjet.DoubleJoystick;
import objets.ihmEditionObjet.Sure;
import objets.objetPhong.CubepasFini;
import objets.objetPhong.Plan;
import objets.objetPhong.Sphere;

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
    
    //Onglet scène
    
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

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == joyPrinc){
      int action= joyPrinc.AFaire((Button) e.getSource());
      System.out.print(action);
      p.r.getParam().setOeil(p.r.getParam().getOeil().transformation(action, p.incrtrans, p.incrrot, p.r.getParam().getBase(), p.r.getParam().getOeil()));
      p.r.getParam().setDirection(p.r.getParam().getDirection().transformation(action, p.incrrot, p.r.getParam().getBase(), p.r.getParam().getOeil()));
      afficherImage();
      repaint();
    }
    else if (e.getSource() == onglet1.ok) {
      try {
        if (onglet1.largOuHaut.getState()==true) {//On édite la hauteur
          p.r.getParam().aggrandirHJusque(onglet1.getHaut());
          onglet1.larg.setText(String.valueOf(p.r.getParam().getLargpx()));        
        }
        else {//On édite la largeur
          p.r.getParam().aggrandirLJusque(onglet1.getLarg());
          onglet1.haut.setText(String.valueOf(p.r.getParam().getHautpx()));
        }
        
        p.formatImage=onglet1.getFormat();
        
      } catch (NumberFormatException ex) {
        System.out.println("Entrée non reconnue. Veuillez entrer une longueur en nombre entier de pixels");
      }
    }
    else if (e.getSource() == onglet3.ajouter) {
      ParametresRaytracing par = p.r.getParam();
      Point3 pointDevant= par.getOeil().plus(par.getDirection().prod(5+par.getEcart()));
      switch(onglet3.listeNouv.getSelectedItem()) {
      case "Plan":
        Plan pl =new Plan(par.getDirection(),pointDevant , Color.black);
        p.r.ajouter(pl);
        onglet3.AjouterSurf(pl);
        afficherImage();
        new ModifierObjet(this, p.r.getScene().getSurfs()[p.r.getScene().getSurfs().length - 1]);     
        break;
      case "Sphere":
        Sphere sp = new Sphere(pointDevant, 2, Color.black);
        p.r.ajouter(sp);
        onglet3.AjouterSurf(sp);
        afficherImage();
        new ModifierObjet(this, p.r.getScene().getSurfs()[p.r.getScene().getSurfs().length - 1]);     
        break;
      case "Cube":
        CubepasFini cu = new CubepasFini(pointDevant, 2, Color.black);
        p.r.ajouter(cu);
        onglet3.AjouterSurf(cu);
        afficherImage();
        new ModifierObjet(this, p.r.getScene().getSurfs()[p.r.getScene().getSurfs().length - 1]);     
        break;
      }
    }
    else if (e.getSource() == onglet3.modifierObj) {
      new ModifierObjet(this, p.r.getScene().getSurfs()[onglet3.listeObjets.getSelectedIndex()]);
    }
    else if (e.getSource() == onglet3.modifierSou) {
      new ModifierObjet(this, p.r.getScene().getSources()[onglet3.listeSources.getSelectedIndex()]);
    }
    else if (e.getSource() == onglet3.supprimerObj) {
      p.r.supprimer(p.r.getScene().getSurfs()[onglet3.listeObjets.getSelectedIndex()]);
      onglet3.SupprimerSurf(onglet3.listeObjets.getSelectedIndex());
      afficherImage();
    }
    else if (e.getSource() == onglet3.supprimerSou) {
      p.r.supprimer(p.r.getScene().getSources()[onglet3.listeSources.getSelectedIndex()]);
      onglet3.SupprimerSource(onglet3.listeSources.getSelectedIndex());
      afficherImage();
    }
  }
  
  
  public void afficherImage() {
    zoneImage.affecterImage(p.r.mainProgramSimple());
  }


}
