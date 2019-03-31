package ihm.fenetreImage;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;





///////////////////////////////////////////////////////////////
// CLASSE VisuImage (programme principal)
///////////////////////////////////////////////////////////////
public class VisuImage extends Frame implements ActionListener, ItemListener {
  static final long serialVersionUID = 1L; // attribut recommandé pour un objet "Serializable"

  private Button boutonSauverImage;
  private Checkbox typeVisu;
  private MenuItem sauverImage;
  private MenuItem zoomer;
  protected MenuBar mb;
  protected Panel commandes;
  public Menu menuFichier;
  public MenuItem quitter;

  private ImageCanvas zoneImage;
  private ImageFrame fenetreExterne;
  private Frame fenetreZoom;
  
  private double coefZoom;
  protected BufferedImage img;
  public String format = "png";

  public VisuImage() {
    // invocation du constructeur de la classe mère :
    super("VisuImage");   // paramètre = titre de la fenêtre
    setLayout(new BorderLayout());
    coefZoom=100;
    // =======================================================
    // Elements Graphiques
    
    boutonSauverImage = new Button("Sauver l'image");
    boutonSauverImage.addActionListener(this);
        
    typeVisu = new Checkbox("FENETRE EXTERNE");
    typeVisu.addItemListener(this);
        
    commandes = new Panel();
    commandes.setLayout(new GridLayout(1, 2));
    commandes.add(boutonSauverImage);
    commandes.add(typeVisu);
    add(commandes, BorderLayout.NORTH);
    

    zoneImage = new ImageCanvas();
    add(zoneImage, BorderLayout.CENTER);
    
    
    // fenêtre séparée :
    fenetreExterne = new ImageFrame();
    fenetreExterne.addWindowListener(new WindowAdapter() {
      @Override
	public void windowClosing(WindowEvent w) {
        // masque la feêntre externe :
        fenetreExterne.setVisible(false);
        typeVisu.setState(false);
        afficherImage();
        
      }
    });
    
    // fenêtre de zoom
    fenetreZoom = new Frame("Zoom");
    TextField text= new TextField("100",3);
    Button zoomOK= new Button("OK");
    zoomOK.addActionListener(new ActionListener() {

      @Override
	public void actionPerformed(ActionEvent e) {
        coefZoom=(Double.valueOf(text.getText()));
        afficherImage();
        
      }
      
    });
    fenetreZoom.addWindowListener(new WindowAdapter() {
      @Override
	public void windowClosing(WindowEvent w) {
        // masque la feêntre externe :
        fenetreZoom.setVisible(false);
        
      }
    });
    fenetreZoom.add(text,BorderLayout.NORTH);
    fenetreZoom.add(zoomOK,BorderLayout.CENTER);
    fenetreZoom.repaint();
    fenetreZoom.pack();
    
    //==========================================================
    // barre de menu :
    mb = new MenuBar();
    setMenuBar(mb);
    
    //===%%%%=====
    
    menuFichier = new Menu("Fichier");
    mb.add(menuFichier);
    
    //Sauver l'image
    sauverImage = new MenuItem("Sauvegarder l'image");
    menuFichier.add(sauverImage);
    sauverImage.addActionListener(this);
    
    //Quitter
    quitter = new MenuItem("Quitter");
    menuFichier.add(quitter);
    quitter.addActionListener(
      new ActionListener() {
        @Override
		public void actionPerformed(ActionEvent evt) {
          System.exit(0);
        }
      } 
    );
    
    
    
    //===%%%%====
    
    Menu menuAffichage = new Menu("Affichage");
    mb.add(menuAffichage);
    
    //Zoom
    zoomer = new MenuItem("Zoom");
    zoomer.addActionListener(this);
    menuAffichage.add(zoomer);
    
    
  }
  
  public VisuImage(BufferedImage pic) {
	  	this();
	    //Finitions
	    img=pic;
	    afficherImage();
	    // écoute des evts de type "fenetre" via une classe dédiée (pour quitter en cas de fermeture) :
	    addWindowListener(new WindowAdapter() {
	      @Override
		public void windowClosing(WindowEvent w) {
	        // masque la feêntre externe :
	        setVisible(false);
	        
	      }
	    }); 
  }
  
  
  public void sauverImage() {
    try {
      FileDialog fd = new FileDialog(this);
      fd.setVisible(true);
      String nomFichier = fd.getDirectory() + fd.getFile();
      ImageIO.write(img, format, new File(nomFichier+"."+format));
    } catch (IOException e) {
      System.out.println(e);
    }
  }
  
  public void sauverImage(String s) {
   try {
    String nomFichier = s;
    ImageIO.write(img, format, new File(nomFichier+"."+format));
    } catch (IOException e) {
    System.out.println(e);
    }
  }
  
  
  
  @Override
public void actionPerformed(ActionEvent evt) {
    if (evt.getSource() == sauverImage || evt.getSource() == boutonSauverImage) {
      sauverImage();
    }
    else if (evt.getSource() == zoomer) {
      fenetreZoom.setVisible(true);
    }
  }
  
  
  @Override
public void itemStateChanged(ItemEvent evt) {
    if (evt.getSource() == typeVisu) {
      if (evt.getStateChange() == ItemEvent.SELECTED) {
        zoneImage.affecterImage(null);
        repaint();
      } 
      else fenetreExterne.setVisible(false);
      
      afficherImage();
    }
  }
  
  
  
  /**Affiche l'image au bon endroit
   * 
   */
  private void afficherImage() {
    if (typeVisu.getState()) {
      // affichage dans une fenêtre séparée :
      fenetreExterne.affecterImage(img,coefZoom);
      fenetreExterne.setTitle("Image");  // positionne le titre
      fenetreExterne.setVisible(true);
    } else {
      // affichage dans la fenêtre principale :
      zoneImage.affecterImage(img,coefZoom);
      pack();
      
    }

  }
  
  public void changerImage(BufferedImage pic){
    img=pic;
    afficherImage();
  }
  
  
  public static void main(String[] args) {
    BufferedImage img=new BufferedImage(500,500, BufferedImage.TYPE_INT_RGB);
    Graphics g2 = img.getGraphics();
    g2.setColor(Color.blue);
    g2.fillRect(100, 100, 300, 300);
    VisuImage appli = new VisuImage(img);
    appli.setLocation(150, 100);
    appli.setVisible(true);
  }
}
