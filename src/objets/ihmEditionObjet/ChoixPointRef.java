package objets.ihmEditionObjet;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.LinkedList;
import algLin.Point3;
import ihm.Programme;
import objets.ObjetRaytracing;
import objets.TourneAutour;
import optique.Source;

public class ChoixPointRef extends EntreePoint implements ItemListener{

  /**
   * 
   */
  private static final long serialVersionUID = 8504758772496438658L;

  
  EntreePoint fenetreMere;
  public List listePoints;
  java.util.List<Point3> listePointsV;
  public Button valider;
  
  boolean estPourCamera;
  
  public ChoixPointRef(boolean b, Programme p, EntreePoint e) {
    super(b,p);
    estPourCamera=false;
    act=p.r.getParam().getOeil();
    fenetreMere=e;
    dJoy=new DoubleJoystick(this);
    
    valider=new Button("OK");
    valider.addActionListener(e);
    
    //Liste des points possibles    
    listePoints= new List();
    listePointsV= new ArrayList<Point3>();
    
    listePoints.add("Oeil");
    listePointsV.add(p.r.getParam().getOeil());
    
    listePoints.add("Origine");
    listePointsV.add(Point3.origine);
    
    for (ObjetRaytracing o: p.r.getScene().getSurfs()) {
      if (o instanceof TourneAutour) {
        listePoints.add(o.getName());
        listePointsV.add(((TourneAutour) o).getPointRef());
      }
    }
    
    for (Source o: p.r.getScene().getSources()) {
      if (o instanceof TourneAutour) {
        listePoints.add(o.getName());
        listePointsV.add(((TourneAutour) o).getPointRef());
      }
    }
    
    listePoints.add("Personnalisé");
    listePointsV.add(null);
    
    
    //Finitions
    listePoints.addItemListener(this);
    Panel pan=new Panel();
    pan.setLayout(new GridLayout(2,1));
    pan.add(valider);
    pan.add(listePoints);
    add(pan);
  }
  
  
  
  
  public ChoixPointRef(boolean b, Programme p, EntreePoint e, Point3 act) {
    this(b,p,e);
    this.act=act;
  }
  
  /**Un constructeur dédié au changement de référentiel de caméra
   * 
   * @param b
   * @param p
   */
  public ChoixPointRef(boolean b, Programme p) {
    this(b,p,null);
    estPourCamera=true;
    
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    if (e.getSource()==listePoints) {
      try{
        if (!estPourCamera) fenetreMere.setRef(listePointsV.get(listePoints.getSelectedIndex()));
        else p.refCamera=listePointsV.get(listePoints.getSelectedIndex());
        
        dJoy.setEnabled(false);
        setEditable(false);
      }
      catch (NullPointerException ex) {
        dJoy.setEnabled(true);
        setEditable(true);
      }
      
    }
    
  }
  
  

}
