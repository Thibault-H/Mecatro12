package ihm;

import java.awt.Checkbox;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ihm.fenetreImage.VisuImage;
import ihm.util.MenuSauverOuvrir;
import objets.ihmEditionObjet.Slide;

public class Fenetre2 extends VisuImage{
  /**
	 * 
	 */
	private static final long serialVersionUID = 6073674098267913730L;
Programme p;
  Slide slideInt;
  Checkbox niveauInt;
  
  MenuSauverOuvrir sauvOuv;
  
  
  public Fenetre2(Programme prog) {
	super();

    p=prog;
	switch (p.mode) {
	case Raytracing :
		changerImage(prog.r.mainProgram());
		break;
	case Miroirs:
		changerImage(prog.mec.mainProgram());
		break;
	}
    
    setLocation(150, 100);
    format=prog.formatImage;
    
    //Boutons
    niveauInt = new Checkbox("Niveau Intensité");
    niveauInt.addItemListener(this);
    
    commandes.add(niveauInt);
    
    //Slide
    slideInt=new Slide();
    slideInt.addChangeListener(new ChangeListener(){

    public void stateChanged(ChangeEvent event){

      modifBlanc(((JSlider)event.getSource()).getValue(), slideInt.getMaximum());
      
    }});
    slideInt.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent w) {
        // masque la feêntre externe :
        slideInt.setVisible(false);
        niveauInt.setState(false);
        
      }
    });

    //=============================================
    //Enregistrement et sauvegarde de scènes
    
    sauvOuv=new MenuSauverOuvrir(p,2);
    menuFichier.add(sauvOuv.sauver);
    menuFichier.add(sauvOuv.ouvrir);
    menuFichier.remove(quitter);
    menuFichier.add(quitter);
    
    setVisible(true);
  }
  
  
  

  public void itemStateChanged(ItemEvent evt) {
    if (evt.getSource() == niveauInt) {
      if (evt.getStateChange() == ItemEvent.SELECTED) {
        slideInt.setVisible(true);
      }
      else slideInt.setVisible(false);
    }
    else super.itemStateChanged(evt);    
  }
  
    
  
  
  public void modifBlanc(double d, double dMax) {
	  switch (p.mode) {
		case Raytracing :
			changerImage(p.r.getParam().getPic(p.r.imageBase, p.r.intBlanc*(1+d/dMax)));;
			break;
		case Miroirs:
			changerImage(p.mec.getParam().getPic(p.r.imageBase, p.r.intBlanc*(1+d/dMax)));;
			break;
		}
  }
  
  
}
