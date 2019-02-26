package ihm;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ChoixDuMode {
	
	Programme p;
	
  public ChoixDuMode() {
    JDialog.setDefaultLookAndFeelDecorated(true);
    Object[] selectionValues = { "Image haute qualité (raytracing)", "Distribution spatiale d'un rayonnement réfléchi (mécatro)" };
    String initialSelection = "Dogs";
    Object selection = JOptionPane.showInputDialog(null, "Quel type de simulation cherchez-vous?",
        "Choix du mode", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
    
    
    p = null;
    switch (selection.toString()) {
    case "Image haute qualité (raytracing)":
    	p = new Programme(Mode.Raytracing);
    	break;
    case "Distribution spatiale d'un rayonnement réfléchi (mécatro)":
    	p = new Programme(Mode.Miroirs);
    	break;
    }
    
    
  }
  
}