package objets.ihmEditionObjet;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import algLin.M3;
import algLin.Point3;
import algLin.R3;
import ihm.Programme;
import objets.AFaireTourner;

public class EntreeAngle extends EntreeVect{

  /**
   * 
   */
  private static final long serialVersionUID = -7151713665018479452L;
  JButton choixRef;
  AFaireTourner obj;
  
  
  public EntreeAngle(boolean b, Programme p, AFaireTourner o) {
    super(b, p);
    obj=o;
    JButton choixRef = new JButton("Ref");
    choixRef.setSize(10,20);
    
    Panel panel= new Panel();
    panel.setLayout(new GridLayout(2,1));
    panel.add(choixRef);
    panel.add(joy);
    add(panel);
    setEditable(false);
  }
  
  public R3 getValue() {
    return super.getValue().prod(Math.PI);
  }
  
  
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() instanceof Button) {
      int action= joy.AFaire((Button) e.getSource());
      obj.faireTournerAvecJoystick(action,  p.incrrot, p.r.getParam().getBase(), Point3.origine);
      new ComponentEvent(this,ComponentEvent.COMPONENT_FIRST);
    }
  }
}
