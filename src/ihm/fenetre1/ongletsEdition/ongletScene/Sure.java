package ihm.fenetre1.ongletsEdition.ongletScene;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Sure extends Frame{
  public Button ok;
  public Button annuler;
  Label text;
  
  public Sure() {
    super("Attention");
    setLayout(new BorderLayout());

    ok= new Button("Ouais");
    annuler = new Button("En fait, non");
    text= new Label("En êtes-vous sûr?");
    
    add(text,BorderLayout.CENTER);
    Panel p=new Panel(new GridLayout(1,2));
    p.add(ok);
    p.add(annuler);
    add(p,BorderLayout.SOUTH);

    addWindowListener(new WindowAdapter() {
      @Override
	public void windowClosing(WindowEvent w) {
        setVisible(false);
      }
        
      }
      
    );
    setSize(200,100);
    setLocation(500,300);
    setVisible(true);
  }
  
  public Sure(ActionListener a) {
	  this();
	  donnerReaction(a);
  }
  
  public void donnerReaction(ActionListener a) {
    ok.addActionListener(a);
  }
}
