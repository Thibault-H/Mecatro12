package objets.objetMecatro;

import java.awt.Color;

import algLin.Point3;
import algLin.R3;
import objetmaths.surfacemaths.RectangleMath;
import objets.ObjetMecatro;
import objets.objetPhong.Rectangle;
import objets.objetPhong.Surface;
import objets.scene.SceneMecatro;

/*Miroir Rectangle avec une unique source ponctuelle en 0
 * 
 */
public class MiroirRectangle extends ObjetMecatro{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4502307120773927116L;

	

	public MiroirRectangle(R3 n, Point3 centre, double longueur, double largeur) {
		forme = new Rectangle(n, centre, largeur, longueur, Color.RED);
		domaineAction = new RienIllumine();
	}
	
	
	public void setScene(SceneMecatro s) {
		sc=s;
		domaineAction = new DomaineIlluminePyramide(sc.source, (RectangleMath)forme.getSurfMath());
	}
	
	public Surface getSurfacePhong() {
		return forme;
	}

}
