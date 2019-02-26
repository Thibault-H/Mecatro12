package ihm.fenetre1;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import algLin.Point3;
import corps.ParametresRaytracing;
import ihm.Fenetre1;
import objets.ihmEditionObjet.ChoixPointRef;
import objets.ihmEditionObjet.EntreeCoul;
import objets.ihmEditionObjet.EntreePoint;
import objets.ihmEditionObjet.EntreeVect;
import objets.ihmEditionObjet.Sure;
import objets.objetPhong.Plan;

public class OngletCamera extends Panel implements ActionListener{
  /**
   * 
   */
  private static final long serialVersionUID = 5335365630305783334L;
  Fenetre1 f;
  
  JButton valider;
  JButton choixRef;
  
  public ChoixPointRef fenetreChoix;
  
  Point3 refCamera;
  
  public OngletCamera(Fenetre1 fen) {
    super();
    f=fen;
    refCamera=Point3.origine;
    
    //Reinitialisation camÃ©ra
    JButton resetCam = new JButton("Réinitialiser la caméra");
    resetCam.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        Sure box = new Sure();
        box.donnerReaction(new ActionListener() {

          public void actionPerformed(ActionEvent e) {
            if (e.getSource() == box.ok) {
              f.p.r.setParam(new ParametresRaytracing());
              f.afficherImage();
            }
            box.setVisible(false);
            
          }
          
        });
      }
      
    });
    
    //EntrÃ©e donnÃ©es
    EntreeVect eDirection= new EntreeVect(false,fen.p);
    eDirection.setValue(f.p.r.getParam().getDirection());
    eDirection.setJoystick(true);
    
    
    EntreePoint ePoint= new EntreePoint(false,fen.p);
    ePoint.setAct(refCamera);
    ePoint.setRef(Point3.origine);
    
    //Bouttons
    valider=new JButton("Valider");
    choixRef=new JButton("Référentiel caméra");
    fenetreChoix = new ChoixPointRef(false,f.p);
    
    choixRef.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        JFrame f = new JFrame();
        f.setContentPane(fenetreChoix);
        f.setSize(500,300);
        f.setVisible(true);
        
      }
      
    });
    
    //Etiquettes
    Label lDirection= new Label("Direction");
    Label lPoint= new Label("Point particulier");
    
    
    //Disposition
    setLayout(new GridBagLayout());
    
    GridBagConstraints c = new GridBagConstraints();
    
    c.gridx=0;
    c.gridy=0;
    c.gridwidth=2;
    add(lPoint,c);
    
    c.gridx=2;
    c.gridy=0;
    c.gridwidth=2;
    add(lDirection,c);
    
    c.gridx=4;
    c.gridy=0;
    c.gridwidth=1;
    add(choixRef,c);
    
    c.gridx=0;
    c.gridy=1;
    c.gridwidth=2;
    c.gridheight=3;
    add(ePoint,c);
    
    c.gridx=2;
    c.gridy=2;
    c.gridwidth=2;
    c.gridheight=3;
    add(eDirection,c);
    
    c.gridx=4;
    c.gridy=2;
    c.gridwidth=1;
    c.gridheight=1;
    add(resetCam,c);
    
    c.gridx=4;
    c.gridy=3;
    c.gridwidth=1;
    c.gridheight=1;
    add(valider,c);
    
    
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    
  }
}
