package ihm.fenetre1;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import corps.ParametresRaytracing;
import ihm.Fenetre1;

public class OngParam extends Panel implements ItemListener{
  public Button ok;
  public TextField larg;
public TextField haut;
  Label lLarg, lHaut, lFormat;
  protected Choice format;
  public Checkbox largOuHaut;
  
  Fenetre1 fen;
  
  
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
  
  public OngParam(Fenetre1 fen) {
    super();
    setLayout(new GridLayout(4,2));
    
    //Boutons
    ok=new Button("Valider");
    ok.addActionListener(fen);
    larg = new TextField();
    haut= new TextField();
    haut.setEditable(false);
    format=new Choice();
    format.add("jpg");
    format.add("png");
    largOuHaut=new Checkbox("Editer la hauteur?");
    largOuHaut.addItemListener(this);
    MAJ(fen.p.r.getParam());
    
    
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
    add(largOuHaut);
    add(ok);
    
  }

  public void MAJ(ParametresRaytracing p) {
    larg.setText(String.valueOf(p.getLargpx()));
    haut.setText(String.valueOf(p.getHautpx()));
    format.select(1);
  }


  public void itemStateChanged(ItemEvent e) {
    if (e.getSource()==largOuHaut) {
      boolean b= e.getStateChange()==ItemEvent.SELECTED;
      haut.setEditable(b);
      larg.setEditable(!b);
      
    }
    
  }
}
