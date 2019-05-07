package ihm;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ihm.fenetreImage.AfficheurImageEdition;
import ihm.fenetreImage.ImageScrollPane;

public interface Programmable {
	
	//Fenetre 1
	BufferedImage imageEdition();
	default ImageScrollPane afficheurImage() {
		return new AfficheurImageEdition(this);
	}
	JPanel commandesSupp();
	JTabbedPane ongletsEdition();
	
	//Fenetre 2
	BufferedImage imageFinale();
	JFrame fenetreOptions();
	JMenu menu();
	String getFormatImage();
	
	
	

}
