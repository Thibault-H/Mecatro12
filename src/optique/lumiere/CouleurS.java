package optique.lumiere;

import java.awt.Color;
import java.io.Serializable;

public class CouleurS implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3172943563213286148L;

	float r,g,b; //Proportion d'intensite lumineuse absorbee en fonction de la couleur 


	public CouleurS(Color coul) {
		r= ((float)coul.getRed())/255;
		b= ((float)coul.getBlue())/255;
		g= ((float)coul.getGreen())/255;
	}

	public CouleurS(CouleurS co) {
		r = co.r;
		g = co.g;
		b = co.b;
	}

	public Color getColor() {
		return new Color(r,g,b);
	}

	public double getLuminositeBase() {
		return r+g+b/3;
	}

	public CouleurL getResultante(CouleurL lum) {
		if (r==0 && g==0 && b==0)
			return CouleurL.noir;
		else {
			CouleurL c = new CouleurL((lum.i*lum.r*r), (lum.i*lum.g*g), (lum.i*lum.b*b), lum.getIntensite());
			return c;
		}
	}


	@Override
	public String toString() {
		return String.format("( CouleurS: (%.3g ; %.3g ; %.3g) )",r,g,b);
	}

	@Override
	public int hashCode() {
		return (int)(255*(r+g+b));
	}

	@Override
	public boolean equals(Object o2) {
		if (o2 instanceof CouleurS) {
			return r== ((CouleurS)o2).r && g== ((CouleurS)o2).g && b== ((CouleurS)o2).b;
		}
		else return false;
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
