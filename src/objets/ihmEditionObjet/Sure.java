package objets.ihmEditionObjet;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Sure extends Frame{
  public Button ok;
  public Button annuler;
  Label text;
  
  public Sure() {
    super("Attention");
    setLayout(new BorderLayout());

    ok= new Button("Ouais");
    annuler = new Button("En fait, non");
    text= new Label("Êtes-vous sûr?");
    
    add(text,BorderLayout.CENTER);
    Panel p=new Panel(new GridLayout(1,2));
    p.add(ok);
    p.add(annuler);
    add(p,BorderLayout.SOUTH);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent w) {
        setVisible(false);
      }
        
      }
      
    );
    setSize(200,100);
    setLocation(500,300);
    setVisible(true);
  }
  
  public void donnerReaction(ActionListener a) {
    ok.addActionListener(a);
    annuler.addActionListener(a);
  }
}
