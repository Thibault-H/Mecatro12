package optique;

import java.awt.Color;
import java.io.Serializable;

import objets.editable.Entrable;
import objets.editable.TypeEntrable;

public class CouleurS implements Serializable, Entrable{
  /**
   * 
   */
  private static final long serialVersionUID = 3172943563213286148L;
  Color c;
  float r,g,b; //Proportion d'intensite lumineuse absorbee en fonction de la couleur 
  
  
  public CouleurS(Color coul) {
    r= ((float)coul.getRed())/255;
    b= ((float)coul.getBlue())/255;
    g= ((float)coul.getGreen())/255;
  }
  
  
  public Color getValue() {
    return new Color(r,g,b);
  }
  
  public double getLuminositeBase() {
    return r+g+b/3;
  }
  
  public CouleurL getResultante(CouleurL lum) {
/*    double newr=(lum.i*lum.r*r/lum.maxCoul);
    double newg=(lum.i*lum.b*b/(lum.maxCoul));
    double newb=(lum.i*lum.b*b/(lum.maxCoul));*/
    return new CouleurL((lum.i*lum.r*r/lum.maxCoul), (lum.i*lum.g*g/(lum.maxCoul)), (lum.i*lum.b*b/(lum.maxCoul)), lum.getIntensite());
  }
  
  
  @Override
  public TypeEntrable getTypeEntrable() {
  	// TODO Auto-generated method stub
  	return TypeEntrable.Couleur;
  }
    
  public static void main(String[] args) {
/*    CouleurS c= new CouleurS(Color.red);
    CouleurL l = new CouleurL(1,1,1,1);
    System.out.println(c.getResultante(l,1));
    BufferedImage off_Image =
        new BufferedImage(200,200,
                          BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = off_Image.createGraphics();
        g2.fillRect(0,0,200,200);
    try {
      File outputfile = new File("saved.png");
      ImageIO.write(off_Image, "png", outputfile);
  } catch (IOException e) {
  }*/
  }



  
  
  
  
  
}
