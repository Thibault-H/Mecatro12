package corps;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import auxMaths.algLin.Point3;
import corps.tableauCouleurs.Parametres;
import corps.tableauCouleurs.parametres.ParametresRaytracing;
import ihm.TypeAction;
import ihm.fenetre1.commandesSup.DoubleJoystick;
import ihm.fenetre1.ongletsEdition.OngletParam;
import ihm.fenetre1.ongletsEdition.OngletScene;
import ihm.fenetreImage.ImageScrollPane;
import objets.scene.Stageable;

public abstract class GenerateurImageScene extends GenerateurImage implements ActionListener{

	DoubleJoystick joyCamera;
	ImageScrollPane imagePrevisu;
	public Point3 refCamera;
	public double incrtrans; //L'incrément de translation pour le joystick
	public double incrrot;  //L'incrément de translation pour le joystick
	public String formatImage;


	GenerateurImageScene(){
		super();
		joyCamera=new DoubleJoystick(this);
		incrtrans=1;
		incrrot=Math.PI/6;
		refCamera=Point3.origine;
		formatImage="jpg";
		imagePrevisu= new ImageScrollPane();

	}


	public abstract Stageable getScene();
	public abstract Parametres getParam();
	protected abstract ParametresRaytracing getParamPrevisu();

	public void afficherImage() {
		imagePrevisu.affecterImage(mainProgram(TypeImage.Previsualisation));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof OngletParam) {
			OngletParam ong =(OngletParam)e.getSource(); 
			if (ong.isHauteurEditee.getState()) {//On édite la hauteur
				getParam().aggrandirHJusque(ong.getHaut());
				ong.larg.setText(String.valueOf(getParam().getLargpx()));        
			}
			else {//On �dite la largeur
				getParam().aggrandirLJusque(ong.getLarg());
				ong.haut.setText(String.valueOf(getParam().getHautpx()));
			}

			formatImage=ong.getFormat();
		}	          
		else if (e.getSource() instanceof Button){

			int typeReaction = joyCamera.AFaire((Button) e.getSource());
			ParametresRaytracing param = getParamPrevisu();
			param.setOeil(param.getOeil().transformation(typeReaction, incrtrans, incrrot, param.getBase(), refCamera));
			//majRefCam();
			param.setBase(param.getBase().transformation(typeReaction, incrrot, param.getBase(), Point3.origine));
			afficherImage();
			//f1.repaint();
		}

	}



	//================================================
	//Methodes de Programmable

	@Override
	public JPanel commandesSupp() {
		return joyCamera;
	}

	@Override
	public JTabbedPane ongletsEdition() {
		JTabbedPane res = new JTabbedPane();
		res.add("Scene", new OngletScene(this));
		res.addTab("Parametres", new OngletParam(this));
		return res;
	}


	@Override
	public String getFormatImage() {
		return formatImage;
	}


}
