package objets.ihmEditionObjet.vieux;

import java.awt.Button;
import java.awt.FlowLayout;

import java.awt.Panel;
import java.awt.event.ActionListener;

public class DoubleJoystick extends Panel{
  /**
   * 
   */
  private static final long serialVersionUID = 5872246513808695845L;
  ActionListener a;
  Joystick trans;
  Joystick rot;
  
  public DoubleJoystick(ActionListener ac) {
    new Panel();
    a=ac;
    trans=new Joystick(a,"Position");
    rot=new Joystick(a,"Angle");
    setLayout(new FlowLayout());
    add(trans);
    add(rot);
    
  }
  
  @Override
public void setEnabled(boolean bool) {
    
  }
  
  public int AFaire(Button b) {
    if (b==trans.fleches[0])
      return 1;
    else if (b==trans.fleches[1])
      return 2;
    else if (b==trans.fleches[2])
      return 3;
    else if (b==trans.fleches[3])
      return 4;
    else if (b==trans.fleches[4])
      return 5;
    else if (b==trans.fleches[5])
      return 6;
    else if (b==rot.fleches[0])
      return -1;
    else if (b==rot.fleches[1])
      return -2;
    else if (b==rot.fleches[2])
      return -3;
    else if (b==rot.fleches[3])
      return -4;
    else if (b==rot.fleches[4])
      return -5;
    else if (b==rot.fleches[5])
      return -6;
    else return Integer.MAX_VALUE;
  }
  

}
