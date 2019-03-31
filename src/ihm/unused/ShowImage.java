package ihm.unused;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class ShowImage extends Frame {
  public BufferedImage  image;
  double grossissement;
  
  public ShowImage() {
  try {
  System.out.println("Enter image name\n");
  BufferedReader bf=new BufferedReader(new 
InputStreamReader(System.in));
 String imageName=bf.readLine();
  File input = new File(imageName);
  image = ImageIO.read(input);
  
  grossissement=1;
  } catch (IOException ie) {
  System.out.println("Error:"+ie.getMessage());
  }
  }

  
  public static BufferedImage grossir(BufferedImage img, double x) {
    BufferedImage result = new BufferedImage( (int) x*img.getWidth(), (int) x*img.getHeight(), img.getType());
    Graphics2D g =result.createGraphics();
    for (int i=0; i<result.getWidth();i++) {
      for (int j=0; j<result.getHeight(); j++) {
        result.setRGB(i, j, img.getRGB((int) (i/x), (int) (j/x)));
      }
    }
    return result;
  }
  
  
  
  
  @Override
public void paint(Graphics g) {
  grossissement=Math.min(this.getHeight()/image.getHeight() , getWidth()/image.getWidth());
  g.drawImage( grossir(image,grossissement), 0, 0, null);
  }
  

  

  static public void main(String args[]) throws
Exception {
  Frame framed = new Frame("Display image");
  ShowImage paneld = new ShowImage();
  framed.add(paneld);
  framed.setSize(paneld.image.getWidth(),paneld.image.getHeight());
  framed.setVisible(true);
  }
}