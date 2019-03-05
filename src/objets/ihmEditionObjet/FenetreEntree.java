package objets.ihmEditionObjet;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import algLin.Point3;
import algLin.R3;
import objets.editable.Editable;
import objets.editable.TypeEntrable;
import objets.objetPhong.Rectangle;

public class FenetreEntree extends JFrame{
	List<Entree> contenu;
	JPanel contenant;
	
	
	private FenetreEntree(String nomFenetre) {
		super(nomFenetre);
	}
	
	public FenetreEntree(Editable obj) {
		super(obj.getNom());
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		construireContenu(obj.getTypeAttributsEditables());
		majFenetre();
		setVisible(true);
	}
	
	
	private void construireContenu(Map<String,TypeEntrable> m) {
		contenu = new ArrayList<Entree>();
		
		Iterator<String> itr = m.keySet().iterator();
		String tmp;
		Entree aAjouter;
		while (itr.hasNext()) {
			tmp=itr.next();
			aAjouter = creerEntree(tmp,m.get(tmp));
			if (aAjouter != null)
				add(aAjouter);
		}
		
	}
	
	private Entree creerEntree(String nom, TypeEntrable t) {
		switch (t) {
			case Scalaire: return new EntreeScal(nom) ;
			case Vecteur: return new EntreeR3(nom,false);
			default : return null;
		}
	}
	
	private void majFenetre() {
		contenant = new JPanel();
		contenant.setLayout(new FlowLayout());
		Iterator<Entree> itr = contenu.iterator();
		while (itr.hasNext()) {
			contenant.add(itr.next());
		}

        validate();
        repaint();
        pack();
	}
	
	public static void main(String[] args) {
		Rectangle s = new Rectangle("rect1", R3.ux, Point3.origine, 2, 3, Color.BLACK);
		FenetreEntree fen = new FenetreEntree(s);
	}
}
