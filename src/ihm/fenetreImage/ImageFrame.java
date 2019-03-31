package ihm.fenetreImage;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

///////////////////////////////////////////////////////////////
// CLASSE ImageFrame à utiliser pour afficher une image
// dans une fenêtre séparée (de taille optimale)
///////////////////////////////////////////////////////////////
class ImageFrame extends Frame implements ComponentListener{
  static final long serialVersionUID = 1L; // attribut recommandé pour un objet "Serializable"
  private BufferedImage img;
  private ImageCanvas can;



  
  public ImageFrame() {
    super();
    setLayout(new FlowLayout());
/*    ScrollPane sp = new ScrollPane();
    add(sp, BorderLayout.CENTER);*/
    can = new ImageCanvas();
/*    sp.add(can);*/
    add(can);
    addComponentListener(this);
  }
  
  
  /**Adapter la taille à celle du canevas
   * 
   */
  public void MAJSize() {
    setSize(can.getSize());
  }
  
  
  /**Modifier le coefficient de zoom
   * 
   * @param a
   */
  public void setZoom(double a) {
    can.setZoom(a);
    MAJSize();
  }
  


  

  
  
  /**Affecter l'image au canevas et adapter les tailles
   * 
   * @param img
   */
  public void affecterImage(BufferedImage img) {
    this.img=img;
    setSize(img.getWidth(), img.getHeight());
    can.affecterImage(img);
    repaint();
  }
  
  /**Affecter l'image au canevas et au zoom et adapter les tailles
   * 
   * @param img
   */
  public void affecterImage(BufferedImage img, double zoom) {
    this.img=img;
    can.affecterImage(img,zoom);
    MAJSize();
    repaint();
  }
  

  @Override
public void componentResized(ComponentEvent arg0) {
   /* can.setZoom( 100 * Math.min( ((double) getWidth()) / ((double)img.getWidth()), ((double) getHeight())/((double)img.getHeight())));
    repaint();*/

  }
  
  
  
  
  
  
  
  

  @Override
  public void componentHidden(ComponentEvent arg0) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void componentMoved(ComponentEvent arg0) {
    // TODO Auto-generated method stub
    
  }



  @Override
  public void componentShown(ComponentEvent arg0) {
    // TODO Auto-generated method stub
    
  }
  
  /*  public static BufferedImage grossir(BufferedImage img, double x) {
  if (x==0)
    return null;
  else {
    BufferedImage result = new BufferedImage( (int) x*img.getWidth(), (int) x*img.getHeight(), img.getType());
    Graphics2D g =result.createGraphics();
    for (int i=0; i<result.getWidth();i++) {
      for (int j=0; j<result.getHeight(); j++) {
        result.setRGB(i, j, img.getRGB((int) (i/x), (int) (j/x)));
      }
    }
    return result;
  }*/
  
}
  
