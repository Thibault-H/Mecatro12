package objets.objetPhong;

import java.awt.Color;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import objets.CategorieObjet;
import objets.ObjetRaytracing;
import objets.scene.Stageable;
import optique.Ambiant;
import optique.lumiere.CouleurL;

public class Horizon extends ObjetRaytracing {

	double d;
	Color c;
	double i;

	static final Horizon VIDE = new Horizon(Double.POSITIVE_INFINITY, Color.BLACK, new Ambiant(CouleurL.noir)); 


	public Horizon(double di, Color co, Ambiant lum) {
		super("Horizon");
		d=di;
		c=co;
		i=lum.getCouleurL().getIntensite();
	}

	@Override
	public CategorieObjet getTypeObjet() {
		return CategorieObjet.Horizon;
	}


	@Override
	public double dist(Point3 m, R3 d) {
		return this.d;
	}

	@Override
	public CouleurL getColor(Point3 p, VectUnitaire d, Stageable s) {
		return new CouleurL(c.getRed(),c.getGreen(),c.getBlue(),i);
	}

	@Override
	public CouleurL getColorSimple(Point3 p, R3 d, Stageable s) {
		return new CouleurL(c.getRed(),c.getGreen(),c.getBlue(),i);
	}




	@Override
	public CouleurL getColor(Point3 m, R3 d, Stageable s, int ref) {
		return getColor(m,d,s);
	}


}
