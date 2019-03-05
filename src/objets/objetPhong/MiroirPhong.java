package objets.objetPhong;

import java.awt.Color;

import algLin.Point3;
import algLin.R3;
import objetmaths.surfacemaths.SurfMath;
import objetmaths.volumemaths.VolumeMath;
import optique.*;

public abstract class MiroirPhong extends Surface {
	
	CouleurS[] listeCouleurs = {new CouleurS(Color.WHITE)};
	
	final double kd=0;		
	double ks=1;
	final double s = Integer.MAX_VALUE;
	
	public MiroirPhong(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public abstract VolumeMath domaineIllumine(SourcePonctuelleIsotrope s);
	
	public CouleurL getColor(Point3 m, R3 d) {
	      //CouleurL lumTot = sc.getAmbiant().getCouleurL();
		  CouleurL lumTot = CouleurL.noir;
	      CouleurL lumTotSpec = CouleurL.noir;
	      R3 n = d.bonSens(getNorm(m));
	      Photon lux;
	      double costheta;
	     // double cosalpha;
	      for (Source src : sc.getSources()) {
	        lux=src.getInfluence(m);
	        costheta= - n.scal(lux.getV());
	        if (costheta>0) {
	          //lumTot = lumTot.plus(lux.getCoul().multiplieIntensite(kd * costheta));  //terme de reflexion diffuse
	          //cosalpha=Math.abs(d.scal(n.prod(2*costheta).plus(lux.getV()).normer()));  //cos(alpha)=d.c où c=2*(L.n)n-L (normé) (symetrie axiale)
	          //lumTotSpec=lumTotSpec.plus(lux.getCoul().multiplieIntensite(ks * Math.pow(cosalpha, s)));
	          lumTotSpec=lumTotSpec.plus(lux.getCoul().multiplieIntensite(ks));

	        }
	        }
	      //lumTotSpec=CouleurL.noir;
		      return (getCouleurIntra(m).getResultante(lumTot)).plus(lumTotSpec);
		    }
	}
