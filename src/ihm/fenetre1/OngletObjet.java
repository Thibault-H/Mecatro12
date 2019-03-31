package ihm.fenetre1;

import java.awt.Button;
import java.awt.Choice;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;

import ihm.Fenetre1;
import ihm.util.MultiLineLabel;
import objets.objetPhong.Surface;
import optique.Source;

public class OngletObjet extends Panel implements ItemListener, ActionListener{
  /**
   * 
   */
  private static final long serialVersionUID = 6803651736580960832L;
  Label lObjets, lSources;
  JLabel lObjets2;
  MultiLineLabel lSources2;
  public List listeObjets;
  public List listeSources;
  public Button ajouter;
  public Choice listeNouv;
  
  public Button supprimerObj;
  public Button supprimerSou;
  public Button modifierObj;
  public Button modifierSou;
  
  Surface[] tabsu;
  Source[] tabso;
  
  Fenetre1 f;
  
  public OngletObjet(Fenetre1 fen) {
    super();
    setLayout(new GridBagLayout());
    
    f=fen;
    //Boutons
    ajouter=new Button("Ajouter");
    ajouter.addActionListener(fen);
    
    
    supprimerObj= new Button("Supprimer");
    supprimerObj.addActionListener(fen);
    supprimerSou= new Button("Supprimer");
    supprimerSou.addActionListener(fen);
    
    modifierObj= new Button("Modifier");
    modifierObj.addActionListener(fen);
    modifierSou= new Button("Modifier");
    modifierSou.addActionListener(fen);
    
    listeNouv = new Choice();
    listeObjets=new List(5,false);
    listeObjets.addItemListener(this);
    listeSources=new List(5,false);
    listeSources.addItemListener(this);
    
    tabsu = fen.p.r.getScene().getListeObjetsEditables();  
    for (int i=0; i< tabsu.length; i++)
      listeObjets.add((i+1) + " "+ tabsu[i].getClass().getName().substring(7));

    
    tabso = fen.p.r.getScene().getSources();  
    for (int i=0; i< tabso.length; i++)
      listeSources.add((i+1) + " "+ tabso[i].getClass().getName().substring(8));
    
    
    listeNouv.add("Plan");
    listeNouv.add("Sphere");
    listeNouv.add("Quadrique");
    listeNouv.add("Source");
    listeNouv.add("Cube");
    
    //Etiquettes
    lObjets= new Label("Objets");
    lObjets2= new JLabel(tabsu[0].toString());
    
    //Finitions
/*    add(lObjets);
    add(lSources);
    add(lObjets2);
    add(lSources2);
    add(listeObjets);
    add(listeSources);
    add(listeNouv);
    add(new Panel());
    add(ajouter);
    add(supprimer);*/
    
    GridBagConstraints c = new GridBagConstraints();
    
    c.gridx=0;
    c.gridy=1;
    add(lObjets,c);
    
    c.gridx=2;
    c.gridy=1;
    c.gridheight=1;
    add(lObjets2,c);
    
    c.gridx=0;
    c.gridy=2;
    add(listeObjets,c); 

    c.gridx=0;
    c.gridy=5;
    c.insets = new Insets(0,0,50,0);
    add(modifierObj,c);
    
    c.gridx=0;
    c.gridy=5;
    c.insets = new Insets(0,50,0,0);
    add(supprimerObj,c);
    

    //Liste des sources
    
    
    lSources= new Label("Sources");
    lSources2= new MultiLineLabel(tabso[0].toString());
    
    c.gridx=1;
    c.gridy=1;
    add(lSources,c);
    
    c.gridx=2;
    c.gridy=2;
    c.gridheight=3;
    add(lSources2,c);
        
    c.gridx=1;
    c.gridy=2;
    add(listeSources,c);
    

    
    
    c.gridx=1;
    c.gridy=5;
    c.insets = new Insets(0,0,50,0);
    add(modifierSou,c);
    
    c.gridx=2;
    c.gridy=5;
    add(listeNouv,c);
    
    c.gridx=3;
    c.gridy=5;
    add(ajouter,c);
        
    c.gridx=1;
    c.gridy=5;
    c.insets = new Insets(0,50,0,0);
    add(supprimerSou,c);
    
  }

  //*On suppose qu'on a déjà ajouté l'objet dans le programme
  public void AjouterSurf(Surface o) {
    tabsu = f.p.r.getScene().getListeObjetsEditables();  
    listeObjets.add((listeObjets.getItemCount()+1) + " "+ tabsu[tabsu.length-1].getClass().getName().substring(7));
  }
  
  //*On suppose qu'on a déjà ajouté la source dans le programme
  public void AjouterSource(Surface o) {
    tabso = f.p.r.getScene().getSources();  
    listeSources.add((listeSources.getItemCount()+1) + " "+ tabso[tabso.length-1].getClass().getName().substring(7));
  }
  
  public void SupprimerSurf(int i) {
      listeObjets.remove(i);
      revalidate();
  }
  
  public void SupprimerSource(int i) {
    listeSources.remove(i);
    revalidate();
  }
  
  @Override
  public void itemStateChanged(ItemEvent e) {
    if (e.getSource()==listeObjets) {
      lObjets2.setText(tabsu[Integer.parseInt(listeObjets.getSelectedItem().substring(0, 1))-1].toString());
    }
    else if (e.getSource()==listeSources) {
      lSources2.setText(tabso[Integer.parseInt(listeSources.getSelectedItem().substring(0, 1))-1].toString());
    }
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    
  }



    
  
}
