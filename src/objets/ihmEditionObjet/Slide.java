package objets.ihmEditionObjet;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ihm.Programme;

public class Slide extends JFrame{

  private JLabel label = new JLabel("Valeur actuelle : 0");   
  private Programme p;
  private JSlider slide;

  
  
  //=====================
  //Methodes réécrites pour s'appliquer à slide

  
  public void addChangeListener(ChangeListener c) {
    slide.addChangeListener(c);
  }
  
  public void setMaximum(int i){
    slide.setMaximum(i);
  }
  
  public void setMinimum(int i) {
    slide.setMinimum(i);
  }
  
  public void setValue(int i) {
    slide.setValue(i);
  }
  
  public int getMaximum(){
    return slide.getMaximum();
  }
  
  public int getMinimum() {
    return slide.getMinimum();
  }
  
  
  //=========================================
  //Constructeur
  
  public Slide(){

    this.setSize(250, 150);
    this.setTitle("Slider");
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      

    slide = new JSlider();

   

    slide.setMaximum(10);
    slide.setMinimum(-10);
    slide.setValue(0);

    slide.setPaintTicks(true);
    slide.setPaintLabels(true);
    slide.setMinorTickSpacing(10);
    slide.setMajorTickSpacing(20);

    slide.addChangeListener(new ChangeListener(){

      public void stateChanged(ChangeEvent event){

        label.setText("Valeur actuelle : " + ((JSlider)event.getSource()).getValue());
        //p.modifBlanc(((JSlider)event.getSource()).getValue(),slide.getMaximum());
        
      }

    });      

    this.getContentPane().add(slide, BorderLayout.CENTER);

    this.getContentPane().add(label, BorderLayout.SOUTH);      
    
    
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

  }   

  
  
  public static void main(String[] args){

    Slide slide = new Slide();

    slide.setVisible(true);

  }   

}