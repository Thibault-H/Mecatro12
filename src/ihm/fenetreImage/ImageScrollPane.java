package ihm.fenetreImage;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.ScrollPane;
import java.awt.image.BufferedImage;

public class ImageScrollPane extends ScrollPane{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5490744734915991126L;
	private ImageCanvas can;
	private double coefZoom;


	public ImageScrollPane() {
		super();
		coefZoom = 100;
		can=new ImageCanvas();
		add(can);
	}


	/**Rajoute a au coef de zoom
	 * 
	 * @param b
	 */
	public void zoom(double a){
		coefZoom += a;
		reaffecterImage();
	}


	public void modifierCoefZoom(double a) {
		can.coefZoom=a;
	}



	public void affecterImage(BufferedImage img) {
		can.affecterImage(img,coefZoom);
		setSize(can.getSize());
	}


	public void reaffecterImage() {
		can.setZoom(coefZoom);
		can.repaint();
		setSize(can.getSize());
	}



	public static void main(String[] args) {
		Frame f = new Frame();
		ImageScrollPane sp = new ImageScrollPane();
		f.add(sp);
		BufferedImage img = new BufferedImage(100,200,BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.red);
		g.fillRect(0, 0, 50,50 );
		sp.affecterImage(img);
		f.setSize(sp.getSize());
		f.setVisible(true);
	}
}
