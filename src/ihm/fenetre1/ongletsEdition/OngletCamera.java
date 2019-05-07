package ihm.fenetre1.ongletsEdition;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

import auxMaths.algLin.Point3;
import corps.tableauCouleurs.parametres.ParametresRaytracing;
import ihm.Fenetre1;
import ihm.fenetre1.ongletsEdition.ongletScene.Sure;
import vieux.ihm.ChoixPointRef;
import vieux.ihm.EntreePointOld;
import vieux.ihm.EntreeVectOld;

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

          @Override
		public void actionPerformed(ActionEvent e) {
            if (e.getSource() == box.ok) {
              f.prog.r.setParam(new ParametresRaytracing());
              f.afficherImage();
            }
            box.setVisible(false);
            
          }
          
        });
      }
      
    });
    
    //EntrÃ©e donnÃ©es
    EntreeVectOld eDirection= new EntreeVectOld(false,fen.prog);
    eDirection.setValue(f.prog.r.getParam().getDirection());
    eDirection.setJoystick(true);
    
    
    EntreePointOld ePoint= new EntreePointOld(false,fen.prog);
    ePoint.setAct(refCamera);
    ePoint.setRef(Point3.origine);
    
    //Bouttons
    valider=new JButton("Valider");
    choixRef=new JButton("Référentiel caméra");
    fenetreChoix = new ChoixPointRef(false,f.prog);
    
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
