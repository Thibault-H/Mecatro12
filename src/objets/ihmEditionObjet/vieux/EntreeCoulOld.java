package objets.ihmEditionObjet.vieux;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import algLin.R3;
import ihm.Programme;
import objets.ihmEditionObjet.ColorChooserDemo;

public class EntreeCoulOld extends EntreeVectOld{

  /**
   * 
   */
  private static final long serialVersionUID = 5765129846630675637L;
  JButton choisirCoul;
  CarreCouleur montrerCoul;
  JFrame frame;
  ColorChooserDemo autreFenetre;
  Color ancValCoul;
  
  public EntreeCoulOld(boolean b, Programme p) {
    super(b,p);
    
    
    //===========================================
    // Selection des couleurs
    //Create and set up the window.
    JFrame frame = new JFrame("ColorChooserDemo");

    //Create and set up the content pane.
    autreFenetre = new ColorChooserDemo( new ActionListener() {
      @Override
	public void actionPerformed(ActionEvent e) {
        setCouleur(autreFenetre.tcc.getColor());            
      }       
    });
    autreFenetre.setOpaque(true); //content panes must be opaque
    frame.setContentPane(autreFenetre);

    //Display the window.
    frame.pack();
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    
    
    
    //Petits trucs jolis
    montrerCoul=new CarreCouleur();
    montrerCoul.setSize(20,30);
   
    
    choisirCoul=new JButton("Autre");
    choisirCoul.addActionListener(new ActionListener() {
      @Override
	public void actionPerformed(ActionEvent e) {

        frame.setVisible(true);
        autreFenetre.tcc.setColor(getCouleur());
      }
      
    });
    
    Panel panel = new Panel();
    panel.setLayout(new GridLayout(3,1));
    panel.add(montrerCoul);
    panel.add(choisirCoul);
    add(panel);
  }
  
  
  
  /** Renvoie la couleur lue, si possible
   * 
   * @return
   */
  public Color getCouleur() {
    R3 sol = getValue();
    try {
      ancValCoul = new Color( (float) sol.get1(), (float) sol.get2(), (float) sol.get3());
      setCouleur( ancValCoul);
      return ancValCoul;
    }
    catch(IllegalArgumentException e) {
      System.out.print("Veuillez rentrer des valeurs entre 0 et 1");
      return ancValCoul;
    }
  }
  
  /** A utiliser pour rentrer une couleur RGB avec des valeurs jusque 255
   * 
   */
  public void setCouleur(Color c) {
    setValue( ((double)c.getRed())/255 , ((double)c.getGreen())/255 , ((double)c.getBlue())/255);
    montrerCoul.setColor(c);
    autreFenetre.tcc.setColor(c);
    new ComponentEvent(this,ComponentEvent.COMPONENT_FIRST);
  }

  
}
