package objets.ihmEditionObjet;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class CarreCouleur extends Canvas{

  Color c;
  
  public CarreCouleur(Color coul) {
    c=coul;
  }
  
  public CarreCouleur() {
    c=Color.black;
  }
  
  public void setColor(Color coul) {
    c=coul;
    repaint();
  }
  
  public Color getColor() {
    return c;
  }
  
  public void paint(Graphics g) {
    g.setColor(c);
    g.fillRect(0, 0, getWidth(), getHeight());
  }
  

}
