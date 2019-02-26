package corps;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import algLin.R3;
import objets.scene.Scene;
import optique.CouleurL;
import optique.Source;

public abstract class GenerateurImage {
	// =============================================
		// Attributs
		// =============================================

		// Propriétés fondamentales et nécessaires
	

		
		public double intBlanc;
		public CouleurL[][] imageBase;
		
		public abstract Parametres getParam() ;

		

		public abstract Scene getScene() ;
		
		// =================================================
		// Fonctions auxilliaires du programme

		public void modifBlanc(double d, double dMax) {
			intBlanc = intBlanc * (1 + d / dMax);
		}

		

		/**
		 * Renvoie le tableau de couleur qui correspond à l'image rendue
		 * 
		 * @return
		 */
		public abstract CouleurL[][] getTab();

		
		/**
		 * S'adapte à la luminosité globale de la scène pour rendre l'image finale
		 * 
		 * @param premImage
		 * @return
		 */
		public BufferedImage getPic(CouleurL[][] premImage, double iBlanc) {
			BufferedImage off_Image = new BufferedImage(getParam().getLargpx(), getParam().getHautpx(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = off_Image.createGraphics();
			for (int i = 0; i < getParam().getLargpx(); i++) {
				for (int j = 0; j < getParam().getHautpx(); j++) {
					g2.setColor(premImage[i][j]
							.appliquerIntGlobale(iBlanc));/*
															 * if (premImage[i][j].appliquerIntGlobale(iBlanc).getBlue() <25
															 * && premImage[i][j].appliquerIntGlobale(iBlanc).getRed() <25)
															 * System.out.print("");
															 */
					g2.fillRect(i, j, 1, 1);
				}
			}
			return off_Image;
		}

		// ====================================
		// Programme principal

		/**
		 * Renvoie l'image de la scène correspondant au point de vue choisi
		 * 
		 * @return
		 */
		public BufferedImage mainProgram() {
			long startTime = System.nanoTime();

			CouleurL[][] premImage = getTab();

			BufferedImage off_Image = getPic(premImage, intBlanc);
			try {
				File outputfile = new File("saved.png");
				ImageIO.write(off_Image, "png", outputfile);
			} catch (IOException e) {
			}
			System.out.println((System.nanoTime() - startTime) / 1000000 + "ms de lecture");
			return off_Image;
		}

		
		
		
}
