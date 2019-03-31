package objets.ihmEditionObjet.vieux;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import algLin.Point3;
import ihm.Programme;

public class EntreePointOld extends EntreeVectOld{

  /**
   * 
   */
  private static final long serialVersionUID = 6353095686415771963L;
  protected Point3 act;
  Point3 ref;
  protected DoubleJoystick dJoy;
  JButton modifierRef;
  
  public EntreePointOld(boolean b, Programme p) {
    super(b, p);
    ref=act=Point3.origine;
    Panel pan=new Panel();
    dJoy= new DoubleJoystick(this);

    modifierRef=new JButton(ref.toString());
    modifierRef.addActionListener(this);
    

    pan.setLayout(new GridLayout(2,1));
    pan.add(modifierRef);
    pan.add(dJoy);    
    add(pan);
    
  }
  
  
  public Point3 getAct() {
    return act;
  }
  public void setAct(Point3 p) {
    act=p;
    setValue(ref.Vecteur(p));
  }
  
  
  
  public void setRef(Point3 p) {
    setValue(p.Vecteur(ref).plus(getValue()));
    ref=p;
    modifierRef.setText(ref.toString());
  }
  
  public Point3 getRef() {
    return ref;
  }
  
  
  @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource()==modifierRef) {
      Frame f=new Frame("Editer le point de référence");
      f.add(new ChoixPointRef(b,p,this,getRef()));
      f.addWindowListener(new WindowAdapter() {
        @Override
		public void windowClosing(WindowEvent w) {
          f.setVisible(false);          
        }
      });
      f.setSize(500,300);
      f.setVisible(true);
    }
    if (e.getSource() instanceof ChoixPointRef) {
      setRef(((ChoixPointRef) e.getSource()).getAct());
    }
    else if (e.getSource() instanceof Button) {
      int action= dJoy.AFaire((Button) e.getSource());
      setAct(act.transformation(action, p.incrtrans, p.incrrot, p.r.getParam().getBase(), ref));
      new ComponentEvent(this,ComponentEvent.COMPONENT_FIRST);
    
  }
  }
  
  
  
}



