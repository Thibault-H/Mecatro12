package corps;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import corps.tableauCouleurs.TableauCouleurs;
import corps.tableauCouleurs.algos.AlgoRaytracing;
import corps.tableauCouleurs.algos.AlgoSimple;
import corps.tableauCouleurs.parametres.ParametresRaytracing;
import objets.scene.Objet;
import objets.scene.SceneRaytracing;

public class Raytracing extends GenerateurImageScene implements ActionListener{

	//=============================================
	//Attributs
	//=============================================


	protected ParametresRaytracing param;
	protected SceneRaytracing sc;





	//=====================================
	//Constructeur: définit les attributs par défaut
	
	public Raytracing() {
		sc=new SceneRaytracing();
		param=new ParametresRaytracing();
	}

	public Raytracing(SceneRaytracing s) {
		sc=s;
		param=new ParametresRaytracing();
	}

	public Raytracing(SceneRaytracing s, ParametresRaytracing p) {
		sc=s;
		param=p;
	}

	//======================================================
	//Getters & Setters

	@Override
	public ParametresRaytracing getParam() {
		return param;
	}

	public void setParam( ParametresRaytracing par) {
		param=par;
	}

	@Override
	public SceneRaytracing getScene() {
		return sc;
	}

	
	// =================================================
	// ============= Programme principal ===============
	// =================================================

	

	@Override
	public TableauCouleurs getTab(TypeImage t) {
		switch(t) {
		case Raytracing: return new TableauCouleurs(new AlgoRaytracing(param,sc));
		case Previsualisation: return new TableauCouleurs(new AlgoSimple(param,sc));
		default : throw new RuntimeException(t+" pas pris en compte par ce générateur d'image!");
		}
	}


	//=========================================================
	//Methodes de programmable

	@Override
	public BufferedImage imageFinale() {
		return mainProgram(TypeImage.Raytracing);
	}

	@Override
	protected ParametresRaytracing getParamPrevisu() {
		return getParam();
	}


	//=============================================

	

	public static void main(String[] args) {
		Raytracing r = new Raytracing();
	}




}
