package corps;

import java.awt.image.BufferedImage;

import corps.tableauCouleurs.TableauCouleurs;
import corps.tableauCouleurs.algos.AlgoMecatro;
import corps.tableauCouleurs.algos.AlgoRaytracing;
import corps.tableauCouleurs.algos.AlgoSimple;
import corps.tableauCouleurs.parametres.ParametresMecatro;
import corps.tableauCouleurs.parametres.ParametresRaytracing;
import objets.scene.SceneMecatro;

public class Mecatro extends GenerateurImageScene{

	//=============================================
	//Attributs
	//=============================================



	protected ParametresMecatro param;
	protected SceneMecatro sc;



	//=====================================
	//Constructeur: définit les attributs par défaut
	
	public Mecatro() {
		sc=new SceneMecatro();
		param=new ParametresMecatro();
	}

	public Mecatro(SceneMecatro s) {
		sc=s;
		param=new ParametresMecatro();
	}

	public Mecatro(SceneMecatro s, ParametresMecatro p) {
		sc=s;
		param=p;
	}

	
	//===============================================
	//Getters
	
	@Override
	public SceneMecatro getScene() {
		return sc;
	}

	@Override
	public ParametresMecatro getParam() {
		return param;
	}
	
	//==================================================
	@Override
	public TableauCouleurs getTab(TypeImage t) {
		switch(t) {
		case Raytracing: return new TableauCouleurs(new AlgoRaytracing(param.toRay(),sc));
		case Previsualisation: return new TableauCouleurs(new AlgoSimple(param.toRay(),sc));
		case Mecatro : return new TableauCouleurs(new AlgoMecatro(param,sc));
		default : throw new RuntimeException(t+" pas pris en compte par ce générateur d'image!");
		}
	}
	

	//=========================================================
	//Methodes de programmable

	@Override
	public BufferedImage imageFinale() {
		return mainProgram(TypeImage.Mecatro);
	}

	@Override
	protected ParametresRaytracing getParamPrevisu() {
		return param.toRay();
	}




}
