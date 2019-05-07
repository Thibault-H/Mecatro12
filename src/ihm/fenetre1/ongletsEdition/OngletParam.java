package ihm.fenetre1.ongletsEdition;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

import corps.GenerateurImageScene;
import corps.tableauCouleurs.Parametres;


//TODO : finitions avec l'entr�e des dimensions

public class OngletParam extends JPanel implements ItemListener, ActionListener{
  public Button ok;
  public JTextField larg;
  public JTextField haut;
  Label lLarg, lHaut, lFormat;
  protected Choice format;
  public Checkbox isHauteurEditee;
  
  
  
  //Fenetre1 fen;
  
  

  
  public OngletParam(GenerateurImageScene g) {
    super();
    setLayout(new GridLayout(4,2));
    
    //Boutons
    ok=new Button("Valider");
    ok.addActionListener(g);
    
    
    NumberFormat integerFieldFormatter;
    integerFieldFormatter = NumberFormat.getIntegerInstance();
    integerFieldFormatter.setMaximumFractionDigits(0);
    integerFieldFormatter.setGroupingUsed(false);	//1000 ne devient pas "1,000"

    larg = new JFormattedTextField(integerFieldFormatter );
    larg.setColumns(3);
    
    haut = new JFormattedTextField(integerFieldFormatter );
    haut.setColumns(3);
    
    format=new Choice();
    format.add("jpg");
    format.add("png");
    isHauteurEditee=new Checkbox("Editer la hauteur?");
    isHauteurEditee.addItemListener(this);
    majParam(g.getParam());
    
    
    //Etiquettes
    lLarg= new Label("Largeur");
    lHaut= new Label("Hauteur");
    lFormat=new Label("Format");
    
    //Finitions
    add(lLarg);
    add(larg);
    add(lHaut);
    add(haut);
    add(lFormat);
    add(format);
    add(isHauteurEditee);
    add(ok);
    
    haut.setEditable(false);
  }
  
  //======================================
  //Lecture
  
  /**Renvoie l'entier représentant la hauteur de l'image finale
   * 
   * @return
   */
  public int getHaut() {
     return Integer.parseInt(haut.getText());
  }
  
  /**Renvoie l'entier représentant la largeur de l'image finale
   * 
   * @return
   */
  public int getLarg() {
    return Integer.parseInt(larg.getText());
 }
  
  /**Renvoie le format de l'image finale
   * 
   * @return
   */
  public String getFormat() {
    return format.getSelectedItem();
  }

  public void majParam(Parametres p) {
    larg.setText(String.valueOf(p.getLargpx()));
    haut.setText(String.valueOf(p.getHautpx()));
    format.select(1);
  }

  
//===============================================================
// Action

  @Override
public void itemStateChanged(ItemEvent e) {
    if (e.getSource()==isHauteurEditee) {
      boolean b= e.getStateChange()==ItemEvent.SELECTED;
      haut.setEditable(b);
      larg.setEditable(!b);
      
    }
    
  }

@Override
public void actionPerformed(ActionEvent e) {
	}

}
