package objets.ihmEditionObjet.vieux;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;
import algLin.Point3;
import algLin.R3;
import ihm.Programme;

public class EntreeVectOld extends JPanel implements ActionListener{
  /**
   * 
   */
  private static final long serialVersionUID = -964034353395319154L;
  TextField[] tab;
  R3 ancVal;
  Joystick joy;
  protected Programme p;
  Boolean b;
  
  
  public Joystick getJoystick() {
    return joy;
  }
  
  
  public void setJoystick(boolean b) {
    if (b) {
      add(joy);
    }
  }
  
  public EntreeVectOld(boolean b, Programme p) {
    super();
    this.b=b;
    this.p=p;
    ancVal=R3.zero;
    setLayout(new GridLayout(1,2));
    Panel pan= new Panel();
    if (b) 
      pan.setLayout(new GridLayout(1,3));
    else 
      pan.setLayout(new GridLayout(3,1));
    tab = new TextField[3];
    for (int i=0; i<=2;i++) {
      tab[i]=new TextField();
      tab[i].setColumns(1);
      pan.add(tab[i]);
    }
    add(pan);
    joy = new Joystick(this, "");
    
  }
  
  
  /**Renvoie la valeur du vecteur entré
   * 
   * @return
   */
  public R3 getValue() {
    try {
      ancVal= new R3(Double.parseDouble(tab[0].getText()),Double.parseDouble(tab[1].getText()), Double.parseDouble(tab[2].getText()));
    }
    catch (NumberFormatException e) {
      System.out.println("Entrée non reconnue. Veuillez entrer des nombres.");
    }
    return ancVal;
  }
  
  /**Définit la valeur des zones de texte
   * 
   * @param v
   */
  public void setValue(R3 v) {
    tab[0].setText(String.valueOf(v.get1()));
    tab[1].setText(String.valueOf(v.get2()));
    tab[2].setText(String.valueOf(v.get3()));    
  }
  
  /**Définit la valeur des zones de textes
   * 
   * @param a
   * @param b
   * @param c
   */
  public void setValue(double a, double b, double c) {
    tab[0].setText(String.valueOf(a));
    tab[1].setText(String.valueOf(b));
    tab[2].setText(String.valueOf(c)); 
  }


  
  public void setEditable(boolean b) {
    for (TextField o:tab) {
      o.setEditable(b);
    }
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() instanceof Button) {
      int action= joy.AFaire((Button) e.getSource());
      setValue(getValue().transformation(action,  p.incrrot, p.r.getParam().getBase(), Point3.origine));
      new ComponentEvent(this,ComponentEvent.COMPONENT_FIRST);
    }
  }
  
  
  
}
