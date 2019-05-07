package ihm.fenetreImage;

import corps.GenerateurImage;
import ihm.Programmable;

public class AfficheurImageEdition extends ImageScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6534065663964311939L;

	Programmable gene;
	
	public AfficheurImageEdition(Programmable programmable) {
		super();
		gene = programmable;
	}
	
	
	public void repaintEdition() {
		super.affecterImage(gene.imageEdition());
	}
	
	public void repaintFinal() {
		super.affecterImage(gene.imageFinale());
	}
}
