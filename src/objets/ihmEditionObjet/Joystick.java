package objets.ihmEditionObjet;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.ComponentOrientation;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Joystick extends Panel{
  ActionListener fen;
  Button[] fleches;
  
  
  
  public Joystick(ActionListener a, String s) {
    new Panel();
    //Construction des boutons
    fen=a;
    fleches=new Button[6];    //0-1-2-3-4-5 <=> haut-bas-gauche-droite-avant-arrière
    fleches[0]=new Button("^");
    fleches[1] = new Button("v");
    fleches[2]=new Button("<");
    fleches[3] = new Button(">");
    fleches[4]=new Button("^");
    fleches[5] = new Button("v");
    for (int i=0; i<6;i++) {
      fleches[i].addActionListener(fen);
      fleches[i].setSize(50, 50);
    }
    Label nom=new Label(s); //Nom
    
/*    // Placement
    setSize(170,100);
    setLayout(new BorderLayout());
    
    Label nom=new Label(s); //Nom
        
    //Fleche du haut
    Panel p=new Panel();
    p.setSize(150,50);
    p.setLayout(new BorderLayout());
    p.add(fleches[0],BorderLayout.SOUTH);
    
    //Autres fleches côtés
    Panel p1=new Panel();
    p1.setSize(150,50);
    p1.add(fleches[2],BorderLayout.CENTER);
    p1.add(fleches[1],BorderLayout.WEST);
    p1.add(fleches[3],BorderLayout.EAST);
    
    //Fleches avant-arrière
    Panel p2 = new Panel();
    p2.setSize(50,100);
    p2.setLayout(new BorderLayout());
    p2.add(fleches[4],BorderLayout.NORTH);
    p2.add(fleches[5],BorderLayout.SOUTH);
        
    
    add(nom,BorderLayout.NORTH);
    add(p,BorderLayout.CENTER);
    add(p1,BorderLayout.SOUTH);
    add(p2,BorderLayout.EAST);*/
    
    
    
    //================================================
   
    setLayout(new GridBagLayout());
    
    GridBagConstraints c = new GridBagConstraints();
    
    c.gridx=0;
    c.gridy=3;
    c.gridwidth=3;
    add(nom,c);
    
    c.gridwidth=1;
    c.gridx=2;
    c.gridy=0;
    add(fleches[4],c);
    
    c.gridx=1;
    c.gridy=1;
    add(fleches[0],c);
    
    c.gridx=2;
    c.gridy=1;
    add(fleches[5],c);
    
    
    
    c.gridx=0;
    c.gridy=2;
    add(fleches[2],c);
    
    c.gridx=1;
    c.gridy=2;
    add(fleches[1],c);
    
    c.gridx=2;
    c.gridy=2;
    add(fleches[3],c);
    
    
    
    
    
    //===========================================================
    
  }

  /*Convertit le clic de l'utilisateur sur un bouton du joystick en un entier
   * 
   */
  public int AFaire(Button b) {
    if (b==fleches[0])
      return -1;
    else if (b==fleches[1])
      return -2;
    else if (b==fleches[2])
      return -3;
    else if (b==fleches[3])
      return -4;
    else if (b==fleches[4])
      return -5;
    else if (b==fleches[5])
      return -6;
    else return Integer.MAX_VALUE;
  }
  
  
  public static void main(String[] arg) {
    Frame f= new Frame();
    f.add(new Joystick(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        
      }},"bonjour"));
    f.pack();
    f.setVisible(true);
    
    
  }
}
