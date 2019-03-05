package objets.editable;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import algLin.Point3;
import algLin.R3;
import ihm.Fenetre1;
import objetmaths.surfacemaths.CubeMath;
import objets.ObjetRaytracing;
import objets.ihmEditionObjet.EntreeAngle;
import objets.ihmEditionObjet.EntreeCoul;
import objets.ihmEditionObjet.EntreePoint;
import objets.ihmEditionObjet.EntreeScal;
import objets.ihmEditionObjet.EntreeVect;
import objets.objetPhong.CubepasFini;
import objets.objetPhong.Plan;
import objets.objetPhong.Sphere;
import objets.scene.ObjetDansScene;
import optique.SourcePonctuelleIsotrope;

public class ModifierObjet extends Frame{
  
  
  public Button valider;
  public Fenetre1 fen;
  ObjetRaytracing retour;
  
  List<EntreeVect> listeEntree;
  
  
  public ModifierObjet(Fenetre1 f, ObjetDansScene o) {
    super();
    fen=f;
    valider = new Button("Valider");
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent w) {
        setVisible(false);
      }
    });
    
    
    
    //=======================================
    //===%===
    if (o instanceof SourcePonctuelleIsotrope) {
      //Elements
          
      EntreeScal eIntens= new EntreeScal();
      eIntens.setValue(((SourcePonctuelleIsotrope) o).getCouleurL().getIntensite());
      
      
      EntreePoint ePoint= new EntreePoint(false,fen.p);
      ePoint.setAct(((SourcePonctuelleIsotrope) o).getPoint());
      ePoint.setRef(Point3.origine);
      
      
      EntreeCoul eCoul = new EntreeCoul(false,fen.p);
      eCoul.setCouleur(((SourcePonctuelleIsotrope) o).getCouleurL().getColor());
      
      
      
      Label lRayon= new Label("Intensité");
      Label lPoint= new Label("Centre");
      Label lCoul=new Label("Couleur (RGB)");
      
      //Disposition
      setLayout(new GridLayout(4,2));
      add(lRayon);
      add(lPoint);
      add(eIntens);
      add(ePoint);
      add(lCoul);
      add(new Panel());
      add(eCoul);
      add(valider);
      
      
      //Finitions
      valider.addActionListener(new ActionListener() {
  
        @Override
        public void actionPerformed(ActionEvent e) {
          ((SourcePonctuelleIsotrope) o ).reset(ePoint.getAct(),eCoul.getCouleur(),eIntens.getValue());
          fen.afficherImage();
          
        }
      });
      setSize(300,500);
      setVisible(true);
    }
    
    //===%%===
    else if (o instanceof Plan) { 
      //Elements
      
      EntreeVect eNormal= new EntreeVect(false,fen.p);
      eNormal.setValue(((Plan) o).getNorm(Point3.origine));
      eNormal.setJoystick(true);
      
      
      EntreePoint ePoint= new EntreePoint(false,fen.p);
      ePoint.setAct(((Plan)o).getPoint());
      ePoint.setRef(Point3.origine);
      
      
      EntreeCoul eCoul = new EntreeCoul(false,fen.p);
      eCoul.setCouleur(((Plan)o).getCouleurIntra(Point3.origine).getColor());
      
      
      
      Label lNormal= new Label("Normale");
      Label lPoint= new Label("Point particulier");
      Label lCoul=new Label("Couleur (RGB)");
      
      //Disposition
      setLayout(new GridLayout(4,2));
      add(lNormal);
      add(lPoint);
      add(eNormal);
      add(ePoint);
      add(lCoul);
      add(new Panel());
      add(eCoul);
      add(valider);
      
      
      //Finitions
      valider.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          ((Plan) o ).reset(eNormal.getValue(),ePoint.getAct(),eCoul.getCouleur());
          fen.afficherImage();
          
        }
      });
      setSize(300,500);
      setVisible(true);
    }
    
    //===%%%===
    else if (o instanceof Sphere) {
      //Elements
          
      EntreeScal eRayon= new EntreeScal();
      eRayon.setValue(((Sphere) o).getRayon());
      
      
      EntreePoint ePoint= new EntreePoint(false,fen.p);
      ePoint.setAct(((Sphere)o).getCentre());
      ePoint.setRef(Point3.origine);
      
      
      EntreeCoul eCoul = new EntreeCoul(false,fen.p);
      eCoul.setCouleur(((Sphere)o).getCouleurIntra(Point3.origine).getColor());
      
      
      
      Label lRayon= new Label("Rayon");
      Label lPoint= new Label("Centre");
      Label lCoul=new Label("Couleur (RGB)");
      
      //Disposition
      setLayout(new GridLayout(4,2));
      add(lRayon);
      add(lPoint);
      add(eRayon);
      add(ePoint);
      add(lCoul);
      add(new Panel());
      add(eCoul);
      add(valider);
      
      
      //Finitions
      valider.addActionListener(new ActionListener() {
  
        @Override
        public void actionPerformed(ActionEvent e) {
          ((Sphere) o ).reset(eRayon.getValue(),ePoint.getAct(),eCoul.getCouleur());
          fen.afficherImage();
          
        }
      });
      setSize(300,500);
      setVisible(true);
    }
    
    //==%%%%=====
    else if (o instanceof CubepasFini) {
      //Elements
          
      EntreeScal eCote= new EntreeScal();
      eCote.setValue(((CubepasFini) o).getSurf().getCote());
      
      
      EntreePoint ePoint= new EntreePoint(false,fen.p);
      ePoint.setAct(((CubepasFini)o).getSurf().getCentre());
      ePoint.setRef(Point3.origine);
      
      EntreeAngle eAngle= new EntreeAngle(false,fen.p, (CubepasFini) o);       //C'est la donnée des 3 composantes de rotation
      eAngle.setValue(R3.zero);
      eAngle.setJoystick(true);
      
      EntreeCoul eCoul = new EntreeCoul(false,fen.p);
      eCoul.setCouleur(((CubepasFini)o).getCouleurIntra(Point3.origine).getColor());
      
      
      
      Label lCote= new Label("Cote");
      Label lPoint= new Label("Centre");
      Label lAngle= new Label("Angle");
      Label lCoul=new Label("Couleur (RGB)");
      
      //Disposition
      /*setLayout(new GridLayout(4,3));
      add(lCote);
      add(lPoint);
      add(lAngle);
      add(eCote);
      add(ePoint);
      add(eAngle);
      add(lCoul);
      add(new Panel());
      add(new Panel());
      add(eCoul);
      add(valider);*/
      setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      
         
      c.gridx=0;
      c.gridy=0;
      c.gridwidth=3;
      add(lPoint,c);
      
      c.gridx=3;
      c.gridy=0;
      c.gridwidth=2;
      add(lAngle,c);
      
            
      c.gridx=0;
      c.gridy=1;
      c.gridwidth=3;
      c.gridheight=3;
      add(ePoint,c);
      
      c.gridx=3;
      c.gridy=1;
      c.gridwidth=2;
      c.gridheight=3;
      add(eAngle,c);
      
      c.gridx=5;
      c.gridy=0;
      c.gridwidth=2;
      add(lCoul,c);
      
      c.gridx=5;
      c.gridy=1;
      c.gridwidth=2;
      c.gridheight=3;
      add(eCoul,c);
     
      c.gridx=0;
      c.gridy=4;
      add(lCote,c);
     
      c.gridx=2;
      c.gridy=4;
      c.gridwidth=1;
      add(eCote,c);
      
      c.gridx=5;
      c.gridy=4;
      c.gridwidth=2;
      add(valider,c);
      //Finitions
      valider.addActionListener(new ActionListener() {
  
        @Override
        public void actionPerformed(ActionEvent e) {
          ((CubepasFini) o ).reset(ePoint.getAct(),eCote.getValue(),eAngle.getValue(),fen.p.r.getParam().getBase(), eCoul.getCouleur());
          fen.afficherImage();
          
        }
      });
      pack();
      setVisible(true);
    
      
    }
    
    
  }
  
  
  
  
  
  
  
  
  
  
  
  public void setObjet(ObjetRaytracing o) {
    retour=o;
  }
  
  
  public ObjetRaytracing engendrerObjet() {
    return retour;
  }
  
  
  }
