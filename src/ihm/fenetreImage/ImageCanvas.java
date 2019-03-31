package ihm.fenetreImage;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

///////////////////////////////////////////////////////////////
// CLASSE ImageCanvas à utiliser pour afficher une image 
// dans la fenêtre principale
///////////////////////////////////////////////////////////////
class ImageCanvas extends Canvas {
  static final long serialVersionUID = 1L; // attribut recommandé pour un objet "Serializable"
  private BufferedImage img;
  public double coefZoom;   //En pourcentage
  protected Image imgCachee;          // l'image jamais affichée 
  protected Dimension dimImgCachee;   // sa taille
  
  
  
  
  
  
  /**Affecter et dessine l'image
   * 
   * @param img
   */
  public void affecterImage(BufferedImage img) {
    this.img = img;
    coefZoom = 100;
    MAJSize();
    repaint();  // demande de re-dessin du contenu du canvas : invoque paint()
  }
  
  
  /**Affecte et dessine l'image, en appliquant en zoom de facteur a
   * 
   * @param img
   * @param a
   */
  public void affecterImage(BufferedImage img, double a) {
    this.img = img;
    coefZoom = a;
    MAJSize();
    repaint();  // demande de re-dessin du contenu du canvas : invoque paint()
  }
  
  
  
  
  public void setZoom(double a) {
    coefZoom=a;
    MAJSize();
  }
  
  /**Adapte le canvas à l'image zoomée
   * 
   */
  public void MAJSize() {
    if(img!=null)
      setSize( (int) (coefZoom/100)*(img.getWidth()),(int) (coefZoom/100)*(img.getHeight()));
  }
  
  
  
  
  
  
  
  @Override
public void paint(Graphics g) {
    if (img != null) {
      g.drawImage(img, 0, 0, (int) (coefZoom/100)*(img.getWidth()),(int) (coefZoom/100)*(img.getHeight()), this);  // affichage de l'image
    }
  }
  
@Override
public void update(Graphics g) {
    Dimension dimCanvas = getSize();                 // taille actuelle du canvas visible sur l'écran
    if ( imgCachee == null ||                        // l'image cachée n'a pas encore été crée
         dimImgCachee.width  != dimCanvas.width ||   // l'IHM a été redimensionnée
         dimImgCachee.height != dimCanvas.height ) {
      imgCachee = createImage(dimCanvas.width, dimCanvas.height);
      dimImgCachee = dimCanvas;                     
    }
    paint(imgCachee.getGraphics());               // dessine sur l'image cachée
    g.drawImage(imgCachee, 0, 0, null);           // affiche l'image
  }    
  
  public static void main(String[] args) {
    BufferedImage img=new BufferedImage(500,500, BufferedImage.TYPE_INT_RGB);
    Graphics g2 = img.getGraphics();
    g2.setColor(Color.blue);
    g2.fillRect(100, 100, 300, 300);
    ImageCanvas can = new ImageCanvas();
    can.affecterImage(img);
    System.out.println(img.getWidth());
    System.out.println(can.getWidth());
    
  }
  

  
  
}