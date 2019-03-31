package objets.objetMecatro;

import java.awt.Color;

import algLin.Point3;
import algLin.R3;
import objetmaths.surfacemaths.RectangleMath;
import objets.MiroirMecatro;
import objets.objetPhong.Rectangle;
import objets.objetPhong.Surface;
import objets.scene.SceneMecatro;

/*Miroir Rectangle avec une unique source ponctuelle en 0
 * 
 */
public class MiroirRectangle extends MiroirMecatro{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4502307120773927116L;

	

	public MiroirRectangle(String nom, R3 n, Point3 centre, double longueur, double largeur) {
		forme = new Rectangle(nom,n, centre, largeur, longueur, Color.RED);
		domaineAction = new RienIllumine();
	}
	
	
	@Override
	public void setScene(SceneMecatro s) {
		sc=s;
		domaineAction = new DomaineIlluminePyramide(sc.source, (RectangleMath)forme.getSurfMath());
	}
	
	@Override
	public Surface getSurfacePhong() {
		return forme;
	}

}
