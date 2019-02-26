package objets.ihmEditionObjet;

import java.awt.Panel;
import java.awt.TextField;
import javax.swing.JPanel;
import algLin.R3;

public class EntreeScal extends JPanel{
  TextField champ;
  double ancVal;
  
  
  
  public EntreeScal() {
    super();
    champ= new TextField();
    add(champ);
    ancVal=0;
  }
  
  
  public double getValue() {
    try {
      return Double.parseDouble(champ.getText());
    }
    catch (NumberFormatException e) {
      System.out.println("Entrée non reconnue. Veuillez entrer un nombre.");
      return ancVal;
    }
  }
  
  public void setValue(double d) {
    champ.setText(String.valueOf(d));
  }
  
  
  public void setEditable(boolean b) {
    champ.setEditable(b);
  }
}
