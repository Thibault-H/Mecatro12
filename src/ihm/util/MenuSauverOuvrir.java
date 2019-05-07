package ihm.util;

import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class MenuSauverOuvrir implements ActionListener{
  
  //Programme p;
  //Frame frameParent;
  
  private Menu sauver;
  private Menu ouvrir;
 
  
  MenuItem sauverScene;         //sauver scene en binaire
  MenuItem ouvrirScene;
  
  MenuItem sauverPack;        //sauver scene+parametres en binaire
  MenuItem ouvrirPack;
  
  MenuItem sauverFullPack;    //sauver scene+parametres en binaire+image
  
  
  
  public MenuSauverOuvrir() {
/*    this.p=p;
    if (i==1) frameParent=p.f1;
    else if (i==2) frameParent=p.f2;
    else throw new IllegalArgumentException("Numéro de fenêtre invalide!");
    */
    
    sauver=new Menu("Sauver");
    ouvrir=new Menu("Ouvrir");
 
    
    //MenuItems
    sauverScene=new MenuItem("Sauver scene");
    sauverScene.addActionListener(this);
    ouvrirScene=new MenuItem("Ouvrir scene");
    ouvrirScene.addActionListener(this);
    
    sauverPack=new MenuItem("Sauver scene et parametres");
    sauverPack.addActionListener(this);
    ouvrirPack=new MenuItem("Ouvrir scene et parametres");
    ouvrirPack.addActionListener(this);
    
    sauverFullPack=new MenuItem("Sauver scene, parametres et image");
    sauverFullPack.addActionListener(this);
    
    
    sauver.add(sauverScene);
    sauver.add(sauverPack);
    
    //if (i==2) sauver.add(sauverFullPack);
    
    ouvrir.add(ouvrirScene);
    ouvrir.add(ouvrirPack);
    

  }
  
  public void ajouterA(Menu menuPere) {
	  menuPere.add(sauver);
	  menuPere.add(ouvrir);
  }
  
  
  
  @Override
public void actionPerformed(ActionEvent evt) {
   /* if (evt.getSource()== sauverScene || evt.getSource()== sauverPack || evt.getSource()== sauverFullPack) {
      try {
        FileDialog fd = new FileDialog(frameParent);
        fd.setVisible(true);
        boolean faireLesDeux;
        FileOutputStream out;
        
        if (evt.getSource()==sauverScene) {
          faireLesDeux=false;
          out=new FileOutputStream(fd.getDirectory() + fd.getFile()+".bin1");
        }
        else if (evt.getSource()==sauverPack) {
          faireLesDeux=true;
          out=new FileOutputStream(fd.getDirectory() + fd.getFile()+".bin2");
        }
        else {    //ssi evt.getSource() == sauverFullPack
          faireLesDeux=true;
          boolean success =(new File(fd.getDirectory() + "\\" + fd.getFile()) ).mkdirs();
          if (!success) throw new IOException("Probleme lors de la creation du dossier");
          else {
            out=new FileOutputStream(fd.getDirectory() + "\\" + fd.getFile() + "\\" + fd.getFile() + ".bin2");  //On ouvre un nouveau dossier
            ((Fenetre2) frameParent).sauverImage(fd.getDirectory() + "\\" + fd.getFile() + "\\" + fd.getFile()+"."+p.formatImage);
          }
        }
        
        
        ObjectOutputStream objOut = new ObjectOutputStream(out);
        objOut.writeObject(p.r.getScene());
        objOut.reset();
        if (faireLesDeux) {
          ParametresRaytracing param = p.r.getParam();
          param.aggrandirLJusque(300);
          objOut.writeObject(param);
        }
        objOut.close();
        
      } 
      catch (IOException e) {
        System.out.println(e);
      }
    }
    else if (evt.getSource()==ouvrirScene ||evt.getSource()==ouvrirPack)
      try {
        FileDialog fd = new FileDialog(frameParent);
        fd.setVisible(true);
        boolean faireLesDeux;
        
        FileInputStream in=new FileInputStream(fd.getDirectory() + fd.getFile());;
        ObjectInputStream objin = new ObjectInputStream(in);
        SceneRaytracing aLire = (SceneRaytracing) objin.readObject();
       
        if (evt.getSource()==sauverScene) {
          p.r=new Raytracing(aLire);
        }
        else p.r=new Raytracing(aLire, (ParametresRaytracing) objin.readObject());
        
        objin.close();
        
        try {
            p.f2.setVisible(false);
        } catch(NullPointerException e) {
        }
        
        p.f1.setVisible(false);
        p.f1= new Fenetre1(p);
      } 
      catch (IOException e) {
        System.out.println(e);
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    */
    
  }
}
