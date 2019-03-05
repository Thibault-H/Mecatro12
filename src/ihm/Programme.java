package ihm;


import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;


import algLin.Point3;
import algLin.R3;
import corps.Mecatro;
import corps.Raytracing;
import objets.objetMecatro.MiroirRectangle;
import objets.objetPhong.Sphere;
import optique.SourcePonctuelleIsotrope;



public class Programme implements ActionListener, ItemListener{

  public Mode mode;
  public Raytracing r;
  public Mecatro mec;
  
  public Fenetre1 f1;
  public Fenetre2 f2;

  public Point3 refCamera;
  public double incrtrans; //L'incrément de translation pour le joystick
  public double incrrot;  //L'incrément de translation pour le joystick
  public String formatImage;
  
  
  public Programme(Mode m) {
    r=new Raytracing();
    mec=new Mecatro(100);
    mode = m;
    switch (mode) {
    case Raytracing:
    	r.ajouter(new Sphere( "Sphere1", Point3.origine.plus(new R3(-3,4,0)), 3, Color.red ));
        r.ajouter(new Sphere( "Sphere2", Point3.origine.plus(new R3(2,4,0)), 2, Color.blue ));
	    //Cube c =new Cube(Point3.origine.plus(new R3(0,4,0)), 3, M3.id, Color.red );
	    //c.tourner(R3.uz,Math.PI/4);
	    //r.ajouter(c);
        r.ajouter(new SourcePonctuelleIsotrope(Point3.origine.plus(new R3(0,1,1)) , 5));
        break;
    case Miroirs:
    	r.ajouter(mec.getScene().getSource());
    	mec.ajouter(new MiroirRectangle("Miroir 1" ,R3.ux.opp(), Point3.origine.moins(R3.ux.prod(50)),200,200),r);
    	break;
    default:
    	break;
    }
    
    incrtrans=1;
    incrrot=Math.PI/6;
    refCamera=Point3.origine;
    
    formatImage="jpg";
    
    f1=new Fenetre1(this);
  }
  
  
  public void itemStateChanged(ItemEvent arg0) {
    // TODO Auto-generated method stub
    
  }

  
  public void majRefCam() {
    if (f1.onglet2.fenetreChoix.listePoints.getSelectedIndex()==0)    //Si on a sélectionné "oeil"
      refCamera=r.getParam().getOeil();
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource()==f1.commencer || e.getSource()==f1.menuCommencer) {
      f2=new Fenetre2(this);
    }
    else if (e.getSource() instanceof Button){
      int action= f1.joyPrinc.AFaire((Button) e.getSource());
      r.getParam().setOeil(r.getParam().getOeil().transformation(action, incrtrans, incrrot, r.getParam().getBase(), refCamera));
      majRefCam();
      r.getParam().setBase(r.getParam().getBase().transformation(action, incrrot, r.getParam().getBase(), Point3.origine));
      f1.afficherImage();
      f1.repaint();
    }
  }
  
 /* public BufferedImage mainProgramm() {
	  BufferedImage result = null;
	  switch (mode) {
	    case Raytracing:
	    	result= r.mainProgram();
	    case Miroirs:
	    	result = mec.mainProgram();
	    	break;
	    default:
	    	break;
	    }
	  return result;
  }*/
    
  public static void main(String[] args) {
    ChoixDuMode c = new ChoixDuMode();
  }






  
  
  

}
