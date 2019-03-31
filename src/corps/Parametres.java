package corps;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import optique.CouleurL;

/*Classe contenant les parametres de l'image à générer et ceux de la scène
 * 
 */
public class Parametres implements Serializable{

  protected static final long serialVersionUID = 4015220554644674093L;
  protected double largpx,hautpx;
  /**Jamais directement modifiÃ©; rapport des dimensions de l'image finale par celles du cadre "virtuel"
   * 
   */
  double ratio; 

  public static final double h=0.000001;
  protected int refMiroir=0;
  
  double larg, haut;

  
  
  public Parametres() {     
    //Image
    largpx = 300;
    hautpx = 200;
    larg= 5;
    ratio = larg/largpx;
    haut = ratio * hautpx;    
  }
  
  public Parametres(Parametres p) {
    largpx = p.largpx;
    hautpx = p.hautpx;
    larg= p.larg;
    ratio = p.ratio;
    haut = p.haut;
  }
  
  

  
  //===================================================
  //====================================================
  //Getters & Setters
  
  public int getMiroir() {
    return refMiroir;
  }

  //=========================================================
  //Parametres d'image
  
  /**
   * @return the largpx
   */
  public int getLargpx() {
    return (int)(largpx+0.5);
  }


  /**
   * @return the hautpx
   */
  public int getHautpx() {
    return (int) (hautpx+0.5);
  }
  
  
  
  /**Modifie la hauteur du cadre "virtuel" et adapte sa largeur pour conserver le ratio
   * 
   * @param haut
   */
  public void setHaut(double haut) {   
    this.haut = haut;  
    ratio= haut/hautpx;
    larg=ratio*largpx;
    }
  



  /**Modifie la largeur du cadre "virtuel" et adapte sa hauteur pour conserver le ratio
   * 
   * @param larg
   */
  public void setLarg(double larg) {   
    this.larg = larg;  
    ratio= larg/largpx;
    haut=ratio*hautpx;
    } 

  /**Modifie la hauteur de l'image rendue et adapte celle du cadre virtuel sans toucher au ratio
   * 
   * @param hautpx
   */
  public void setHautpx(int hautpx) {    
    this.hautpx = hautpx;  
    haut= ratio*hautpx;
  } 
  
  /**Modifie la largeur de l'image rendue et adapte celle du cadre virtuel sans toucher au ratio
   * 
   * @param largpx
   */
  public void setLargpx(int largpx) {    
    this.largpx = largpx;  
    larg= ratio*largpx;
  } 
  
  
  /**Aggrandit l'image rendue sans toucher au cadre "virtuel"
   * 
   * @param facteur
   */
  public void aggrandir(double facteur) {
    ratio /= facteur;
    hautpx = hautpx *facteur;
    largpx = largpx*facteur;    
  }
  
  /**Aggrandit l'image rendue jusqu'Ã  ce que sa largeur atteigne lfin, sans toucher au cadre "virtuel"
   * 
   * @param lfin
   */
  public void aggrandirLJusque(int lfin) {
    aggrandir(lfin/largpx);
  }
  
  /**Aggrandit l'image rendue jusqu'Ã  ce que sa hauteur atteigne hfin, sans toucher au cadre "virtuel"
   * 
   * @param hfin
   */
  public void aggrandirHJusque(int hfin) {
    aggrandir(hfin/hautpx);
  }
  
  
  //==============================================================
  
	public BufferedImage getPic(CouleurL[][] premImage, double iBlanc) {
		BufferedImage off_Image = new BufferedImage(getLargpx(), getHautpx(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = off_Image.createGraphics();
		for (int i = 0; i < getLargpx(); i++) {
			for (int j = 0; j < getHautpx(); j++) {
				g2.setColor(premImage[i][j].appliquerIntGlobale(iBlanc));
														/* * if (premImage[i][j].appliquerIntGlobale(iBlanc).getBlue() <25
														 * && premImage[i][j].appliquerIntGlobale(iBlanc).getRed() <25)
														 * System.out.print("");*/
														 
				g2.fillRect(i, j, 1, 1);
			}
		}
		return off_Image;
	}
}
