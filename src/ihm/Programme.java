package ihm;


import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import corps.Mecatro;
import corps.Raytracing;
import ihm.fenetre1.edition.FenetreEntree;
import objets.objetMecatro.MiroirRectangle;
import objets.objetPhong.Sphere;
import objets.scene.SceneMecatro;
import objets.scene.SceneRaytracing;
import objets.scene.Stageable;
import optique.SourcePonctuelleIsotrope;



public class Programme implements ActionListener{

	public Mode mode;
	public Programmable pr;

	public Fenetre1 f1;
	public Fenetre2 f2;



	public Programme(Mode m) {

		mode = m;
		switch (mode) {
		case Raytracing:
			SceneRaytracing sc = new SceneRaytracing();

			sc.ajouter(new Sphere( "Sphere1", Point3.origine.plus(new R3(-3,4,0)), 3, Color.red ));
			sc.ajouter(new Sphere( "Sphere2", Point3.origine.plus(new R3(2,4,0)), 2, Color.blue ));
			//Cube c =new Cube(Point3.origine.plus(new R3(0,4,0)), 3, M3.id, Color.red );
			//c.tourner(R3.uz,Math.PI/4);
			//r.ajouter(c);
			sc.ajouter(new SourcePonctuelleIsotrope("Source 1",Point3.origine.plus(new R3(0,1,1)) , 1000));
			pr=new Raytracing(sc);
			f1=new Fenetre1(pr);
			break;
		case Miroirs:
			SceneMecatro sc2 = new SceneMecatro();
			sc2.ajouter(new MiroirRectangle("Miroir 1" ,R3.ux.opp(), Point3.origine.moins(R3.uy.prod(5)),10,10));
			pr = new Mecatro(sc2);
			f1=new Fenetre1(pr);
			break;
		default:
			break;
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==f1.commencer || e.getSource()==f1.menuCommencer)
			f2=new Fenetre2(pr);
	}


	public static void main(String[] args) {
		ChoixDuMode c = new ChoixDuMode();
	}

}
